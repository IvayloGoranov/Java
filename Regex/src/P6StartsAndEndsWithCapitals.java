import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P6StartsAndEndsWithCapitals {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine(); 
		
		String regexMatchPattern = "[A-Z][a-z0-9A-Z]*[A-Z]";
		Pattern emailPattern = Pattern.compile(regexMatchPattern);
		Matcher matcher = emailPattern.matcher(inputText);
		
		while (matcher.find()) {
			
			System.out.print(matcher.group() + " ");
		}
	}
}
