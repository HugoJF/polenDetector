package polen_detector.contour_detectors.threshold;

import georegression.struct.point.Point2D_I32;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import polen_detector.ContourDetector;
import polen_detector.SquareRegion;
import boofcv.alg.feature.shapes.ShapeFittingOps;
import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.core.image.ConvertBufferedImage;
import boofcv.core.image.ConvertImage;
import boofcv.gui.feature.VisualizeShapes;
import boofcv.gui.image.ShowImages;
import boofcv.struct.ConnectRule;
import boofcv.struct.PointIndex_I32;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;

public class ThresholdContourDetector extends ContourDetector {

	private int threshold = -1;
	private int erode = 3;
	private int dilate = 3;
	private int maximumArea = 5000;
	private int minimumArea = 2000;
	private int padding;

	@Override
	public void run() {
		// ////////////
		// INTERFACE //
		// ////////////

		BufferedImage imageBuf = ConvertBufferedImage.convertTo(this.image, null);

		ThresholdContourDetectorInterface tcdi = new ThresholdContourDetectorInterface(this);
		ThresholdContourDetectorDisplay tcdd = new ThresholdContourDetectorDisplay(imageBuf);
		tcdd.setVisible(true);
		tcdi.setVisible(true);

		// /////////////////////
		// CONTOUR DETECTION //
		// /////////////////////

		if (this.threshold == -1)
			this.threshold = (int) ImageStatistics.mean(this.image);
		System.out.println("Image mean: " + threshold);

		while (tcdi.isVisible() || tcdd.isVisible()) {

			while(!tcdi.needsUpdate() && (tcdi.isVisible() || tcdd.isVisible())) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ImageUInt8 binary = new ImageUInt8(this.image.width, this.image.height);
			ThresholdImageOps.threshold(this.image, binary, (float) this.threshold, true);
			ImageUInt8 filtered = BinaryImageOps.erode8(binary, erode, null);
			filtered = BinaryImageOps.dilate8(filtered, dilate, null);
			List<Contour> contours = BinaryImageOps.contour(filtered, ConnectRule.EIGHT, null);
			this.contours = contours;

			// ///////////////////////////
			// SQUARE REGION DETECTION //
			// ///////////////////////////

			this.squareRegions = new ArrayList<SquareRegion>();
			for (Contour c : this.contours) {
				SquareRegion sr = new SquareRegion(c);

				sr.lower.x = c.external.get(0).x;
				sr.lower.y = c.external.get(0).y;
				sr.upper.x = c.external.get(0).x;
				sr.upper.y = c.external.get(0).y;

				for (Point2D_I32 seg : c.external) {
					if (seg.x < sr.lower.x)
						sr.lower.x = seg.x;
					if (seg.y < sr.lower.y)
						sr.lower.y = seg.y;

					if (seg.x > sr.upper.x)
						sr.upper.x = seg.x;
					if (seg.y > sr.upper.y)
						sr.upper.y = seg.y;
				}

				if (sr.getArea() < this.maximumArea && sr.getArea() > this.minimumArea) {
					this.squareRegions.add(sr);
				}
			}

			System.out.println("Detected " + this.squareRegions.size() + " square regions");

			// ////////////////////
			// UPDATE INTERFACE //
			// ////////////////////

			BufferedImage bi = new BufferedImage(imageBuf.getWidth(), imageBuf.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();

			g2.drawImage(imageBuf, 0, 0, null);

			for (SquareRegion sr : this.squareRegions) {

				sr.addPadding(this.padding);
				
				g2.setColor(Color.GREEN);
				g2.setStroke(new BasicStroke(2));
				g2.drawRect(sr.lower.x, sr.lower.y, sr.getWidth(), sr.getHeight());

				g2.setColor(Color.RED);
				g2.setStroke(new BasicStroke(2));
				List<PointIndex_I32> vertexes = ShapeFittingOps.fitPolygon(sr.originalContour.external, true, 1, Math.PI / 10, 100);
				VisualizeShapes.drawPolygon(vertexes, true, g2);
			}

			tcdd.setImage(bi);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Contour> getContours() {
		return this.contours;
	}

	public void setMinimumArea(int minimumArea) {
		this.minimumArea = minimumArea;
	}

	public void setMaximumArea(int maximumArea) {
		this.maximumArea = maximumArea;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setErode(int erode) {
		this.erode = erode;
	}

	public void setDilate(int dilate) {
		this.dilate = dilate;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

}
