import java.util.Scanner;

public class P2StringOccurences {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine().toLowerCase();
		String searchWord = scanner.nextLine().toLowerCase();

		int index = text.indexOf(searchWord);
		int counter = 0;
		while(index != -1) {
		    
			index = text.indexOf(searchWord, index + 1);
			counter++;
		}
		
		System.out.println(counter);
	}
}
