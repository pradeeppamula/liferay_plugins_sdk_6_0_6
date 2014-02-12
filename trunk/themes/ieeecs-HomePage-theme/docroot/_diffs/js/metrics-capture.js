/*!
 * IEEECS Metrics JavaScript SDK
 * Version: 0.1.1
 * http://www.computer.org
 *
 * Copyright 2013 IEEE Computer Society
 * The IEEECS Metrics JavaScript SDK is freely distributable under the MIT license.
 */
(function( window, undefined ) {
	var Metrics = {};
  Metrics.VERSION = "0.1.1";
	Metrics.ENDPOINT_URL = '';
	Metrics.DEBUG_MODE = false;
	
  // make sure console is available
  if (!window.console) window.console = {};
  if (!window.console.log) window.console.log = function () {};
	if (!window.console.warn) window.console.warn = function () {};
	if (!window.console.error) window.console.error = function () {};
	
	// check for socket io, if not present, throw error and exit
	if(!window.io) { throw new Error("Socket.io is required to utilize the Metrics capture application."); return; }
	
  /**
   * Contains functions to deal with capturing metric data
   * @name Metrics.Capture
   */
  Metrics.Capture = Metrics.Capture || {};
	
  /**
   * Function that will help determine if the parameter is 
	 * a function.
	 * @param optionsOrCallback {}
   * @name isFunction
   */
  function isFunction(functionToCheck) {
		var getType = {};
		return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
	}
	
  /**
   * Initializes the Capture object for the Metric API.  As of now
	 * it simply sets the ENDPOINT_URL.
	 * @param {Object} options - The initalization options
   */
  Metrics.Capture.init = function(options) {
		Metrics.ENDPOINT_URL = options.url || 'http://localhost:5557';
		Metrics.DEBUG_MODE = options.debug || false;
		Metrics.debug("Initializing Metrics SDK.");
		Metrics.debug("Metrics Server Endpoint URL: " + Metrics.ENDPOINT_URL);
  };
	
  /**
   * Logs the passed in message to the console.
	 * @param {String} message - The log message
   */
	Metrics.debug = function(message) {
		if(Metrics.DEBUG_MODE) { console.log(message); }
	}
	
  /**
   * Run the given callbacks 
   * @param optionsOrCallback {} A options object or a
   * callback function. 
   */
  Metrics.runCallbacks = function(optionsOrCallback) {
    var options;
    if (isFunction(optionsOrCallback)) {
      return optionsOrCallback;
    } else {
      options = optionsOrCallback;
    }
    options = options || {};
		return options;
  };

  /**
   * Formats the metric data that will be sent to the Metric API.
	 * @param {String} name - The name of the event.
   * @param {Object} data -  The data of the client metrics.  
   */
  Metrics.Capture.format = function(name, data) {
		// add necessary data to json that is needed for analytics
		var cleanData = {};
		cleanData.eventName = name;
		cleanData.data = data;
		Metrics.debug("Capturing event: " + JSON.stringify(cleanData, null, 4));
    return cleanData;
  };

  /**
   * Sends the metric data to the Metric API.
	 * @param {String} name - The name of the event.
   * @param {Object} data -  The data of the client metrics.  
   * @param {Object} options An object that has an optional success function,
   * that takes no arguments and will be called on a successful capture, and
   * an error function that takes a Metrics.Error and will be called if the capture
   * failed.
   */
  Metrics.Capture.capture = function(name, data, options) {		
    options = options || {};
		name = name || 'generic';
		var socket = io.connect(Metrics.ENDPOINT_URL);
    socket.emit('capture', Metrics.Capture.format(name, data));
    return Metrics.runCallbacks(options);
  };
	
	// If there is a window object, that at least has a document property, define the Metrics object
	if ( typeof window === "object" && typeof window.document === "object" ) {
		window.Metrics = Metrics.Capture;
	}
})( window );