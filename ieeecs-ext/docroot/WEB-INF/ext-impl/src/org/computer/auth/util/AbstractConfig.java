/**
 * 
 */
package org.computer.auth.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author wberks
 *
 */
public abstract class AbstractConfig {

    protected static Properties props;
    
    /**
     * @param propertyFilename
     * @throws IOException
     * @throws FileNotFoundException
     * 
     * @deprecated
     */
    protected static void loadProperties(String propertyFilename) throws IOException, FileNotFoundException {
    	try {
        	// Open the property file
        	InputStream is = AbstractConfig.class.getClassLoader().getResourceAsStream(propertyFilename);
        	if (is == null) {
        		String errorMessage = propertyFilename + " not found in the classpath.";
        		_log.error(errorMessage);
        		throw new FileNotFoundException(errorMessage);
        	}
        	else if (_log.isDebugEnabled()) {
        		_log.debug("Property file '" + propertyFilename + "' opened");
        	}
            props = new Properties();
            props.loadFromXML(is);
            
            is.close();
        } catch (IOException e) {
        	_log.error("Unable to load properties from '" + propertyFilename + "', " + e.getMessage());
            throw e;
        }
        throw new RuntimeException("This method is deprecated.");
//        return;
    }

    /**
     * Overloaded method which uses caller's classloader, allows utility project/jar to reside outside
     * caller's classloader scope
     * @author cshah
     * @param classloader         classloader caller wants this method to use
     * @param propertyFilename    property file name to load
     */
    protected static void loadProperties(ClassLoader classloader, String propertyFilename) throws IOException, FileNotFoundException {
    	try {
        	// Open the property file
        	InputStream is = classloader.getResourceAsStream(propertyFilename);
        	if (is == null) {
        		String errorMessage = propertyFilename + " not found in the classpath.";
        		throw new FileNotFoundException(errorMessage);
        	}
        	else if (_log.isDebugEnabled()) {
        		_log.debug("Property file '" + classloader.getResource(propertyFilename).toExternalForm() + "' opened");
        	}
            props = new Properties();
            props.loadFromXML(is);
            
            if (_log.isDebugEnabled()) {
            	_log.debug("Properties:\n" + props.toString());
            }
            
            is.close();
        } catch (IOException e) {
            throw e;
        }
        return;
    }

    
    private final static Log _log = LogFactory.getLog(AbstractConfig.class);

}
