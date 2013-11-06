package org.ieee.common.constants;

import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.util.*;

import org.ieee.common.bean.ContentItemBean;


public class GlobalConstants {
	
	public static final String JOURNALARTICLE   = "com.liferay.portlet.journal.model.JournalArticle";
	public static final String BLOGSENTRY       = "com.liferay.portlet.blogs.model.BlogsEntry";
	
	public static final String SESSIONOBJECT    = "SESSIONOBJECT";
	public static final String SESSIONMAP       = "SESSIONMAP";
	
	public static final String SESSIONID        = "SESSIONID";
	public static final String USERID           = "USERID";
	public static final String UUID             = "UUID";
	public static final String CONTACTID        = "CONTACTID";
	public static final String SIGNEDINLIFERAY  = "SIGNEDINLIFERAY";
	public static final String SIGNEDINSOCIAL   = "SIGNEDINSOCIAL";
	public static final String GUEST            = "GUEST";
	public static final String ACTIVE           = "ACTIVE";
	public static final String BIRTHDAY         = "BIRTHDAY";
	public static final String COMMENTS         = "COMMENTS";
	public static final String CREATEDATE       = "CREATEDATE";
	public static final String EMAILADDRESS     = "EMAILADDRESS";
	public static final String FIRSTNAME        = "FIRSTNAME";
	public static final String MIDDLENAME       = "MIDDLENAME";
	public static final String LASTNAME         = "LASTNAME";
	public static final String FULLNAME         = "FULLNAME";
	public static final String JOBTITLE         = "JOBTITLE";
	public static final String GENDER           = "GENDER";
	public static final String AVATARURL        = "AVATARURL";
	public static final String AVATARID         = "AVATARID";
	public static final String SCREENNAME       = "SCREENNAME";
	public static final String ZIPCODE          = "ZIPCODE";
	public static final String AUTHFROMEXTERNAL = "AUTHFROMEXTERNAL";
	public static final String AUTHFROM         = "AUTHFROM";
	
	public static final String SOCIALCOMMENTER  = "socialcommenter";
	public static final String ANONYMOUS        = "Anonymous";
	
	public static final String CR  = "\r";
	public static final String LF  = "\n";
	public static final String TAB = "\t";
	public static final String FF  = "\f";
	public static final String BK  = "\b";

	public static Map<Integer, ContentItemBean> contentItemMap = new HashMap<Integer, ContentItemBean>();
	
	static {		
		contentItemMap.put(new Integer(1), new ContentItemBean("ARTICLE", "Article", 1, "com.liferay.portlet.journal.model.JournalArticle"));
		contentItemMap.put(new Integer(2), new ContentItemBean("BLOGPOST", "Blog Post", 2, "com.liferay.portlet.blogs.model.BlogsEntry"));
		contentItemMap.put(new Integer(4), new ContentItemBean("PODCAST", "Podcast", 4, "com.liferay.portlet.blogs.model.BlogsEntry"));	
	}
		
	public static long[] getClassNameIds(int contentBit) {
		
		long[] classNameIds = new long[] {};

		try {
			
			List<Long> classNameList = new ArrayList<Long>();
			
			Iterator<Integer> it = contentItemMap.keySet().iterator();
			while ( it.hasNext() ) {
				Integer key = (Integer) it.next();
				ContentItemBean cib = contentItemMap.get(key);
				
				if ( (contentBit & cib.getBitValue()) == cib.getBitValue() ) {
					classNameList.add(ClassNameLocalServiceUtil.getClassNameId( cib.getClassName() ));	
				}	
			}

			if ( null != classNameList && classNameList.size() > 0 ) {
				classNameIds = new long[classNameList.size()];
				int i = 0;
				for ( Long current : classNameList ) {
					classNameIds[i] = current;
					i++;
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
				
		return classNameIds;
	}
	
	public static List<Object> getContentTypes(int contentBit, String className) {
		
		List<Object> contentTypeList = new ArrayList<Object>();
		
		try {
			
			Iterator<Integer> it = contentItemMap.keySet().iterator();
			
			if ( null != className && !"".equals(className) ) {
				
				while ( it.hasNext() ) {
					Integer key = (Integer) it.next();
					ContentItemBean cib = contentItemMap.get(key);
								
					if ( (contentBit & cib.getBitValue()) == cib.getBitValue() && cib.getClassName().equalsIgnoreCase(className) ) {
						contentTypeList.add( cib.getType().toLowerCase() );
					}
				}
				
			} else {
				
				while ( it.hasNext() ) {
					Integer key = (Integer) it.next();
					ContentItemBean cib = contentItemMap.get(key);
					
					if ( (contentBit & cib.getBitValue()) == cib.getBitValue() ) {
						contentTypeList.add( cib.getType().toLowerCase() );	
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return contentTypeList;
	}
		
}