package logger.interfaces;

import java.time.LocalDateTime;

import logger.ReportLevel;

public interface ILayout {

	String format(String msg, ReportLevel level, LocalDateTime date);
}
