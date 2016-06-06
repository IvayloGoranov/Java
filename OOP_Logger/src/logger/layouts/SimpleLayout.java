package logger.layouts;

import java.time.LocalDateTime;

import logger.ReportLevel;
import logger.interfaces.ILayout;

public class SimpleLayout implements ILayout {
	
	@Override
	public String format(String msg, ReportLevel level, LocalDateTime date) {
    	
        String output = String.format("%s - %s - %s\n", date.toString(), level.toString(), msg);
        
        return output;
    }
}
