package Loggers.Log4j;

/**
 * Created by chetan on 23/11/16.
 */

import org.apache.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.FileAppender;


import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class log4jExample{

    /* Get actual class name to be printed on */
    private  static Logger log = Logger.getLogger(log4jExample.class.getName());

    public static void main(String[] args)throws IOException,SQLException{
//        Enumeration e = Logger.getRootLogger().getAllAppenders();
//    while (e.hasMoreElements()){
//      Appender app = (Appender)e.nextElement();
//     if ( app instanceof FileAppender){
//    System.out.println("File: " + ((FileAppender)app));
//  }}

        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        log.warn("mmmmmmmmmmmmmmmmmmmmm");
    }
}
