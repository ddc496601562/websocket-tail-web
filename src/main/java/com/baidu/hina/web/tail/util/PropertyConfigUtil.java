package com.baidu.hina.web.tail.util;

import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_HOST_CONFIG_KEY;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_HOST_DEFAULT;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_MAX_THREAD_CONFIG_KEY;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_MAX_THREAD_DEFAULT;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_MIN_THREAD_CONFIG_KEY;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_MIN_THREAD_DEFAULT;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_PORT_CONFIG_KEY;
import static com.baidu.hina.web.tail.util.ConstantUtil.JETTY_PORT_DEFAULT;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * load property util
 *
 * @author baidu
 *
 */
public class PropertyConfigUtil {
    private static final Properties properties;
    static {
        properties = PropertyConfigUtil.loadPropertyFromClasspathFile(ConstantUtil.CONFIG_FILE_NAME);
    }

    public static Properties getPropertiesInstance() {
        return properties;
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String getHost() {
        return PropertyConfigUtil.getString(properties, JETTY_HOST_CONFIG_KEY, JETTY_HOST_DEFAULT);
    }

    public static int getPort() {
        return PropertyConfigUtil.getInt(properties, JETTY_PORT_CONFIG_KEY, JETTY_PORT_DEFAULT);
    }

    public static int getMinThreads() {
        return PropertyConfigUtil.getInt(properties, JETTY_MIN_THREAD_CONFIG_KEY, JETTY_MIN_THREAD_DEFAULT);
    }

    public static int getMaxThreads() {
        return PropertyConfigUtil.getInt(properties, JETTY_MAX_THREAD_CONFIG_KEY, JETTY_MAX_THREAD_DEFAULT);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }
    
    public static long getLong(Properties properties, String key, long defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    private static Properties loadPropertyFromClasspathFile(String fileName) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = PropertyConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("load property file error, fileName={}" + fileName);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.err.println("close InputStream error, ");
                }
            }
        }
        return properties;
    }
}
