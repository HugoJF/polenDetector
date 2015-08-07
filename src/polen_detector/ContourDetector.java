package polen_detector;

import java.util.ArrayList;
import java.util.List;

import boofcv.alg.filter.binary.Contour;
import boofcv.struct.image.ImageFloat32;

public abstract class ContourDetector {
	protected ImageFloat32 image;
	protected List<Contour> contours;
	protected ArrayList<SquareRegion> squareRegions = new ArrayList<SquareRegion>();


	public abstract void run();
	
	public List<Contour> getContours() {
		return this.contours;
	}

	public ArrayList<SquareRegion> getSquareRegions() {
		return this.squareRegions;
	}
	
	public void setImage(ImageFloat32 image) {
		this.image = image;
	}
	
}
