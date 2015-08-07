package polen_detector;

import georegression.struct.point.Point2D_I32;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.StrokeBorder;

import polen_detector.contour_detectors.threshold.ThresholdContourDetector;
import boofcv.alg.feature.shapes.ShapeFittingOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.gui.feature.VisualizeShapes;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.PointIndex_I32;
import boofcv.struct.image.ImageFloat32;

public class PolenDetector {

	private ContourDetector contourDetector;
	private BufferedImage imageBuffered;
	private ImageFloat32 image;

	private List<Contour> contours;
	private ArrayList<SquareRegion> squareRegions = new ArrayList<SquareRegion>();
	
	public static void main(String args[]) {
		ContourDetector cd = new ThresholdContourDetector();
		BufferedImage image = UtilImageIO.loadImage("C:\\Users\\Hugo\\Dropbox\\surfExtractor\\trema_micrantha\\trema_micrantha_1_downscaled.jpg");
		System.out.println("Loaded image: " + image.getWidth() + "x" + image.getHeight());

		PolenDetector pd = new PolenDetector(cd, image);

		pd.detect();
		
		System.out.println("Gracefully exiting");
	}

	public PolenDetector(ContourDetector regionDetector, BufferedImage image) {
		this.contourDetector = regionDetector;
		this.imageBuffered = image;
		this.image = ConvertBufferedImage.convertFromSingle(image, null, ImageFloat32.class);
		this.contourDetector.setImage(this.image);
	}


	public void detect() {
		this.contourDetector.run();
	}
}
