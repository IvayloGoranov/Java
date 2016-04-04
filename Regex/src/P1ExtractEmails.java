import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P1ExtractEmails {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine(); 
		
		String regexMatchPattern = "[\\w]+[.\\-_]?[\\w]+@[\\w]+[.\\-_]?[\\w]+\\.[A-Za-z]+\\.?[A-Za-z]+";
		Pattern emailPattern = Pattern.compile(regexMatchPattern);
		Matcher matcher = emailPattern.matcher(inputText);
		
		while (matcher.find()) {
			
			System.out.println(matcher.group());
		}
	}
}
