/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.ieeecs.theme.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.ieeecs.service.util.ThemeServicesUtil;
import org.ieeecs.theme.service.base.ThemeStructureLocalServiceBaseImpl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * The implementation of the theme structure local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.ieeecs.theme.service.ThemeStructureLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link org.ieeecs.theme.service.ThemeStructureLocalServiceUtil} to access the theme structure local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author IEEECS
 * @see org.ieeecs.theme.service.base.ThemeStructureLocalServiceBaseImpl
 * @see org.ieeecs.theme.service.ThemeStructureLocalServiceUtil
 */
public class ThemeStructureLocalServiceImpl extends ThemeStructureLocalServiceBaseImpl {
	
	private static String databaseName;
	private static String collectionName;
	private static String mongoDBUrl;
	private static MongoClient mongoClient;
	
	
	public Map<String,String> getThemeStructureSections(String communityName, String currentUrl) {
		
		Map<String,String> themeStructureMap = new HashMap<String,String>();
		String targetUrl = currentUrl;
		
		try {
			
			long start = (new Date()).getTime();
			if ( null == mongoClient ) {
				Properties mongoConfigFile = new Properties();
				mongoConfigFile.load( ThemeStructureLocalServiceImpl.class.getClassLoader().getResourceAsStream("mongoConfig.properties") );
				databaseName   = mongoConfigFile.getProperty("mongo.theme.database.name");
				collectionName = mongoConfigFile.getProperty("mongo.theme.collection.name");
				mongoDBUrl     = mongoConfigFile.getProperty("mongo.theme.db.url");
				mongoClient    = new MongoClient(new MongoClientURI(mongoDBUrl));
			}

			List<DBObject> structureList = ThemeServicesUtil.find(mongoClient, databaseName, collectionName, new BasicDBObject("url", targetUrl));
			
			if ( null != structureList && structureList.size() > 0 ) {
				
				populateThemeStructureMap(structureList, themeStructureMap);

			} else {
				
				BasicDBObject queryByCommunity = new BasicDBObject();
				queryByCommunity.put("community", communityName);
				queryByCommunity.put("type", "community");
				
				List<DBObject> communityStructureList = ThemeServicesUtil.find(mongoClient, databaseName, collectionName, queryByCommunity);
				
				if ( null != communityStructureList && communityStructureList.size() > 0 ) {
					
					populateThemeStructureMap(communityStructureList, themeStructureMap);
					
				} else {
					
					themeStructureMap.put("showInfo", "0");
					themeStructureMap.put("libraries", "");
					themeStructureMap.put("header", "");
					themeStructureMap.put("navigation", "");
					themeStructureMap.put("preBody", "");
					themeStructureMap.put("postBody", "");
					themeStructureMap.put("footer", "");
					
				}
			}

			long end = (new Date()).getTime();
			long totalTime = end - start;	
			themeStructureMap.put("totalTime", totalTime+"ms");
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return themeStructureMap;
	}
	
	private static void populateThemeStructureMap(List<DBObject> structureList, Map<String,String> themeStructureMap) {
	
		try {

			DBObject structureObject = structureList.get(0);
			String showInfo        = structureObject.containsField("showInfo") ? (String) structureObject.get("showInfo") : "0";
			BasicDBList libraries  = structureObject.containsField("libraries") ? (BasicDBList) structureObject.get("libraries") : new BasicDBList();
			BasicDBList header     = structureObject.containsField("header") ? (BasicDBList) structureObject.get("header") : new BasicDBList();
			BasicDBList navigation = structureObject.containsField("navigation") ? (BasicDBList) structureObject.get("navigation") : new BasicDBList();
			BasicDBList preBody    = structureObject.containsField("preBody") ? (BasicDBList) structureObject.get("preBody") : new BasicDBList();
			BasicDBList postBody   = structureObject.containsField("postBody") ? (BasicDBList) structureObject.get("postBody") : new BasicDBList();
			BasicDBList footer     = structureObject.containsField("footer") ? (BasicDBList) structureObject.get("footer") : new BasicDBList();
	
			themeStructureMap.put("showInfo", showInfo);
			themeStructureMap.put("libraries", StringUtils.join(libraries, ""));
			themeStructureMap.put("header", StringUtils.join(header, ""));
			themeStructureMap.put("navigation", StringUtils.join(navigation, ""));
			themeStructureMap.put("preBody", StringUtils.join(preBody, ""));
			themeStructureMap.put("postBody", StringUtils.join(postBody, ""));
			themeStructureMap.put("footer", StringUtils.join(footer, ""));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}