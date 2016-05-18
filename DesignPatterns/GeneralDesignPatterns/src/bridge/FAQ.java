package bridge;

import java.util.LinkedHashMap;
import java.util.Map;

public class FAQ extends Manuscript {

	private LinkedHashMap<String, String> questions;
	
	protected FAQ(String title, Formattable formatter) {
		
		super(title, formatter);
		this.questions = new LinkedHashMap<String, String>();
	}
	
	public LinkedHashMap<String, String> getQuestions() {
		return questions;
	}

	public void setQuestions(LinkedHashMap<String, String> questions) {
		this.questions = questions;
	}

	@Override
	public void print() {
		
		System.out.println(this.getFormatter().format("Title", this.getTitle()));
        for (Map.Entry<String, String> question : this.questions.entrySet())
        {
            System.out.println(this.getFormatter().format("   Question", question.getKey()));
            System.out.println(this.getFormatter().format("   Answer", question.getValue()));
        }

        System.out.println();
	}

}
