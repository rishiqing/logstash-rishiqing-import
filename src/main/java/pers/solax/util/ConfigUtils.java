package pers.solax.util;

import com.rishiqing.util.*;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by solax on 2017-4-22.
 */
public class ConfigUtils {

    private static Properties properties;

    public static String JDBC_CONNECTION_STRING;

    public static String JDBC_USER;

    public static String JDBC_PASSWORD;

    public static int JDBC_PAGE_SIZE;

    public static String HOSTS;

    static {
        properties = loadProperties("import.properties");
        generateConfig();
    }

    private static void generateConfig () {
        JDBC_CONNECTION_STRING =    properties.getProperty("jdbc_connection_string");
        JDBC_USER     =    properties.getProperty("jdbc_user");
        JDBC_PASSWORD =    properties.getProperty("jdbc_password");
        JDBC_PAGE_SIZE =   Integer.parseInt(properties.getProperty("jdbc_page_size"));
        HOSTS  =   properties.getProperty("hosts");
    }

    private  static Properties loadProperties (String path) {
        Properties config= new Properties();
        try {
            InputStream is =Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            config .load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }
}
