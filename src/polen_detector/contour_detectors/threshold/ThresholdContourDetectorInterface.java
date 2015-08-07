package polen_detector.contour_detectors.threshold;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ThresholdContourDetectorInterface extends JFrame {

	private JPanel contentPane;
	private boolean needsUpdate = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThresholdContourDetectorInterface frame = new ThresholdContourDetectorInterface(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param thresholdContourDetector 
	 * @param thresholdContourDetector 
	 */
	public ThresholdContourDetectorInterface(ThresholdContourDetector tcd) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 190, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMinimumArea = new JLabel("Minimum area");
		lblMinimumArea.setBounds(10, 11, 65, 14);
		contentPane.add(lblMinimumArea);
		
		JLabel lblMaximumArea = new JLabel("Maximum area");
		lblMaximumArea.setBounds(10, 36, 69, 14);
		contentPane.add(lblMaximumArea);
		
		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setBounds(10, 61, 47, 14);
		contentPane.add(lblThreshold);
		
		JLabel lblErodes = new JLabel("Erodes");
		lblErodes.setBounds(10, 86, 33, 14);
		contentPane.add(lblErodes);
		
		JLabel lblDilate = new JLabel("Dilate");
		lblDilate.setBounds(10, 111, 27, 14);
		contentPane.add(lblDilate);
		
		JSpinner spinnerMinimumArea = new JSpinner();
		spinnerMinimumArea.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				tcd.setMinimumArea((int) spinnerMinimumArea.getValue());
				needsUpdate = true;
			}
		});
		
		JLabel lblPadding = new JLabel("Padding");
		lblPadding.setBounds(10, 136, 46, 14);
		contentPane.add(lblPadding);
		spinnerMinimumArea.setBounds(113, 8, 52, 20);
		contentPane.add(spinnerMinimumArea);
		
		JSpinner spinnerMaximumArea = new JSpinner();
		spinnerMaximumArea.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tcd.setMaximumArea((int) spinnerMaximumArea.getValue());
				needsUpdate = true;
			}
		});
		spinnerMaximumArea.setBounds(113, 33, 52, 20);
		contentPane.add(spinnerMaximumArea);
		
		JSpinner spinnerThreshold = new JSpinner();
		spinnerThreshold.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tcd.setThreshold((int) spinnerThreshold.getValue());
				needsUpdate = true;
			}
		});
		spinnerThreshold.setBounds(113, 58, 52, 20);
		contentPane.add(spinnerThreshold);
		
		JSpinner spinnerErodes = new JSpinner();
		spinnerErodes.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tcd.setErode((int) spinnerErodes.getValue());
				needsUpdate = true;
			}
		});
		spinnerErodes.setBounds(113, 83, 52, 20);
		contentPane.add(spinnerErodes);
		
		JSpinner spinnerDilate = new JSpinner();
		spinnerDilate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tcd.setDilate((int) spinnerDilate.getValue());
				needsUpdate = true;
			}
		});
		spinnerDilate.setBounds(113, 108, 52, 20);
		contentPane.add(spinnerDilate);
		
		JSpinner spinnerPadding = new JSpinner();
		spinnerPadding.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tcd.setPadding((int) spinnerPadding.getValue());
				needsUpdate = true;
			}
		});
		spinnerPadding.setBounds(113, 133, 52, 20);
		contentPane.add(spinnerPadding);
	}

	public boolean needsUpdate() {
		return this.needsUpdate;
	}
}
