package org.ieeecs.service.util;

import java.util.*;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.json.XML;
import org.ieeecs.common.mongo.MongoException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class ThemeServicesUtil {

	public static long getTime() {
		return (new Date()).getTime();
	}

	public static long getTotalTime(long startTimeMS) {
		return (new Date()).getTime() - startTimeMS;
	}

	public static JSONObject getJSONObject(JSONObject targetJSONObject, String attributeName) {

		JSONObject resultingJSONObject = null;

		try {			
			resultingJSONObject = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getJSONObject(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingJSONObject;
	}

	public static BasicDBObject getBasicDBObject(BasicDBObject targetBasicDBObject, String attributeName) {

		BasicDBObject resultingBasicDBObject = null;

		try {			
			resultingBasicDBObject = null != targetBasicDBObject && targetBasicDBObject.containsField(attributeName) ? (BasicDBObject) targetBasicDBObject.get(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingBasicDBObject;
	}

	public static JSONArray getJSONArray(JSONObject targetJSONObject, String attributeName) {

		JSONArray resultingJSONArray = null;

		try {			
			resultingJSONArray = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getJSONArray(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingJSONArray;
	}	

	public static BasicDBList getBasicDBList(BasicDBObject targetBasicDBObject, String attributeName) {

		BasicDBList resultingBasicDBList = null;

		try {			
			resultingBasicDBList = null != targetBasicDBObject && targetBasicDBObject.containsField(attributeName) ? (BasicDBList) targetBasicDBObject.get(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingBasicDBList;
	}

	public static String getString(JSONObject targetJSONObject, String attributeName) {

		String resultingString = "";

		try {			
			resultingString = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getString(attributeName).trim() : "";			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingString;
	}

	public static String getString(BasicDBObject targetBasicDBObject, String attributeName) {

		String resultingString = "";

		try {			
			resultingString = null != targetBasicDBObject && targetBasicDBObject.containsField(attributeName) ? targetBasicDBObject.getString(attributeName).trim() : "";			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingString;
	}

	public static JSONObject jsonObjectFromDotNotation(JSONObject parentJSON, String dotNotation) {

		JSONObject resultingJSONObject = null;
		JSONObject processJSONObject = parentJSON;

		try {

			if ( null != dotNotation && !"".equals(dotNotation) ) {

				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						processJSONObject = getJSONObject(processJSONObject, currentNode);
					}

					resultingJSONObject = null != processJSONObject ? processJSONObject : resultingJSONObject;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingJSONObject;
	}

	public static BasicDBObject jsonObjectFromDotNotation(BasicDBObject parentBasicDBObject, String dotNotation) {

		BasicDBObject processBasicDBObject = parentBasicDBObject;

		try {

			if ( null != dotNotation && !"".equals(dotNotation) ) {

				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						processBasicDBObject = getBasicDBObject(processBasicDBObject, currentNode);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return processBasicDBObject;
	}

	public static JSONArray jsonArrayFromDotNotation(JSONObject parentJSON, String dotNotation) {

		JSONArray resultingJSONArray = null;
		JSONObject processJSONObject = parentJSON;

		try {

			if ( null != dotNotation && !"".equals(dotNotation) ) {

				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						if ( index < nodes.length - 1 ) {
							processJSONObject = getJSONObject(processJSONObject, currentNode);
						} else {
							if ( processJSONObject.get(currentNode) instanceof JSONArray ) {
								resultingJSONArray = getJSONArray(processJSONObject, currentNode);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingJSONArray;
	}

	public static BasicDBList jsonArrayFromDotNotation(BasicDBObject parentBasicDBObject, String dotNotation) {

		BasicDBList resultingBasicDBList = null;
		BasicDBObject processBasicDBObject = parentBasicDBObject;

		try {

			if ( null != dotNotation && !"".equals(dotNotation) ) {

				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						if ( processBasicDBObject.get(currentNode) instanceof BasicDBObject ) {
							processBasicDBObject = getBasicDBObject(processBasicDBObject, currentNode);
						} else if ( processBasicDBObject.get(currentNode) instanceof BasicDBList ) {
							resultingBasicDBList = getBasicDBList(processBasicDBObject, currentNode);
							break;  // Take the first BasicDBList on the chain
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultingBasicDBList;
	}	

	public static JSONObject byteArrayToJSONObject(byte[] responseBody) {

		JSONObject jsonResponse = null;

		try {
			if ( null != responseBody ) {
				jsonResponse = XML.toJSONObject(new String(responseBody));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}
	
	public static List<DBObject> find(MongoClient mongoClient, String databaseName, String collectionName, final BasicDBObject query) throws MongoException {

		DB db = null;
		List<DBObject> result = null;

		try {

			db = mongoClient.getDB(databaseName);
			db.requestStart();
			db.requestEnsureConnection();
			result = db.getCollection(collectionName).find(query).toArray();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.requestDone();
		}

		return result;
	}
	
}