package com.highsens.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	public LoginFunctions login = new LoginFunctions();
	private JComboBox<String> cbUsers = new JComboBox<String>();

	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 295);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 24));
		lblNewLabel.setBounds(98, 11, 231, 28);
		contentPane.add(lblNewLabel);
		
		cbUsers.setBounds(131, 85, 172, 20);
		populateComboBox();
		contentPane.add(cbUsers);
		
		
		txtName = new JTextField();
		txtName.setBounds(131, 154, 172, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton = new JButton("Register user");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtName.getText().isEmpty()){
					if(login.registerPlayer(txtName.getText())){
						populateComboBox();
						ScreenManager.setUser();
						txtName.setText("");
					}
				}
			}
		});
		btnNewButton.setBounds(168, 185, 112, 23);
		contentPane.add(btnNewButton);
		contentPane.getRootPane().setDefaultButton(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Select User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbUsers.getSelectedIndex() > -1)
				{
					if(login.tryLogin(cbUsers.getSelectedItem().toString())){
						ScreenManager.setUser();
						ScreenManager.hideLoginScreen();
					}
				}
			}
		});
		btnNewButton_1.setBounds(168, 116, 109, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back to Main Menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.hideLoginScreen();
			}
		});
		btnNewButton_2.setBounds(102, 219, 247, 23);
		contentPane.add(btnNewButton_2);
		this.setLocationRelativeTo(null);
	}
	
	private void populateComboBox(){
		cbUsers.removeAllItems();
		for(int i = 0; i < PlayersData.getInstance().getPlayerArray().size(); i++){
			cbUsers.addItem((String)((JSONObject)(PlayersData.getInstance().getPlayerArray().get(i))).get("name"));
		}
	}
}
