package mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryTraverser {

	private ISubDirectoryExtractor subDirectoryExtractor;
	private String currentDirectory;

    public DirectoryTraverser(String directory, ISubDirectoryExtractor subDirectoryExtractor) {
    	
        this.setCurrentDirectory(directory);
        this.subDirectoryExtractor = subDirectoryExtractor;
    }

    public String getCurrentDirectory() {
		
    	return this.currentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory) {
		
		this.currentDirectory = currentDirectory;
	}

	public String[] getChildDirectories() {
		
        String[] directories = subDirectoryExtractor.getDirectories(this.getCurrentDirectory());

        List<String> directoryNames = new ArrayList<String>();
        for (String directory : directories) {
        	
            int lastBackSlash = directory.lastIndexOf("\\");
            String directoryName = directory.substring(lastBackSlash + 1);

            directoryNames.add(directoryName);
        }

        Collections.sort(directoryNames);

        return directoryNames.stream().toArray(String[]::new);
    }
}
