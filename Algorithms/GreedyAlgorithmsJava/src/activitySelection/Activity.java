package activitySelection;

public class Activity {
	
	private int start;
	private int finish;
	
	public Activity(int start, int finish) {
		
		this.start = start;
		this.finish = finish;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	@Override
	public String toString() {
		return "[start=" + start + ", finish=" + finish + "]";
	}
}
