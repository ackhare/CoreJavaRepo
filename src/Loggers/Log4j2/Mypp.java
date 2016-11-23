package Loggers.Log4j2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by chetan on 23/11/16.
 */
public class Mypp {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Get instance of configuration factory; your options are default ConfigurationFactory, XMLConfigurationFactory,
        // 	YamlConfigurationFactory & JsonConfigurationFactory
        ConfigurationFactory factory = XmlConfigurationFactory.getInstance();

        // Locate the source of this configuration, this located file is dummy file contains just an empty configuration Tag
        ConfigurationSource configurationSource = new ConfigurationSource(new FileInputStream(new File("/home/chetan/IdeaProjects/CoreJavaRepo/src/Loggers/Log4j2/configuration.xml")));
        // Get context instance
        LoggerContext context = new LoggerContext("JournalDevLoggerContext");

        // Get a reference from configuration
        Configuration configuration;
        configuration = factory.getConfiguration(context, configurationSource);

        // Create default console appender
        ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());

        // Add console appender into configuration
        configuration.addAppender(appender);

        // Create loggerConfig
        LoggerConfig loggerConfig = new LoggerConfig("com", Level.FATAL, false);

        // Add appender
        loggerConfig.addAppender(appender, null, null);

        // Add logger and associate it with loggerConfig instance
        configuration.addLogger("com", loggerConfig);


        // Start logging system
        context.start(configuration);

        // Get a reference for logger
        org.apache.logging.log4j.core.Logger logger = context.getLogger("com");

        // LogEvent of DEBUG message
        logger.log(Level.FATAL, "Logger Name :: " + logger.getName() + " :: Passed Message ::");

        // LogEvent of Error message for Logger configured as FATAL
        logger.log(Level.ERROR, "Logger Name :: " + logger.getName() + " :: Not Passed Message ::");

        // LogEvent of ERROR message that would be handled by Root
        logger.getParent().log(Level.ERROR, "Root Logger :: Passed Message As Root Is Configured For ERROR Level messages");

    }
}