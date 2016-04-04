import java.util.Scanner;

public class P4CountAllWords {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine().toLowerCase(); 

		String[] allWords = inputText.split("[\\s!',.?]");

		System.out.println(allWords.length);
	}
}
