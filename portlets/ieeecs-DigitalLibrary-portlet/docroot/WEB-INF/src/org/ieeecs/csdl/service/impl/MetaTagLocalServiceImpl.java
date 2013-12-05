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

package org.ieeecs.csdl.service.impl;

import java.util.*;

import org.ieeecs.csdl.bean.ContentBean;
import org.ieeecs.csdl.service.base.MetaTagLocalServiceBaseImpl;

import org.ieeecs.csdl.util.DigitalLibraryUtil;

/**
 * The implementation of the meta tag local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.ieeecs.csdl.service.MetaTagLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link org.ieeecs.csdl.service.MetaTagLocalServiceUtil} to access the meta tag local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author IEEECS
 * @see org.ieeecs.csdl.service.base.MetaTagLocalServiceBaseImpl
 * @see org.ieeecs.csdl.service.MetaTagLocalServiceUtil
 */
public class MetaTagLocalServiceImpl extends MetaTagLocalServiceBaseImpl {
	
	public Map<String,String> getMetaTagData(String currentUrl) {
		
		Map<String,String> metaTags = new HashMap<String,String>();
		long startTiming = DigitalLibraryUtil.getTime();
		
		try {
		
			if ( null != currentUrl && !"".equals(currentUrl.trim()) ) {
				
				String metaTagKey = currentUrl.replace(DigitalLibraryUtil.csdlProperties.getProperty("csdl.context.path.prefix"), "");
				metaTagKey = !"".equals(metaTagKey) ? metaTagKey.substring(1) : "";
				
				if ( metaTagKey.startsWith(DigitalLibraryUtil.MAGS) ||
					 metaTagKey.startsWith(DigitalLibraryUtil.TRANS) || 
					 metaTagKey.startsWith(DigitalLibraryUtil.PROCEEDINGS) ||
					 metaTagKey.startsWith(DigitalLibraryUtil.LETTERS) ) {
					
					String[] metaTagSegments = metaTagKey.split("/");
					int numberSegments = metaTagSegments.length;
					
					/*
					 * 		0 = publication type 	(pubType)
					 * 		1 = id prefix 			(idPrefix)
					 * 		2 = year				(year or volume)
					 * 		3 = issue number		(issueNumber)
					 * 		4 = file name			(fileName)
					 * 		5 = file detail			(fileDetail)
					 * 		6 = file type			(fileType)
					 */
					
					if ( numberSegments == 4 ) {
						
					} else if ( numberSegments == 5 ) {
						
					} else if ( numberSegments == 6 ) {
					
					} else if ( numberSegments == 7 ) {
						
						if ( "abs".equalsIgnoreCase(metaTagSegments[5]) ) {
							
							ContentBean cb = DigitalLibraryUtil.getAbstract(metaTagSegments);
							
							metaTags.put("citation_journal_title", "");
							metaTags.put("citation_journal_abbrev", "");
							metaTags.put("citation_publisher", cb.getPackageBean().getPublisher());
							metaTags.put("citation_title", cb.getTitle());							
							metaTags.put("citation_firstpage", DigitalLibraryUtil.getPageRange(cb.getLength())[0] ); 
							metaTags.put("citation_lastpage", DigitalLibraryUtil.getPageRange(cb.getLength())[1] );
							metaTags.put("citation_doi", cb.getDoi());
							metaTags.put("citation_fulltext_html_url", cb.getUri() + "/" + cb.getFilename() + ".html");
							metaTags.put("citation_pdf_url", cb.getUri() + "/" + cb.getFilename() + ".pdf");							
							metaTags.put("citation_language", "English");
							metaTags.put("citation_keywords", cb.getKeywords());
							
							if ( cb.getPublicationDate().indexOf("T") > -1 ) {
								String slashedDate = cb.getPublicationDate().substring(0,10).replaceAll("-", "/");
								metaTags.put("citation_publication_date", slashedDate);
							} else {
								metaTags.put("citation_publication_date", cb.getPublicationDate());
							}
							
							if ( null != cb.getPackageBean().getMetadataMap().get("volumeNumber") ) {
								metaTags.put("citation_volume", cb.getPackageBean().getMetadataMap().get("volumeNumber"));
							} else {
								metaTags.put("citation_volume", "");
							}
							
							if ( null != cb.getPackageBean().getMetadataMap().get("issueNumber") ) {
								metaTags.put("citation_issue", cb.getPackageBean().getMetadataMap().get("issueNumber"));
							} else {
								metaTags.put("citation_issue", cb.getPackageBean().getMetadataMap().get(""));
							}
							
							if ( null != cb.getPackageBean().getMetadataMap().get("issn") ) {
								metaTags.put("citation_issn", cb.getPackageBean().getMetadataMap().get("issn"));
							} else {
								metaTags.put("citation_issn","");
							}							

						} // if ( "abs".equalsIgnoreCase(metaTagSegments[5]) ) {				
					} // if ( numberSegments == 7 ) {					
				} // if ( metaTagKey.startsWith(DigitalLibraryUtil.MAGS) || ...
			} // if ( null != currentUrl && !"".equals(currentUrl.trim()) ) {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long totalTime = DigitalLibraryUtil.getTotalTime(startTiming);
		metaTags.put("metaTagService",totalTime+"");
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX (" + totalTime + ")");

		return metaTags;
	}
}