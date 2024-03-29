/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.mongo
 * @created July 15, 2013
 * @description This class is for custom mongodb exceptions
 */
package org.ieeecs.communities.mongo;

public class MongoException extends Exception {
	private static final long serialVersionUID = -6265163124213887069L;
	public MongoError error;
	//Parameterless Constructor
    public MongoException() {}

    //Constructor that accepts a message
    public MongoException(MongoError error)
    {
       super(error.getMessage());
       this.error = error;
    }
}
