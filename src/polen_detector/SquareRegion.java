package polen_detector;

import java.awt.Point;

import boofcv.alg.filter.binary.Contour;

public class SquareRegion {
	public Point lower = new Point();
	public Point upper = new Point();
	public Contour originalContour;

	public SquareRegion(Point lower, Point upper) {
		this.lower = lower;
		this.upper = upper;
	}

	public SquareRegion(Contour c) {
		this.originalContour = c;
	}
	
	public SquareRegion() {
	
	}
	
	public int getHeight() {
		return this.upper.y - this.lower.y;
	}
	
	public int getWidth() {
		return this.upper.x - this.lower.x;
		
	}
	
	public int getArea() {
		return this.getHeight() * this.getWidth();
	}
	
	public void addPadding(int padding) {
		this.upper.x += padding;
		this.upper.y += padding;
		this.lower.x -= padding;
		this.lower.y -= padding;
		
	}

}
