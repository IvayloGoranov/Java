package logger.interfaces;

import java.time.LocalDateTime;

import logger.ReportLevel;

public interface IAppender {

	ILayout getLayout();
	
	void setLayout(ILayout value);

	void append(String message, ReportLevel level, LocalDateTime date);
}
