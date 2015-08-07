package polen_detector.contour_detectors.threshold;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import boofcv.gui.image.ShowImages;

public class ThresholdContourDetectorDisplayJPanel extends JPanel {

	private BufferedImage image;

	/**
	 * Create the panel.
	 */
	public ThresholdContourDetectorDisplayJPanel(BufferedImage image) {
		this.image = image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		if(this.image != null) {
			g2.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());
			g2.drawImage(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), null);
			this.setBounds(0, 0, this.image.getWidth(), this.image.getHeight());
		} else {
			System.out.println("Image is null, not paiting on JPanel");
		}
	}

}
