package org.ieee.common.util;

import com.liferay.portlet.journal.model.JournalArticle;


public class ExpandoUtil {

	
	public static void setExpandoValue(JournalArticle ja, String value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Boolean value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}	
	
	public static void setExpandoValue(JournalArticle ja, Long value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Integer value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Float value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Double value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Short value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Byte value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
	
	public static void setExpandoValue(JournalArticle ja, Character value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
		
	public static void setExpandoValue(JournalArticle ja, Number value, String expandoKey) {
		
		if ( null != value ) {
			if ( ja.getExpandoBridge().hasAttribute( expandoKey ) ) {
				ja.getExpandoBridge().setAttribute( expandoKey, value );
			}
		}	
	}
}