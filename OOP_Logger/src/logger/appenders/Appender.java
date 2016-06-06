package logger.appenders;

import java.time.LocalDateTime;

import logger.ReportLevel;
import logger.interfaces.IAppender;
import logger.interfaces.ILayout;

public abstract class Appender implements IAppender {
	
    private ILayout layout;
    
    public Appender(ILayout layout) {
    	
        this.setLayout(layout);
    }


    public ILayout getLayout() {
		
    	return this.layout;
	}

	public void setLayout(ILayout value) {
		
		if (value == null) {
			
            throw new NullPointerException("Layout cannot be null");
        }

        this.layout = value;
	}

	public abstract void append(String message, ReportLevel level, LocalDateTime date);
}
