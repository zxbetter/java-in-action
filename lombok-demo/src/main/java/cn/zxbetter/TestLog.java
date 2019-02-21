package cn.zxbetter;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

/**
 * #8 日志对象
 *
 * CommonsLog
 * private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);
 *
 * Flogger
 * private static final com.google.common.flogger.FluentLogger log = com.google.common.flogger.FluentLogger.forEnclosingClass();
 *
 * JBossLog
 * private static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class);
 *
 * Log
 * private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());
 *
 * Log4j
 * private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class);
 *
 * Log4j2
 * private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);
 *
 * Slf4j
 * private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
 *
 * XSlf4j
 * private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);
 *
 * 1. 指定 topic = "testlog"
 * private static final Logger log = Logger.getLogger("testlog");
 */
@Slf4j
public class TestLog {
    public static void main(String[] args) {
        log.info("111");
    }
}
