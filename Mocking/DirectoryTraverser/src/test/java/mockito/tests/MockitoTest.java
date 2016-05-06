package mockito.tests;

import org.junit.Test;
import org.mockito.Mockito;

import mockito.DirectoryTraverser;
import mockito.ISubDirectoryExtractor;
import mockito.SubDirectoryExtractor;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockitoTest {

	@Test
	public void getChildDirectories_ShouldReturnDirectoryNames()  {

	  //Create mock.
	  ISubDirectoryExtractor mockedDirectoryExtarctor = Mockito.mock(SubDirectoryExtractor.class);
	  
	  //Define return value for method getDirectories()
	  when(mockedDirectoryExtarctor.getDirectories(anyString())).
	  thenReturn(new String[] { "C:\\test\\dir3", "C:\\test\\dir1", "C:\\test\\dir2" });
	  
	  
	  //Use mock in test.... 
	  String startDir = "C:\\test";
	  DirectoryTraverser dirTraverser = new DirectoryTraverser(startDir, mockedDirectoryExtarctor);
	  String[] actualSubDirectoryNames = dirTraverser.getChildDirectories();
	  String[] expectedSubDirectoryNames = new String[] { "dir1", "dir2", "dir3" };
	  
	  assertArrayEquals(actualSubDirectoryNames, expectedSubDirectoryNames);
	}
}
