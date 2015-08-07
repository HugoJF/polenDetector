package polen_detector.contour_detectors.threshold;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ThresholdContourDetectorDisplay extends JFrame {

	private JPanel contentPane;
	private ThresholdContourDetectorDisplayJPanel tcddPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThresholdContourDetectorDisplay frame = new ThresholdContourDetectorDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThresholdContourDetectorDisplay() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ThresholdContourDetectorDisplayJPanel panel = new ThresholdContourDetectorDisplayJPanel(null);
		panel.setBounds(10, 11, 414, 240);
		contentPane.add(panel);
	}

	public ThresholdContourDetectorDisplay(BufferedImage image) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, image.getWidth(), image.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tcddPanel = new ThresholdContourDetectorDisplayJPanel(image);
		tcddPanel.setBounds(0, 0, image.getWidth(), image.getHeight());
		contentPane.add(tcddPanel);
		
	}
	
	public void setImage(BufferedImage image) {
		tcddPanel.setImage(image);
		tcddPanel.repaint();
	}
}
