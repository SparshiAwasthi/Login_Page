package cs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class MyFrame extends JFrame implements ActionListener
{
	Container c;
	JLabel label1,label2;
	JTextField user;
	JPasswordField pass;
	JButton login_btn,reset_btn,exit_btn;
	
	MyFrame()
	{
		setTitle("Login Page");
		setSize(500,350);
		setLocation(100,100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		
		label1 = new JLabel("Username");
		label2 = new JLabel("Password");
		label1.setBounds(100,50,100,20);
		label2.setBounds(100,110,100,20);
		label1.setFont(new Font("Arial", Font.BOLD, 17));
		label2.setFont(new Font("Arial", Font.BOLD, 17));
		c.add(label1);
		c.add(label2);
		
		user = new JTextField();
		user.setBounds(230,50,190,30);
		c.add(user);
		pass = new JPasswordField();
		pass.setBounds(230,110,190,30);
		c.add(pass);
		
		login_btn = new JButton("Login");
		login_btn.setFont(new Font("Arial", Font.BOLD, 14));
		login_btn.setBounds(90,190,90,37);
		c.add(login_btn);
		reset_btn = new JButton("Reset");
		reset_btn.setFont(new Font("Arial", Font.BOLD, 14));
		reset_btn.setBounds(220,190,90,37);
		c.add(reset_btn);
		exit_btn = new JButton("Exit");
		exit_btn.setFont(new Font("Arial", Font.BOLD, 14));
		exit_btn.setBounds(350,190,90,37);
		c.add(exit_btn);
		
		addActionListener();
		setVisible(true); 
	}
	public void addActionListener()
	{
		login_btn.addActionListener(this);
		reset_btn.addActionListener(this);
		exit_btn.addActionListener(this);
	}
    @Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == login_btn)
		{
		     String username = user.getText();
		     char[] pass1 = pass.getPassword();
		     String pswd = "";
		     for(char i : pass1)
		     {
			     pswd = pswd + i;
		     } 
	         try
		     {
			      Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/data_login","root","root");
			      PreparedStatement ps = (PreparedStatement) con.prepareStatement("Select name, password from Student where name=? and password=?");
			
			      ps.setString(1, username);
			      ps.setString(2, pswd);
			
                  System.out.println(username + " "+pswd);
			
                  ValidateData v = new ValidateData();
                  java.util.List<String> errors = v.validateLogin(username, pswd);
			      if(errors.size() > 0)
			      {
				       JOptionPane.showMessageDialog(null, errors.toArray());
				       return;
			      }
			      
			      ResultSet rs = ps.executeQuery();
			      if(rs.next())
			      {
				       dispose();
				       JOptionPane.showMessageDialog(login_btn, "You have successfully logged in");
			      }
			      else
			      {
				       JOptionPane.showMessageDialog(login_btn, "Invalid user name & password");
			      }
			      con.close();
		      }
		      catch (SQLException sqlException)
		      {
			      sqlException.printStackTrace();
		      }
		 }
		 else if(e.getSource() == reset_btn)
		 {
			  user.setText("");
			  pass.setText("");
		 }
		 else if(e.getSource() == exit_btn)
		 {
			  JFrame fr = new JFrame("Exit");
			  if(JOptionPane.showConfirmDialog(fr,"Confirm if you want to exit","Login Page", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
			  {
				  System.exit(0);
			  }
		 }
	}
}
public class login 
{
    public static void main(String[] args) 
    {
		MyFrame frame = new MyFrame();
	}
}
