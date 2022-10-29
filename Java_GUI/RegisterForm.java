package clay_music_store;

import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.callback.UsernameCallback;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class RegisterForm extends JFrame implements MouseListener {

	private JPanel mainPanel, northPanel, centerPanel, southPanel, genderPanel;
	private JLabel titleRegister, lblEmailRegister, lblPasswordRegister, lblGender, lblConfirmPassword, lblusername;
	private JTextField txtEmailRegister, txtUsername;
	private JPasswordField txtPasswordRegister, txtConfirmPassword;
	private JButton btnLogin, btnRegister;
	private JRadioButton btnMale, btnFemale;
	private ButtonGroup genderGroup;
	
	public Connect con = new Connect();
	
	public RegisterForm() {
		
		 // Jpanel
        mainPanel = new JPanel(new BorderLayout(5,5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 40, 30));
        northPanel = new JPanel();
        centerPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        southPanel = new JPanel();
        
        //northPanel
        titleRegister = new JLabel("REGISTER");
        titleRegister.setFont(new Font("calibri", Font.BOLD, 35));
        northPanel.add(titleRegister);
        
        //centerPanel
        lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Calibri", Font.BOLD, 24));

        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Calibri", Font.BOLD, 24));
		txtUsername.setPreferredSize(new Dimension(150,50));
		txtUsername.setColumns(100000);
        
        lblEmailRegister = new JLabel("Email");
        lblEmailRegister.setFont(new Font("Calibri", Font.BOLD, 24));

        
        txtEmailRegister = new JTextField();
        txtEmailRegister.setFont(new Font("Calibri", Font.BOLD, 24));
		txtEmailRegister.setPreferredSize(new Dimension(150,50));
		txtEmailRegister.setColumns(100000);
		
        lblPasswordRegister = new JLabel("Password");
        lblPasswordRegister.setFont(new Font("Calibri", Font.BOLD, 24));

		
        txtPasswordRegister = new JPasswordField();
        txtPasswordRegister.setFont(new Font("Calibri", Font.BOLD, 24));
		txtPasswordRegister.setPreferredSize(new Dimension(150,50));
		txtPasswordRegister.setColumns(100000);
		
		lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(new Font("Calibri", Font.BOLD, 24));

		
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Calibri", Font.BOLD, 24));
		txtConfirmPassword.setPreferredSize(new Dimension(150,50));
		txtConfirmPassword.setColumns(100000);
		
		//gender
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Calibri", Font.BOLD, 24));

		
        genderPanel = new JPanel(); // flowlayout
        
        btnMale = new JRadioButton("Male");
        btnMale.setPreferredSize(new Dimension(75,50));
		btnMale.setFont(new Font("Calibri", Font.BOLD, 20));
		btnMale.setActionCommand("Male");
		
        btnFemale = new JRadioButton("Female");
        btnFemale.setPreferredSize(new Dimension(100,50));
		btnFemale.setFont(new Font("Calibri", Font.BOLD, 20));
		btnFemale.setActionCommand("Female");
		
        genderGroup = new ButtonGroup();
        genderGroup.add(btnFemale);
        genderGroup.add(btnMale);
        
        genderPanel.add(btnFemale);
        genderPanel.add(btnMale);

        
		centerPanel.add(lblusername);
        centerPanel.add(txtUsername);
        centerPanel.add(lblEmailRegister);
        centerPanel.add(txtEmailRegister);
        centerPanel.add(lblPasswordRegister);
        centerPanel.add(txtPasswordRegister);
        centerPanel.add(lblConfirmPassword);
        centerPanel.add(txtConfirmPassword);
        centerPanel.add(lblGender);
        centerPanel.add(genderPanel); 
        
        
        
        // southPanel
        btnRegister = new JButton("Register");
        btnRegister.setPreferredSize(new Dimension(175,40));
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 14));
        
        btnLogin = new JButton("I have an account");
        btnLogin.setPreferredSize(new Dimension(175,40));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 14));
		
		southPanel.add(btnRegister);
		southPanel.add(btnLogin);
        
        
        // main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		// Jframe
        setTitle("Clay's Music Store");
        setVisible(true);
        setSize(700, 500);
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
			this.dispose();
			new LoginForm();
		}else if (e.getSource() == btnRegister) {
			//

			String username = txtUsername.getText();
			String email = txtEmailRegister.getText();
			String password = txtPasswordRegister.getText();
			String confirmPassword = txtConfirmPassword.getText();
			String gender = genderGroup.getSelection().getActionCommand();
			int role;
			//
			
			if (confirmPassword.equals(password) && !confirmPassword.isEmpty() && !password.isEmpty() ) {
				role = 2;
				con.prepInsertUser(username, email, confirmPassword, role, gender);
				
				this.dispose();
				new LoginForm();
				
			}else if (username.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				
			}else if (email.startsWith("@") || email.endsWith("@") || !email.contains("@") || !email.contains(".")) {
				
				JOptionPane.showMessageDialog(null, "Please input a valid email!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (password.isEmpty()) {
				
				JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (confirmPassword.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Confirmation password cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (!confirmPassword.equals(password)) {
				JOptionPane.showMessageDialog(null, "Password must be same with the confirmation password!", "Alert", JOptionPane.WARNING_MESSAGE);
			}
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
