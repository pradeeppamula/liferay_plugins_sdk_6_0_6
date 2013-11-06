/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.mongo
 * @created July 15, 2013
 * @description This class will handle all mongodb related functionality.
 */
package org.ieeecs.communities.mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.List;

public class MongoHandler {
    private MongoClient mongoClient = null;
    private DB db = null;
    private MongoConfigUtil.Collection collection = null;
    private static MongoHandler instance;

    /**
     * Default constructor
     */
    private MongoHandler() {
    }

    /**
     * This helper function will establish the connection to the
     * mongoDB specified by the mongoURI built from the configurations.
     *
     * @throws MongoException
     */
    private void initializeConnection() throws MongoException {
        try {
            // read in the mongo configurations
            JSONParser jsonParser = new JSONParser();
            InputStream inputStream = this.getClass().getResourceAsStream(MongoConfigUtil.CONFIG_FILE_PATH);
            InputStreamReader reader = new InputStreamReader(inputStream);
            JSONObject jsonConfigObject = (JSONObject) jsonParser.parse(reader);
            String enviroment = (String) jsonConfigObject.get("environment");
            String connectionMode =  (String) jsonConfigObject.get("connectionMode");
            JSONObject envConfig = (JSONObject) jsonConfigObject.get(enviroment);
            JSONObject mongoURIObject = (JSONObject)envConfig.get("mongoURI");
            String mongoURI =  (String)mongoURIObject.get(connectionMode);
            this.mongoClient = new MongoClient(
                    new MongoClientURI(MongoConfigUtil.getConstructedURI(mongoURI, this.collection.getDatabaseName())));

        } catch (Exception e) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'initializeConnection', 'exception' : " + e + "}");
            throw new MongoException(MongoError.INITIALIZE_ERROR);
        }
    }

    /**
     * This function will set the collection and db information, and
     * initialize the connection if necessary before a query is
     * executed against the database.
     *
     * @param collection
     * @throws Exception
     */
    private void preQuerySetup(MongoConfigUtil.Collection collection) throws Exception {
        // set the collection to be saved to
        this.setCollection(collection);
        // verify that there is an active connection
        if (this.mongoClient == null) this.initializeConnection();
        // set the database that will be utilized
        this.db = mongoClient.getDB(this.collection.getDatabaseName());
        // ensure the request is handled in the same socket
        this.db.requestStart();
        // ensure there is a connection to the db
        this.db.requestEnsureConnection();
    }

    /**
     * This method will return the singleton instance for
     * the MongoHandler.
     *
     * @return MongoHandler
     */
    public static MongoHandler getInstance() {
        if (instance == null) {
            synchronized (MongoHandler.class) {
                if (instance == null) {
                    instance = new MongoHandler();
                }
            }
        }
        return instance;
    }

    /**
     * This method will close the mongo client
     * connections.
     */
    public void close() {
        if(this.mongoClient != null) {
            this.mongoClient.close();
            this.mongoClient = null;
        }
    }

    /**
     * Sets the collection to be used during the query
     * @param collection
     */
    public void setCollection(MongoConfigUtil.Collection collection) {
        this.collection = collection;
    }

    /**
     * This method will retrieve a given collection
     *
     * @return DBCollection
     * @throws MongoException
     */
    private DBCollection getCollection() throws MongoException {
        DBCollection retVal = null;
        try {
            // verify that there is an active connection
            if (this.mongoClient == null) this.initializeConnection();
            // set the database that will be utilized
            this.db = (this.db == null) ? mongoClient.getDB(this.collection.getDatabaseName()) : this.db;
            // retrieve the collection
            retVal = this.db.getCollection(this.collection.getCollectionName());
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'getCollection', 'exception' : " + e + "}");
            throw new MongoException(MongoError.COLLECTION_ERROR);
        }
        return retVal;
    }

    /**
     * This method will use the helper MongoDB Driver to convert
     * a validate JSON string to a DBObject
     *
     * @param json
     * @return DBObject
     * @throws MongoException
     */
    public BasicDBObject jsonToDBObject(String json) throws MongoException {
        BasicDBObject retVal = null;
        try {
            // convert the json
            retVal = (BasicDBObject) JSON.parse(json);
        } catch (Exception e) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'jsonToDBObject', 'exception' : " + e + "}");
            throw new MongoException(MongoError.JSONTODBOBJECT_ERROR);
        }
        return retVal;
    }

    /**
     * This method will use the helper MongoDB Driver to convert
     * a validate JSON string to a BasicDBList
     *
     * @param json
     * @return DBObject
     * @throws MongoException
     */
    public BasicDBList jsonToDBList(String json) throws MongoException {
        BasicDBList retVal = null;
        try {
            // convert the json
            retVal = (BasicDBList) JSON.parse(json);
        } catch (Exception e) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'jsonToDBObject', 'exception' : " + e + "}");
            throw new MongoException(MongoError.JSONTODBLIST_ERROR);
        }
        return retVal;
    }

    /**
     * This helper function will save the passed in document to
     * the active mongo db collection.
     *
     * @param collection
     * @param document
     * @throws MongoException
     */
    public void insert(final MongoConfigUtil.Collection collection, final BasicDBObject document) throws MongoException {
        try {
            // setup the connection to the db and collection
            this.preQuerySetup(collection);
            // insert the document
            this.getCollection().insert(document);
        } catch (Exception me) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'insert', 'exception' : " + me + "}");
            throw new MongoException(MongoError.INSERT_ERROR);
        } finally {
            // free up the connection back to the connection pool
            this.db.requestDone();
        }
    }

    /**
     * This method will update the document for the collection and
     * search query document parameters to update the data in mongodb.
     *
     * @param collection
     * @param searchQuery
     * @param document
     * @throws MongoException
     */
    public void update(final MongoConfigUtil.Collection collection, final BasicDBObject searchQuery, final BasicDBObject document) throws MongoException {
        try {
            // setup the connection to the db and collection
            this.preQuerySetup(collection);
            // update the document
            this.getCollection().update(searchQuery, document);
        } catch (Exception me) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'update', 'exception' : " + me + "}");
            throw new MongoException(MongoError.UPDATE_ERROR);
        } finally {
            // free up the connection back to the connection pool
            this.db.requestDone();
        }
    }

    /**
     * This method will update the document(s) for the collection and
     * search query document parameters to update the data in mongodb.
     * Note: for multiple update, $set operator must be used in the
     * search query, otherwise an error will be thrown.
     *
     * @param collection
     * @param searchQuery
     * @param document
     * @throws MongoException
     */
    public void updateMultiple(final MongoConfigUtil.Collection collection, final BasicDBObject searchQuery, final BasicDBObject document) throws MongoException {
        try {
            // setup the connection to the db and collection
            this.preQuerySetup(collection);
            // update the document (will not upsert but will update multiple docs matching the query)
            this.getCollection().update(searchQuery, document, false, true);
        } catch (Exception me) {
            // TODO: log exception
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'update', 'exception' : " + me + "}");
            throw new MongoException(MongoError.UPDATE_ERROR);
        } finally {
            // free up the connection back to the connection pool
            this.db.requestDone();
        }
    }

    /**
     * This function will retrieve the documents based on the passed in query
     *
     * @param collection
     * @param query
     * @return retVal
     * @throws MongoException
     */
    public List<DBObject> find(final MongoConfigUtil.Collection collection, final BasicDBObject query) throws MongoException {
        List<DBObject> retVal = null;
        try {
            // setup the connection to the db and collection
            this.preQuerySetup(collection);
            // perform the query against the collection
            retVal = this.getCollection().find(query).toArray();
        } catch (Exception e) {
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'find', 'exception' : " + e + "}");
            throw new MongoException(MongoError.FIND_ERROR);
        } finally {
            // free up the connection back to the connection pool
            this.db.requestDone();
        }
        return retVal;
    }

    /**
     * This method will remove all documents from the passed in collection based
     * on the query
     *
     * @param collection
     * @param query
     * @return boolean if the removal was successful or not
     * @throws MongoException
     */
    public boolean remove(final MongoConfigUtil.Collection collection, final BasicDBObject query) throws MongoException {
        boolean retVal = false;
        try {
            // setup the connection to the db and collection
            this.preQuerySetup(collection);
            // perform the query against the collection
            WriteResult result = this.getCollection().remove(query);
            if (result != null && (result.getError() == null || "".equalsIgnoreCase(result.getError()))) {
                retVal = true;
            }
        } catch (Exception e) {
            System.out.println("{ 'class' : 'MongoHandler', 'method' : 'remove', 'exception' : " + e + "}");
            throw new MongoException(MongoError.REMOVE_ERROR);
        } finally {
            // free up the connection back to the connection pool
            this.db.requestDone();
        }
        return retVal;
    }
}