package Project1Pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUImatrix1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEQmatrix;
	private JTextField textFieldSSHmatrix;
	private Complex[][] MatrixTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUImatrix1 frame = new GUImatrix1();
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
	public GUImatrix1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntroduceTheName = new JLabel("Introduce the name of the EQ file (including .xml): ");
		lblIntroduceTheName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIntroduceTheName.setBounds(36, 25, 500, 50);
		contentPane.add(lblIntroduceTheName);
		
		textFieldEQmatrix = new JTextField("Assignment_EQ_reduced.xml");
		//textFieldEQmatrix = new JTextField("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
		
		textFieldEQmatrix.setBounds(36, 89, 500, 22);
		contentPane.add(textFieldEQmatrix);
		textFieldEQmatrix.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introduce the name of the SSH file (including .xml): ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(36, 145, 500, 50);
		contentPane.add(lblNewLabel);
		
		textFieldSSHmatrix = new JTextField("Assignment_SSH_reduced.xml");
		//textFieldSSHmatrix = new JTextField("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
		
		textFieldSSHmatrix.setBounds(36, 208, 500, 22);
		contentPane.add(textFieldSSHmatrix);
		textFieldSSHmatrix.setColumns(10);
		
		JButton btnOkMatrix = new JButton("OK");
		btnOkMatrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				if(textFieldEQmatrix.getText().contains(".xml") && textFieldSSHmatrix.getText().contains(".xml")){
					
					GUIdisplay1 display = new GUIdisplay1(textFieldEQmatrix.getText(),textFieldSSHmatrix.getText());
					display.setTitle("Admittance Matrix");
					display.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnOkMatrix.setBounds(231, 269, 97, 25);
		btnOkMatrix.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnOkMatrix);
	}
}
