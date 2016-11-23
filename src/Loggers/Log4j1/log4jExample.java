package Loggers.Log4j1;

/**
 * Created by chetan on 23/11/16.
 */





import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class log4jExample {

    /* Get actual class name to be printed on */

    private static Logger log = Logger.getLogger(log4jExample.class);

    public static void main(String[] args) throws IOException, SQLException {
        //BasicConfigurator.configure();
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("/home/chetan/IdeaProjects/CoreJavaRepo/src/Loggers/Log4j1/log4j.properties"));
        } catch (Exception e) {
            log.error(e);
        }
        PropertyConfigurator.configure(props);
        System.out.println("dmnvlksdjvklsjv");
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        log.error("mmmmmmmmmmmmmmmmmmmmm");
    }
}
