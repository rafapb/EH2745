package Project1Pack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class GUIdataBase1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEQ;
	private JLabel lblSSH;
	private JTextField textFieldSSH;
	private JLabel lblUsername;
	private JTextField textFieldUsername;
	private JLabel lblIntroduceMysqlPassword;
	private JButton btnOk;
	private JPasswordField passwordField;
	private JLabel lblIntroduceTheName;
	private JTextField textFieldDataBaseName;

	/**
	 * Create the frame.
	 */
	public GUIdataBase1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateDataBase = new JLabel("Create Data Base");
		lblCreateDataBase.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCreateDataBase.setBounds(211, 13, 143, 29);
		contentPane.add(lblCreateDataBase);
		
		JLabel lblEQ = new JLabel("Introduce the name of the EQ file (including .xml): ");
		lblEQ.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEQ.setBounds(39, 48, 500, 38);
		contentPane.add(lblEQ);
		
		textFieldEQ = new JTextField("Assignment_EQ_reduced.xml");
		//textFieldEQ = new JTextField("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
		
		textFieldEQ.setBounds(39, 99, 500, 22);
		contentPane.add(textFieldEQ);
		textFieldEQ.setColumns(10);
		
		lblSSH = new JLabel("Introduce the name of the SSH file (including .xml):");
		lblSSH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSSH.setBounds(39, 152, 500, 29);
		contentPane.add(lblSSH);
		
		textFieldSSH = new JTextField("Assignment_SSH_reduced.xml");
		//textFieldSSH = new JTextField("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
		
		textFieldSSH.setBounds(39, 194, 500, 22);
		contentPane.add(textFieldSSH);
		textFieldSSH.setColumns(10);
		
		lblUsername = new JLabel("Introduce MySQL username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(39, 251, 395, 29);
		contentPane.add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(39, 293, 500, 22);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		lblIntroduceMysqlPassword = new JLabel("Introduce MySQL password:");
		lblIntroduceMysqlPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIntroduceMysqlPassword.setBounds(39, 351, 500, 16);
		contentPane.add(lblIntroduceMysqlPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(39, 380, 500, 22);
		contentPane.add(passwordField);
		
		btnOk = new JButton("OK");
		
		btnOk.addActionListener(new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldEQ.getText().contains(".xml") && textFieldSSH.getText().contains(".xml")){
					
					MainParsing.intoSQL(textFieldEQ.getText(),textFieldSSH.getText(),textFieldUsername.getText(),String.valueOf((passwordField.getPassword())));					
					System.exit(0);
				
				}else{
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
				
		});
		
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOk.setBounds(182, 537, 200, 50);
		contentPane.add(btnOk);

	}
}
