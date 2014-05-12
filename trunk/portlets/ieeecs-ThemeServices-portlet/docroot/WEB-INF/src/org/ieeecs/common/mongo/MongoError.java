/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.common.mongo
 * @created December 7, 2013
 * @description This class contains all the custom mongo errors
 */
package org.ieeecs.common.mongo;

public enum MongoError {
	
    COLLECTION_ERROR("300", "There was a problem retrieving the collection."),
    NO_CONNECTION("301", "No connection available."),
    INSERT_ERROR("302", "There was a problem inserting into mongo."), 
    INITIALIZE_ERROR("303", "There was a problem initializing the connection to mongo db."), 
    FIND_ERROR("304", "There was a problem retrieving the data."),
    REMOVE_ERROR("305", "There was a problem removing the data."),
    JSONTODBOBJECT_ERROR("306", "There was a problem converting the string json to a dbobject."),
    JSONTODBLIST_ERROR("307", "There was a problem converting the string json to a dblist."),
    UPDATE_ERROR("308", "There was a problem updating the document in mongodb.");
    
    public final String code;
    public final String message;
    
    private MongoError(final String errorCode, final String errorMessage) { 
    	this.code = errorCode; 
    	this.message = errorMessage;
    }
    
    public String getCode() { return this.code; }
    public String getMessage() { return this.message; }
}