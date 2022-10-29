package clay_music_store;

import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PasswordView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class LoginForm extends JFrame implements MouseListener{
	
	private JPanel mainPanel, northPanel, centerPanel, southPanel;
	private JLabel titleLogin, lblEmail, lblPassword;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnRegister;
	
	public Connect con = new Connect();
	
	//passing password
	public static Vector<String> tempPassword = new Vector<String>();
	
	public static Vector<String> getTempPassword() {
		return tempPassword;
	}


	public static void setTempPassword(Vector<String> tempPassword) {
		LoginForm.tempPassword = tempPassword;
	}
	//

	public LoginForm() {
        // Jpanel
        mainPanel = new JPanel(new BorderLayout(5,5));
        mainPanel.setBorder(new EmptyBorder(5, 20, 20, 20));
        northPanel = new JPanel();
        centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        southPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        //northPanel
        titleLogin = new JLabel("LOGIN");
        titleLogin.setFont(new Font("calibri", Font.BOLD, 35));
        northPanel.add(titleLogin);
        
        //centerPanel
        ////email
        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 24));

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Calibri", Font.BOLD, 18));
        
        //// password
        lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Calibri", Font.BOLD, 24));
		
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Calibri", Font.BOLD, 18));
        
        centerPanel.add(lblEmail);
        centerPanel.add(txtEmail);
        centerPanel.add(lblPassword);
        centerPanel.add(txtPassword);
        
        // southPanel
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(175,40));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 14));
		
        btnRegister = new JButton("I don't have an account");
        btnRegister.setPreferredSize(new Dimension(175,40));
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 14));
        
        southPanel.add(btnLogin);
        southPanel.add(btnRegister);
        
        // main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		// Jframe
        setTitle("Clay's Music Store");
        setVisible(true);
        setSize(500, 300);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // mouse listener
        btnLogin.addMouseListener(this);
        btnRegister.addMouseListener(this);

        
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnLogin ) {
			//validasi check
			String emailValid = "";
			String passwordValid = "";
			String usernameValid = "";
			
			String email = txtEmail.getText();
			String password = txtPassword.getText();
			
			int roleValid = 0;
			tempPassword.add(password);
			con.rs = con.checkLogin(email, password);
			
			try {
				if(con.rs.next()) {
					emailValid = con.rs.getString("email");
					passwordValid = con.rs.getString("password");
					roleValid = con.rs.getInt("role");
					usernameValid = con.rs.getString("username");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (email.equals(emailValid) && password.equals(passwordValid) && roleValid == 1) {
				JOptionPane.showMessageDialog(null, "Welcome, " + usernameValid, "Message", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				setVisible(false);
				new AdminMainForm();
				
			}else if (email.equals(emailValid) && password.equals(passwordValid) && roleValid == 2) {
				
				JOptionPane.showMessageDialog(null, "Welcome, " + usernameValid, "Message", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				setVisible(false);
				new UserMainForm();
				
			}else if (email.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input email !", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input password !", "Alert", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "invalid email or password !", "Alert", JOptionPane.WARNING_MESSAGE);
			}
			

		}else if (e.getSource() == btnRegister) {
			this.dispose();
			new RegisterForm();
			
			
	}
		}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
