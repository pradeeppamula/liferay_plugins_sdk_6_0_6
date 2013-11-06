package org.ieee.common.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.ieee.common.constants.GlobalConstants;


public class DLFileUtil {

	public static String getFileContents(String dlFieldName, String dlFileName, boolean carriageReturn, boolean newLineFeed) {
		
		String output = "";
				
		try {

			DynamicQuery fileItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
												.add(PropertyFactoryUtil.forName(dlFieldName).eq(dlFileName));

			@SuppressWarnings("unchecked")
			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(fileItemQuery);

			if ( null != fileEntryList && fileEntryList.size() > 0 ) {

				DLFileEntry fe = fileEntryList.get(0);

				InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null)   {						
					output += line;					
					if ( carriageReturn ) {
						output += GlobalConstants.CR;
					}
					if ( newLineFeed ) {
						output += GlobalConstants.LF;
					}					
				}
				is.close();				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static String getFileContentsNoComments(String dlFieldName, String dlFileName, String[] commentSymbols, boolean carriageReturn, boolean newLineFeed) {
		
		String output = "";
		
		try {

			DynamicQuery fileItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
												.add(PropertyFactoryUtil.forName(dlFieldName).eq(dlFileName));

			@SuppressWarnings("unchecked")
			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(fileItemQuery);

			if ( null != fileEntryList && fileEntryList.size() > 0 ) {

				DLFileEntry fe = fileEntryList.get(0);

				InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null)   {	
					String firstChar = line.substring(0,1);
					if ( !Arrays.asList(commentSymbols).contains(firstChar) ) {
						output += line;
						if ( carriageReturn ) {
							output += GlobalConstants.CR;
						}
						if ( newLineFeed ) {
							output += GlobalConstants.LF;
						}							
					}
				}
				is.close();				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}
}