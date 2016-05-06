package mockito;

public class TraverserMain {
	
	public static void main(String[] args) {
		
		ISubDirectoryExtractor subDirectoryExtractor = new SubDirectoryExtractor();
        DirectoryTraverser traverser = new DirectoryTraverser("C:\\", subDirectoryExtractor);

        String[] children = traverser.getChildDirectories();
        for (String child : children) {
        	
            System.out.println(child);
        }

        System.out.println(traverser.getCurrentDirectory());
	}
}
