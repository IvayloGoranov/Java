package mockito;

import java.io.File;

public class SubDirectoryExtractor implements ISubDirectoryExtractor {

	public String[] getDirectories(String currentDirectory) {
		
		File[] subDirectories = new File(currentDirectory).listFiles(File::isDirectory);
		String[] results = new String[subDirectories.length];
		for (int i = 0; i < subDirectories.length; i++) {
			
			results[i] = subDirectories[i].getName();
		}
		
		return results;
    }
}
