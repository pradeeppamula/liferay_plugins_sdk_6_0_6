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

import java.util.*;

import org.ieeecs.service.util.ThemeServicesUtil;
import org.ieeecs.theme.service.base.MetaTagLocalServiceBaseImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * The implementation of the meta tag local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.ieeecs.theme.service.MetaTagLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link org.ieeecs.theme.service.MetaTagLocalServiceUtil} to access the meta tag local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author IEEECS
 * @see org.ieeecs.theme.service.base.MetaTagLocalServiceBaseImpl
 * @see org.ieeecs.theme.service.MetaTagLocalServiceUtil
 */
public class MetaTagLocalServiceImpl extends MetaTagLocalServiceBaseImpl {
	
	private static String databaseName;
	private static String collectionName;
	private static String csdlCollectionName;
	private static String mongoDBUrl;
	private static MongoClient mongoClient;
	
	
	public Map<String,BasicDBObject> getMetaTagData(String currentUrl) {
		
		Map<String,BasicDBObject> metaTags = new TreeMap<String,BasicDBObject>();
		String targetUrl = currentUrl;
		
		try {
			
			long start = (new Date()).getTime();
			if ( null == mongoClient ) {
				Properties mongoConfigFile = new Properties();
				mongoConfigFile.load( ThemeStructureLocalServiceImpl.class.getClassLoader().getResourceAsStream("mongoConfig.properties") );
				databaseName       = mongoConfigFile.getProperty("mongo.meta.database.name");
				collectionName     = mongoConfigFile.getProperty("mongo.meta.collection.name");
				csdlCollectionName = mongoConfigFile.getProperty("mongo.meta.csdl.collection.name");
				mongoDBUrl         = mongoConfigFile.getProperty("mongo.meta.db.url");
				mongoClient        = new MongoClient(new MongoClientURI(mongoDBUrl));
			}

			if ( targetUrl.startsWith("/portal/web/guest/csdl/-/csdl") ) {				
				collectionName = csdlCollectionName;
				targetUrl = targetUrl.replaceAll("/portal/web/guest/csdl/-", "");
			} else if ( targetUrl.startsWith("/portal/web/csdl2/home/-/csdl") ) {				
				collectionName = csdlCollectionName;
				targetUrl = targetUrl.replaceAll("/portal/web/csdl2/home/-", "");				
			} else if ( targetUrl.startsWith("/web/guest/csdl/-/csdl") ) {				
				collectionName = csdlCollectionName;
				targetUrl = targetUrl.replaceAll("/web/guest/csdl/-", "");					
			} else if ( targetUrl.startsWith("/csdl") ) {				
				collectionName = csdlCollectionName;
			} 
			
			if ( targetUrl.indexOf("csdl2") > -1 ) {
				collectionName = csdlCollectionName;
			}

			List<DBObject> metaTagList = ThemeServicesUtil.find(mongoClient, databaseName, collectionName, new BasicDBObject("url", targetUrl));
			
			if ( null != metaTagList && metaTagList.size() > 0 ) {
				
				DBObject metaTagObject = metaTagList.get(0);
				DBObject tags = (DBObject) metaTagObject.get("tags");
				
				Iterator<String> tagsIterator = tags.keySet().iterator();
				while ( tagsIterator.hasNext() ) {				
					String tagId = (String) tagsIterator.next();
					BasicDBObject tagData = (BasicDBObject) tags.get(tagId);
					metaTags.put(tagId, tagData);
				}
			}

			long end = (new Date()).getTime();
			long total = end - start;
			metaTags.put("time", new BasicDBObject("value", total + " ms"));
			metaTags.put("currentUrl", new BasicDBObject("value", targetUrl));			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return metaTags;
	}

}