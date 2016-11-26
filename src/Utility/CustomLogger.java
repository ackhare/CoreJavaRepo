package Utility;

import Loggers.Log4j1.log4jExample;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chetan on 26/11/16.
 */
public class CustomLogger {


    public static Object intiateLogging(Class clazz) {
        //BasicConfigurator.configure();
        org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(clazz);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("/home/chetan/IdeaProjects/CoreJavaRepo/src/Loggers/Log4j1/log4j.properties"));
        } catch (Exception e) {
            log.error(e);
        }
        PropertyConfigurator.configure(props);
        return log;
    }
}
