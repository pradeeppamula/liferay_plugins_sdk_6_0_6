/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.common.mongo
 * @created December 7, 2013
 * @description This class will handle all mongodb related functionality.
 */
package org.ieeecs.common.mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.List;


public class MongoHandler {
	
    /**
     * Default constructor
     */
    private MongoHandler() {}

    /**
     * This method will use the helper MongoDB Driver to convert
     * a validate JSON string to a DBObject
     *
     * @param json
     * @return DBObject
     * @throws MongoException
     */
    public static BasicDBObject jsonToDBObject(String json) throws MongoException {
    	
        BasicDBObject retVal = null;
        
        try {        	
            retVal = (BasicDBObject) JSON.parse(json);            
        } catch (Exception e) {
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
            retVal = (BasicDBList) JSON.parse(json);       
        } catch (Exception e) {
            throw new MongoException(MongoError.JSONTODBLIST_ERROR);
        }
        return retVal;
    }

    /**
     * This helper function will save the passed in document to the mongo db collection.
     * 
     * @param uri
     * @param databaseName
     * @param collectionName
     * @param document
     * @return
     * @throws MongoException
     */
    public static WriteResult insert(String uri, String databaseName, String collectionName, final BasicDBObject document) throws MongoException {
    	
    	MongoClient mongoClient = null;
    	DB db = null;
    	WriteResult result = null;
    	
        try {

            mongoClient = new MongoClient(new MongoClientURI(uri));
            db = mongoClient.getDB(databaseName);
            db.requestStart();
            db.requestEnsureConnection();
            result = db.getCollection(collectionName).insert(document);
            
        } catch (Exception me) {
            throw new MongoException(MongoError.INSERT_ERROR);
        } finally {
            db.requestDone();
            mongoClient.close();
        }
        
        return result;
    }

    /**
     * This method will update the document for the collection and
     * search query document parameters to update the data in mongodb.
     * 
     * @param uri
     * @param databaseName
     * @param collectionName
     * @param searchQuery
     * @param document
     * @throws MongoException
     */
    public static WriteResult update(String uri, String databaseName, String collectionName, 
    				   final BasicDBObject searchQuery, final BasicDBObject document) throws MongoException {
        
    	MongoClient mongoClient = null;
    	DB db = null;
    	WriteResult result = null;
    	
    	try {
    		
            mongoClient = new MongoClient(new MongoClientURI(uri));
            db = mongoClient.getDB(databaseName);
            db.requestStart();
            db.requestEnsureConnection();
            result = db.getCollection(collectionName).update(searchQuery, document, true, false);
     
        } catch (Exception me) {
            throw new MongoException(MongoError.UPDATE_ERROR);
        } finally {
            db.requestDone();
            mongoClient.close();
        }
    	
    	return result;
    }

    /**
     * This method will update the document(s) for the collection and
     * search query document parameters to update the data in mongodb.
     * Note: for multiple update, $set operator must be used in the
     * search query, otherwise an error will be thrown.
     *
     * @param uri
     * @param databaseName
     * @param collectionName
     * @param searchQuery
     * @param document
     * @return
     * @throws MongoException
     */
    public static WriteResult updateMultiple(String uri, String databaseName, String collectionName, 
    		                   final BasicDBObject searchQuery, final BasicDBObject document) throws MongoException {
        
    	MongoClient mongoClient = null;
    	DB db = null;
    	WriteResult result = null;
    	
    	try {
    		
            mongoClient = new MongoClient(new MongoClientURI(uri));
            db = mongoClient.getDB(databaseName);
            db.requestStart();
            db.requestEnsureConnection();
            
            // Update the document (will not upsert but will update multiple docs matching the query)
            result = db.getCollection(collectionName).update(searchQuery, document, false, true);
  
        } catch (Exception me) {
            throw new MongoException(MongoError.UPDATE_ERROR);
        } finally {
            db.requestDone();
            mongoClient.close();
        }
    	
    	return result;
    }

    /**
     * This function will retrieve the documents based on the passed in query
     *
     * @param uri
     * @param databaseName
     * @param collectionName
     * @param query
     * @return
     * @throws MongoException
     */
    public static List<DBObject> find(String uri, String databaseName, String collectionName, final BasicDBObject query) throws MongoException {
        
    	MongoClient mongoClient = null;
    	DB db = null;
    	List<DBObject> result = null;
    	
        try {
        	
            mongoClient = new MongoClient(new MongoClientURI(uri));
            db = mongoClient.getDB(databaseName);
            db.requestStart();
            db.requestEnsureConnection();
            result = db.getCollection(collectionName).find(query).toArray();
            
        } catch (Exception e) {
            throw new MongoException(MongoError.FIND_ERROR);
        } finally {
            db.requestDone();
            mongoClient.close();
        }
        
        return result;
    }

    /**
     * This method will remove all documents from the passed in collection based
     * on the query
     *
     * @param uri
     * @param databaseName
     * @param collectionName
     * @param query
     * @return
     * @throws MongoException
     */
    public static boolean remove(String uri, String databaseName, String collectionName, final BasicDBObject query) throws MongoException {
        
    	MongoClient mongoClient = null;
    	DB db = null;
    	WriteResult writeResult = null;
    	boolean result = false;
    	
        try {
        	
            mongoClient = new MongoClient(new MongoClientURI(uri));
            db = mongoClient.getDB(databaseName);
            db.requestStart();
            db.requestEnsureConnection();
            writeResult = db.getCollection(collectionName).remove(query);
            
            if (writeResult != null && (writeResult.getError() == null || "".equalsIgnoreCase(writeResult.getError()))) {
            	result = true;
            }
            
        } catch (Exception e) {
            throw new MongoException(MongoError.REMOVE_ERROR);
        } finally {
            db.requestDone();
            mongoClient.close();
        }
        
        return result;
    }
}