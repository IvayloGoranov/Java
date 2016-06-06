package logger;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import logger.interfaces.IAppender;
import logger.interfaces.ILogger;

public class Logger implements ILogger {

	private List<IAppender> appenders;
	private ReportLevel reportLevel;
    
    public Logger(IAppender... appenders) {
    	
        this.appenders = Arrays.asList(appenders);
        this.setReportLevel(ReportLevel.INFO);
    }

    public ReportLevel getReportLevel() {
		
    	return this.reportLevel;
	}

	public void setReportLevel(ReportLevel reportLevel) {
		
		this.reportLevel = reportLevel;
	}

    public Iterable<IAppender> getAppenders() {
		
    	return this.appenders;
	}

	public void info(String msg) {
		
        this.log(msg, ReportLevel.INFO);
    }

    public void warn(String msg) {
    	
        this.log(msg, ReportLevel.WARN);
    }

    public void error(String msg) {
    	
        this.log(msg, ReportLevel.ERROR);
    }

    public void critical(String msg) {
    	
        this.log(msg, ReportLevel.CRITICAL);
    }

    public void fatal(String msg) {
    	
        this.log(msg, ReportLevel.FATAL);
    }

    public void addAppender(IAppender appenderType) {
    	
        Optional<IAppender> existingAppender = this.appenders.stream().
        		filter(a -> a.getClass().getName().equals(appenderType.getClass().getName())).findFirst();
        if (!existingAppender.isPresent()) {
        	
            throw new IllegalStateException(
                String.format("The type of appender %s is already being used by the logger.", 
                		appenderType.getClass().getName()));
        }

        this.appenders.add(appenderType);
    }

    public void removeAppender(IAppender appender) {
    	
    	Optional<IAppender> existingAppender = this.appenders.stream().
        		filter(a -> a.getClass().getName().equals(appender.getClass().getName())).findFirst();
        if (!existingAppender.isPresent()) {
        	
            throw new IllegalStateException(
                String.format("No appender of type %s is currently in useby the logger.", appender.getClass().getName()));
        }

        this.appenders.remove(existingAppender.get());
    }

    private void log(String msg, ReportLevel level) {
    	
        LocalDateTime date = LocalDateTime.now();
        for (IAppender appender : this.appenders) {
        	
            if (level.getNumValue() >= this.getReportLevel().getNumValue()) {
            	
                appender.append(msg, level, date);
            }
        }
    }
}
