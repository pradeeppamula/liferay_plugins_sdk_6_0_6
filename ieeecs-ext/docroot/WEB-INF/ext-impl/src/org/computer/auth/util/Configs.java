/**
 * 
 */
package org.computer.auth.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author wberks
 *
 */
public class Configs extends AbstractConfig {
	
	public static String LOGIN_URL = "";
	public static String LOGOUT_URL = "";
	public static String VALIDATION_URL = "";
	
	public static Map<String,String> LDAP_CONTEXT = Collections.emptyMap();
	public static String LDAP_USER_BASE = "";
	
	public static String CS_MEMBER_CODE = null;
	
	public static Collection<String> IGNORED_OU = new Vector<String>();

	private static final String PROPERTY_FILE = "cas_authentication_properties_2.xml";
	
	private static final String EXT_PROPERTY_FILE = "ext-properties.xml";
	
	public static String AUTHORIZATION_URL = "";

    private static Log _log = LogFactory.getLog(Configs.class);

    static {
        try {
        	_log.debug("Initialize");
            init(PROPERTY_FILE);
            
        } catch (Exception e) {
        	_log.error(e.getClass().getName() + ": " + e.getMessage());
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }
	
    /**
     * Method for creating the initializer
     * @throws java.io.IOException
     */
    public static void init(String filename) throws IOException, FileNotFoundException, Exception {
    	loadProperties(Configs.class.getClassLoader(), filename);
    	
		// Now, create the constants
		_log.debug("Create the constants");
		LOGIN_URL = props.getProperty("cas.login");
		LOGOUT_URL = props.getProperty("cas.logout");
		VALIDATION_URL = props.getProperty("cas.validate");
				
		LDAP_USER_BASE = props.getProperty("ldap.users");
		
		CS_MEMBER_CODE = props.getProperty("ldap.cs-member-code");
		
		int propCount = 0;
		try {
			propCount = Integer.parseInt(props.getProperty("ldap.entry-count"));
			_log.debug("Found " + propCount + " ldap entries");
		} catch (Exception e) {
			throw new Exception("Entry count invalid: " + e.getMessage());
		}
		
		LDAP_CONTEXT = new HashMap<String, String>(propCount);
		
		for (int count = 1; count <= propCount; count++) {
			if (_log.isDebugEnabled()) {
				String key = props.getProperty("ldap." + count + ".key");
				String value = props.getProperty("ldap." + count + ".value");
				_log.debug("\t" + key + ": " + value);
			}
			LDAP_CONTEXT.put(props.getProperty("ldap." + count + ".key"), 
                             props.getProperty("ldap." + count + ".value"));
		}
		
		String[] vals = props.getProperty("ldap.organizationalUnits.ignored").split(",");
		for (String val : vals)
			IGNORED_OU.add(val);
		
		loadProperties(Configs.class.getClassLoader(), EXT_PROPERTY_FILE);
		AUTHORIZATION_URL = props.getProperty("authorization.url");
		_log.info("Authorization URL - " + AUTHORIZATION_URL);
	    return;
    }
    
}
