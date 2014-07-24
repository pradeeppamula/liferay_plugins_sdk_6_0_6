/**
 * 
 */
package org.computer.auth.util;

import java.util.Hashtable;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;


/**
 * @author wberks
 *
 */
public class LDAPInitialDirContextFactory {
	
	public static InitialDirContext getDefaultIntialDirContext() throws InvalidUserException {
		Hashtable<String,String> env = new Hashtable<String,String>(Configs.LDAP_CONTEXT.size());
		env.putAll(Configs.LDAP_CONTEXT);
		env.put("javax.security.auth.useSubjectCredsOnly", "false");
		return buildInitialDirContext(env);
	}
	
	public static InitialDirContext getInitialDirContext(String principal, String credentials)
		throws InvalidUserException {
		
        if(principal == null)
            throw new InvalidUserException("Null user name provided.");
        if(principal.length() == 0)
            throw new InvalidUserException("Empty user name provided.");
        if(credentials == null)
            throw new InvalidUserException("No credentials provided.");
        if(credentials.length() == 0)
            throw new InvalidUserException("Empty credentials provided.");
        
        else
        {
            Hashtable<String,String> env = new Hashtable<String,String>(Configs.LDAP_CONTEXT.size()+2);
            env.putAll(Configs.LDAP_CONTEXT);
            env.put("java.naming.security.principal", principal);
            env.put("java.naming.security.credentials", credentials);
            return buildInitialDirContext(env);
        }
	}
	
	private static InitialDirContext buildInitialDirContext(Hashtable env)
        throws InvalidUserException
    {
        try {
        	return new InitialDirContext(env);
        }
        catch (javax.naming.AuthenticationException ax) {
        	throw new InvalidUserException("LDAP authentication failed.", ax);
        }
        catch (NamingException nx) {
        	throw new InvalidUserException("Unable to connect to LDAP Server; check LDAP configuration", nx);
        }
    }

	static {
		System.setProperty("javax.security.auth.useSubjectCredentialsOnly", "false");
	}
}