package Project1Pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GUI {

	private JFrame frame;
	private JLabel lblSelectAnOption;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreate = new JButton("Create Data Base");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GUIdataBase1 guidb = new GUIdataBase1();
				guidb.setVisible(true);
			}
		});
		btnCreate.setBounds(24, 79, 200, 50);
		frame.getContentPane().add(btnCreate);
		
		lblSelectAnOption = new JLabel("Select an option:");
		lblSelectAnOption.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectAnOption.setBounds(24, 28, 170, 34);
		frame.getContentPane().add(lblSelectAnOption);
		
		JButton btnAdmittance = new JButton("Build Admittance Matrix");
		btnAdmittance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				GUImatrix1 guimtx = new GUImatrix1();
				guimtx.setVisible(true);
			}
		});
		btnAdmittance.setBounds(24, 162, 200, 50);
		frame.getContentPane().add(btnAdmittance);
	}
}
