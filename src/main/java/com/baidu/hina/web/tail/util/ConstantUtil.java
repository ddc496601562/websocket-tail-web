package com.baidu.hina.web.tail.util;

public class ConstantUtil {
    public static final String CONFIG_FILE_NAME = "log.tail.properties";
    public static final String ROOT_LOG_DIR_CONFIG_KEY = "log.tail.file.root.dir";
    public static final long MB = 1024 * 1024; 
    
    
    
    // for jetty server 
    public static final String JETTY_HOST_CONFIG_KEY = "jetty.http.host" ;
    public static final String JETTY_HOST_DEFAULT = "0.0.0.0" ;
    public static final String JETTY_PORT_CONFIG_KEY = "jetty.http.port" ;
    public static final int JETTY_PORT_DEFAULT = 8013 ;
    public static final String JETTY_MIN_THREAD_CONFIG_KEY = "jetty.http.min.threads" ;
    public static final int JETTY_MIN_THREAD_DEFAULT = 20 ;
    public static final String JETTY_MAX_THREAD_CONFIG_KEY = "jetty.http.max.threads" ;
    public static final int JETTY_MAX_THREAD_DEFAULT = 20 ;
    
    public static final String LOG_TAIL_FLUSH_INTERVAL_CONFIG_KEY ="log.tail.flush.interval" ;
    public static final long LOG_TAIL_FLUSH_INTERVAL_DEFAULT = 1000 ;
    
}
