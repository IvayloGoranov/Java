package logger.appenders;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import logger.ReportLevel;
import logger.interfaces.ILayout;

public class FileAppender extends Appender {
	
    private String filePath;

    public FileAppender(String filePath, ILayout layout) {
    	
        super(layout);
    	this.setFilePath(filePath);
    }

    public String getFilePath() {
		
    	return this.filePath;
	}

	public void setFilePath(String value) {
		
		if (value == null || value.equals("")) {
			
            throw new IllegalArgumentException("File path cannot be empty.");
        }

        this.filePath = value;
	}

	@Override
	public void append(String message, ReportLevel level, LocalDateTime date) {
		
        String output = this.getLayout().format(message, level, date);
        File outputFile = new File(this.getFilePath());
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
			
        	writer.println(output);
		} catch (Exception ioe) {
			
			System.out.println(ioe.toString());
		}

    }
}
