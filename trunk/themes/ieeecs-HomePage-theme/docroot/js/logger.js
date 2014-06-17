/*!
 * IEEECS Logger JavaScript SDK
 * Version: 0.1.1
 * http://www.computer.org
 *
 * Copyright 2014 IEEE Computer Society
 */
(function( window, undefined ) {
	var Logger = {};
  Logger.VERSION = "0.1.1";
	Logger.ENDPOINT_URL = '';
	Logger.DEBUG_MODE = false;
	
  // make sure console is available
  if (!window.console) window.console = {};
  if (!window.console.log) window.console.log = function () {};
	if (!window.console.warn) window.console.warn = function () {};
	if (!window.console.error) window.console.error = function () {};

	// check for jQuery, if not present, throw error and exit
	if(!window.jQuery) { throw new Error("jQuery is required to utilize the Logger."); return; }
			
  /**
   * Contains functions to deal with capturing log data
   * @name Logger.Capture
   */
  Logger.Log = Logger.Log || {};
	
  /**
   * Initializes the Logger object for the Logger API.  As of now
	 * it simply sets the ENDPOINT_URL.
	 * @param {Object} options - The initalization options
   */
  Logger.Log.init = function(options) {
		Logger.ENDPOINT_URL = options.url || 'http://localhost:5556/services/logger/';
		Logger.DEBUG_MODE = options.debug || false;
		Logger.debug("Initializing Logger SDK.");
		Logger.debug("Logger Server Endpoint URL: " + Logger.ENDPOINT_URL);
  };
	
  /**
   * Logs the passed in message to the console.
	 * @param {String} message - The log message
   */
	Logger.debug = function(message) {
		if(Logger.DEBUG_MODE) { console.log(message); }
	}
	
  /**
   * Formats the log data that will be sent to the Logger API.
	 * @param {String} name - The log type.
   * @param {Object} data -  The data of the client logs.  
   */
  Logger.Log.format = function(name, data) {
		// add necessary data to json that is needed for logging analytics
		var cleanData = {};
		cleanData.logType = name;
		cleanData.data = data;
		Logger.debug("Logging data: " + JSON.stringify(cleanData, null, 4));
    return cleanData;
  };

  /**
   * Sends the "info" log data to the Logger API.
   * @param {Object} data -  The data of the client logs.  
   */
  Logger.Log.info = function(data) {
    if(data == undefined) return;
		$.post(Logger.ENDPOINT_URL, Logger.Log.format("[INFO]", data));
  };
	
  /**
   * Sends the "error" log data to the Logger API.
   * @param {Object} data -  The data of the client logs.  
   */
  Logger.Log.error = function(data) {
    if(data == undefined) return;
		$.post(Logger.ENDPOINT_URL, Logger.Log.format("[ERROR]", data));
  };
	
	// If there is a window object, that at least has a document property, define the Log object
	if ( typeof window === "object" && typeof window.document === "object" ) {
		window.Log = Logger.Log;
	}
})( window );