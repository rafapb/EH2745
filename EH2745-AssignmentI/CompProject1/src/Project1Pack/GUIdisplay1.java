package Project1Pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class GUIdisplay1 extends JFrame {

	protected static final String EQ = null;
	protected static final String SSH = null;
	private JPanel contentPane;
	private Complex[][] MatrixTable;
	private String[] column;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIdisplay1 frame = new GUIdisplay1(EQ, SSH);
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
	public GUIdisplay1(String EQ, String SSH) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		MatrixTable = Ybus.yBusMaking(EQ,SSH);
		
		column = new String[MatrixTable[0].length];
		for(int i=0; i < MatrixTable[0].length; i++){
			column[i] = "Bus " + String.valueOf(i+1) + " [S]";
		}
		table = new JTable(MatrixTable,column);
		table.setBounds(10, 11, 675, 144);
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		contentPane.add(scroll);
		
	}

}
