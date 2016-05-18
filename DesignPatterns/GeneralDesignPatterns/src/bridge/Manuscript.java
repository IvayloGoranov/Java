package bridge;

public abstract class Manuscript {

	private Formattable formatter;
	private String title;

    protected Manuscript(String title, Formattable formatter) {
    	
    	this.setTitle(title);
        this.formatter = formatter;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    public Formattable getFormatter() {
		return formatter;
	}

	public void setFormatter(Formattable formatter) {
		this.formatter = formatter;
	}

	public abstract void print();
}
