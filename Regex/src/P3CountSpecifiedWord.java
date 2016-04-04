import java.util.Scanner;

public class P3CountSpecifiedWord {
	
public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine().toLowerCase(); 
		String searchWord = scanner.nextLine().toLowerCase();
		
		String[] allWords = inputText.split("[\\s!',.?]");

		int wordCount = 0;
		for (int i = 0; i < allWords.length; i++) {
			
			if (allWords[i].equals(searchWord)) {
				
				wordCount++;
			}
		}
		
		System.out.println(wordCount);
	}
}
