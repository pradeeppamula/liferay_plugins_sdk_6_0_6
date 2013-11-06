/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.mongo
 * @created July 15, 2013
 * @description This class will handle all utility type functionality for 
 * 				the MongoHandler.
 */
package org.ieeecs.communities.mongo;

public class MongoConfigUtil {
	// connection configurations
    public final static String CONFIG_FILE_PATH = "config.json";
    public final static String CONFIG_PROP_MONGOURI = "mongoURI";
    public final static String CONFIG_PROP_ENVIRONMENT = "environment";
    public final static String CONFIG_PROP_CONNECTION_MODE = "connectionMode";

    // database names
	public final static String DB_NAME_CSDLBUNDLE_DEV = "csdlbundle_dev";
    public final static String DB_NAME_METRICS = "cs_metrics";
	
	// collection names
	public final static String COLLECTION_PURCHASE = "purchase";
	public final static String COLLECTION_PORTLET_META_DATA = "portletMetaData";
    public final static String COLLECTION_METRIC = "metric";
    public final static String COLLECTION_METRIC_ACTION = "metricAction";
    public final static String COLLECTION_ORGANIZATION = "organization";

    /**
     * This enum will contain the collections and their
     * corresponding databases
     */
    public enum Collection {
        PURCHASE(COLLECTION_PURCHASE, DB_NAME_CSDLBUNDLE_DEV),
        ORGANIZATION(COLLECTION_ORGANIZATION, DB_NAME_CSDLBUNDLE_DEV),
        PORTLET_META_DATA(COLLECTION_PORTLET_META_DATA, DB_NAME_CSDLBUNDLE_DEV),
        METRIC(COLLECTION_METRIC,DB_NAME_METRICS),
        METRIC_ACTION(COLLECTION_METRIC_ACTION,DB_NAME_METRICS);

        public final String collectionName;
        public final String databaseName;

        private Collection(final String collectionName, final String databaseName) {
            this.collectionName = collectionName;
            this.databaseName = databaseName;
        }

        public String getCollectionName() {
            return this.collectionName;
        }

        public String getDatabaseName() {
            return this.databaseName;
        }
    }

    /**
     * This helper method will build up the mongo uri
     * @param baseURI
     * @param database
     * @return  String
     */
	public static String getConstructedURI(String baseURI, String database) {
		StringBuilder uri = new StringBuilder(baseURI);
		uri.append(database);
		return uri.toString();
	}
}
