package bridge;

import java.util.ArrayList;
import java.util.List;

public class BridgeExample {

	public static void main(String[] args) {
		
		List<Manuscript> documents = new ArrayList<Manuscript>();
        Formattable formatter = new StandardFormatter();

        FAQ faq = new FAQ("The Bridge Pattern FAQ", formatter);
        faq.getQuestions().put("What is it?", "A design pattern");
        faq.getQuestions().put("When do we use it?", "When you need to separate an abstraction from an implementation.");
        documents.add(faq);

        Book book = new Book("Lots of Patterns", formatter, "John Sonmez", "Blah blah blah...");
        documents.add(book);

        for (Manuscript doc : documents) {
        	
            doc.print();
        }
	}
}
