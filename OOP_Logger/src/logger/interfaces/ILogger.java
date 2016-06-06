package logger.interfaces;

import logger.ReportLevel;

public interface ILogger {

	Iterable<IAppender> getAppenders();

    ReportLevel getReportLevel();
    
    void setReportLevel(ReportLevel reportLevel);
    
    void info(String msg);

    void warn(String msg);

    void error(String msg);

    void critical(String msg);

    void fatal(String msg);
}
