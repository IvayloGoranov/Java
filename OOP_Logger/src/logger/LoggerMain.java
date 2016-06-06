package logger;

import logger.appenders.ConsoleAppender;
import logger.appenders.FileAppender;
import logger.interfaces.IAppender;
import logger.interfaces.ILayout;
import logger.interfaces.ILogger;
import logger.layouts.SimpleLayout;
import logger.layouts.XMLLayout;

public class LoggerMain {

	public static void main(String[] args) {
		
		ILayout simpleLayout = new SimpleLayout();

        IAppender consoleAppender = new ConsoleAppender(simpleLayout);
        String filePath = "log.txt";
        IAppender fileAppender = new FileAppender(filePath, simpleLayout);

        ILogger logger = new Logger(consoleAppender, fileAppender);
        logger.error("Error parsing JSON.");
        logger.info(String.format("User %s successfully registered.", "Pesho"));
        logger.warn("Warning - missing files.");

        System.out.println();

        ILayout xmlLayout = new XMLLayout();
        consoleAppender.setLayout(xmlLayout);

        logger.fatal("mscorlib.dll does not respond");
        logger.critical("No connection string found in App.config");

        System.out.println();

        consoleAppender.setLayout(simpleLayout);
        logger.setReportLevel(ReportLevel.CRITICAL);
        logger.info("Everything seems fine");
        logger.warn("Warning: ping is too high - disconnect imminent");
        logger.error("Error parsing request");
        logger.critical("No connection string found in App.config");
        logger.fatal("mscorlib.dll does not respond");
	}
}
