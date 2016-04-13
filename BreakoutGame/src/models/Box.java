package models;

public class Box {
	
	public static final int BOX_SIZE = 20;
	
	private int x;
	private int y;
	private boolean isVisible;
	
	public Box(int x, int y) {
		
		this.setX(x);
		this.setY(y);  
		this.isVisible = true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isVisible() {
		
		return this.isVisible;
	}

	public void setVisible(boolean isVisible) {
		
		this.isVisible = isVisible;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Box other = (Box) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
