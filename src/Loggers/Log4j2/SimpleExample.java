package Loggers.Log4j2;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by chetan on 23/11/16.
 */
public class SimpleExample {
    public static void main( String[] args ) {
//        Properties props=new  Properties();
//        props.setProperty("log4j.configurationFile","/home/chetan/IdeaProjects/CoreJavaRepo/src/Loggers/Log4j2/configuration.xml");
//        System.setProperties(props);
        Logger logger = LogManager.getRootLogger();
        logger.trace("Configuration File Defined To Be :: "+System.getProperty("log4j.configurationFile"));
        logger.error("hi biju");
    }
}
