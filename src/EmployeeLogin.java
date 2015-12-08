import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EmployeeLogin {

	private JFrame loginFrame;
	private JTextField txtUserName;
	private JPasswordField pswPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin window = new EmployeeLogin();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	
	/**
	 * Create the application.
	 */
	public EmployeeLogin() {
		initialize();
		connection  = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.getContentPane().setForeground(Color.LIGHT_GRAY);
		loginFrame.getContentPane().setBackground(null);
		loginFrame.setBounds(300, 200, 500, 400);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 95, 403, 237);
		loginFrame.getContentPane().add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(132, 77, 242, 26);
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setForeground(Color.LIGHT_GRAY);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Log In");
		lblNewLabel_1.setBounds(30, 23, 84, 26);
		lblNewLabel_1.setFont(new Font("Hiragino Sans", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "Select * from EmployeeInfo where username=? and password=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, txtUserName.getText());
					pst.setString(2, pswPassword.getText());
					ResultSet rs =pst.executeQuery();
					int counter=0;
					while(rs.next()){
						counter++;
					}
					if(counter>=1){
						JOptionPane.showMessageDialog(null,"Login Successfully");
						loginFrame.dispose();
						CoffeePOS coffeeapp = new CoffeePOS();
						coffeeapp.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null,"Invalid username or password");
					}
					rs.close();
					pst.close();
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		btnLogin.setBounds(257, 183, 117, 29);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(new Color(51, 102, 153));
		panel_1.add(btnLogin);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setBounds(30, 76, 92, 29);
		panel_1.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(30, 115, 92, 29);
		panel_1.add(lblPassword);
		
		pswPassword = new JPasswordField();
		pswPassword.setBounds(132, 119, 242, 25);
		panel_1.add(pswPassword);
		
		JLabel lblNewLabel = new JLabel("Expresso Login");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(87, 6, 344, 73);
		loginFrame.getContentPane().add(lblNewLabel);
		lblNewLabel.setForeground(new Color(51, 102, 153));
		lblNewLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 45));
	}
}
