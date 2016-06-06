package logger.layouts;

import java.time.LocalDateTime;

import logger.ReportLevel;
import logger.interfaces.ILayout;

public class XMLLayout implements ILayout {

	@Override
	public String format(String msg, ReportLevel level, LocalDateTime date) {

		StringBuilder output = new StringBuilder();
        output.append("<log>" + "\n");
        output.append("<date>" + date + "</date>" + "\n");
        output.append("<level>" + level + "</level>" + "\n");
        output.append("<message>" + msg + "</message>" + "\n");
        output.append("</log>");

        return output.toString();
	}

}
