package RegistrationGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField passwordField;

	
    private static final String DB_URL = "jdbc:mysql://localhost:3306/btesjava";
    private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(232, 232, 60));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(190, 74, 150, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(190, 142, 150, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(90, 68, 76, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(90, 139, 90, 19);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("LOGIN Now");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(175, 191, 126, 47);
		contentPane.add(btnNewButton);
		
		
		
		
		 btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String email = txtEmail.getText();
	                String password = new String(passwordField.getPassword());

	                // Validate login credentials
	                if (validateLogin(email, password)) {
	                    JOptionPane.showMessageDialog(null, "Login successful!");
	                    // Perform necessary action after successful login (e.g., open a new window)
	                } else {
	                    JOptionPane.showMessageDialog(null, "Invalid login details. Please try again.");
	                }
	            }
	        });
	    }

	    /**
	     * Method to validate login credentials by querying the database.
	     */
	    private boolean validateLogin(String email, String password) {
	        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	            try (PreparedStatement statement = conn.prepareStatement(sql)) {
	                statement.setString(1, email);
	                statement.setString(2, password);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    return resultSet.next(); // Return true if there is at least one matching row
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false; // Return false in case of an exception
	        }
	}

}
