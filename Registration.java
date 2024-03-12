package RegistrationGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Component;

public class Registration extends JFrame {
	
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
    private JTextArea textArea;
    private JTextArea textArea_1;
    private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxNewCheckBox_1;
	//DB Connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/btesjava";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
	    
    /**
	     * Method to insert user data into the database.
    */
	    private void insertUserData(String name, String email, String password, String course) {
	        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
	            String sql = "INSERT INTO users (name, email, password, course) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement statement = conn.prepareStatement(sql)) {
	                statement.setString(1, name);
	                statement.setString(2, email);
	                statement.setString(3, password);
	                statement.setString(4, course);
	                statement.executeUpdate();
	                System.out.println("User registered successfully.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Failed to register user: " + e.getMessage());
	        }
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(232, 232, 60));
		contentPane.setBorder(UIManager.getBorder("Button.border"));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name ");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(70, 39, 68, 22);
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(177, 42, 142, 19);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setBounds(89, 208, 88, 31);
		contentPane.add(btnNewButton);
		
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                // Dispose the current frame
                dispose();

                // Open the Login frame
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            Login loginFrame = new Login();
                            loginFrame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
		
		
		
		JButton btnNewButton_1 = new JButton("Sign-up");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBackground(new Color(128, 128, 255));
		btnNewButton_1.setBounds(243, 208, 95, 31);
		contentPane.add(btnNewButton_1);
		
// Add ActionListener to the Sign-up button
		
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = textArea.getText();
                String email = textArea_1.getText();
                String password = new String(passwordField.getPassword());

  // Get selected courses
                String course = "";
                if (chckbxNewCheckBox.isSelected()) {
                    course += chckbxNewCheckBox.getText() + ", ";
                }
                if (chckbxNewCheckBox_1.isSelected()) {
                    course += chckbxNewCheckBox_1.getText() + ", ";
                }

 // Check if any course is selected
                if (course.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select at least one course.");
                    return; // Stop execution if no course is selected
                }

 // Remove trailing comma and space
                course = course.substring(0, course.length() - 2);

  // Insert user data into the database
                insertUserData(name, email, password, course);

  // Show confirmation message
                JOptionPane.showMessageDialog(null, "User registered successfully.");
            }
        });
		
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 100, 129, 19);
		contentPane.add(passwordField);
		
		
		
		
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(177, 71, 142, 19);
		contentPane.add(textArea_1);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(70, 96, 68, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(70, 71, 68, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(70, 135, 68, 19);
		contentPane.add(lblNewLabel_3);
		
		chckbxNewCheckBox = new JCheckBox("FullStack Java"); // Initialize chckbxNewCheckBox
        chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.ITALIC, 12));
        chckbxNewCheckBox.setBounds(177, 136, 108, 21);
        contentPane.add(chckbxNewCheckBox);

        chckbxNewCheckBox_1 = new JCheckBox("Selenium Testing"); // Initialize chckbxNewCheckBox_1
        chckbxNewCheckBox_1.setBackground(new Color(255, 255, 255));
        chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
        chckbxNewCheckBox_1.setBounds(287, 136, 118, 21);
        contentPane.add(chckbxNewCheckBox_1);
	}
}
