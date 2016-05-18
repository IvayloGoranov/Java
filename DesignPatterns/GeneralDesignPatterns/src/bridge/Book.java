package bridge;

public class Book extends Manuscript {

	private String author;
	private String text;
	
	public Book(String title, Formattable formatter, String author, String text) {
		
		super(title, formatter);
		this.setAuthor(author);
		this.setText(text);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void print() {
		
    	System.out.println(this.getFormatter().format("Title", this.getTitle()));
    	System.out.println(this.getFormatter().format("Author", this.getAuthor()));
    	System.out.println(this.getFormatter().format("Text", this.getText()));
        System.out.println();
	}

}
