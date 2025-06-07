package com.nextZen.Framework.LoggerUtility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger();

    /*
    Usage of Logging Levels:
    info() → General information
    warn() → Warnings
    error() → Errors
    debug() → Debugging details
    */

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
