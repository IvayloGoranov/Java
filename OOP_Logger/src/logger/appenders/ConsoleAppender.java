package logger.appenders;

import java.time.LocalDateTime;

import logger.ReportLevel;
import logger.interfaces.ILayout;

public class ConsoleAppender extends Appender {

	public ConsoleAppender(ILayout layout) {
		
		super(layout);
	}

	@Override
	public void append(String message, ReportLevel level, LocalDateTime date) {
		
		String output = this.getLayout().format(message, level, date);
        System.out.println(output);
	}

}
