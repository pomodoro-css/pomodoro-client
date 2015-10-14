package ch.css.pomodoro.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.utility.IconFactory;

public class RegistrationUI extends JFrame {

	private static final long serialVersionUID = 5894670110315552416L;
	private static Logger logger = LoggerFactory.getLogger(RegistrationUI.class);
	private static JTextField userPnrText;
	private static JTextField userNameText;
	private static JTextField gruppeNameText;
	private static JButton registerButton;

	public RegistrationUI() {
		logger.info("Starting client registration!");
	}

	public void showRegistrationUI() {
		this.setTitle("Registration");
		this.setIconImage(IconFactory.createAppIconBig().getImage());
		// Center on Screen
		this.pack();
		this.setLocationRelativeTo(null);
		this.setSize(300, 170);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setupContent();
		this.setVisible(true);
	}

	private void setupContent() {

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel userPnrLabel = new JLabel("P-Nummer *:");
		userPnrLabel.setBounds(10, 10, 85, 25);
		panel.add(userPnrLabel);

		userPnrText = new JTextField();
		userPnrText.setDocument(new JTextFieldLimit(6));
		userPnrText.setBounds(100, 10, 160, 25);
		panel.add(userPnrText);

		JLabel userNameLabel = new JLabel("Name *:");
		userNameLabel.setBounds(10, 40, 85, 25);
		panel.add(userNameLabel);

		userNameText = new JTextField();
		userNameText.setDocument(new JTextFieldLimit(30));
		userNameText.setBounds(100, 40, 160, 25);
		panel.add(userNameText);

		JLabel gruppeNameLabel = new JLabel("Gruppe:");
		gruppeNameLabel.setBounds(10, 70, 85, 25);
		panel.add(gruppeNameLabel);

		gruppeNameText = new JTextField();
		gruppeNameText.setDocument(new JTextFieldLimit(50));
		gruppeNameText.setBounds(100, 70, 160, 25);
		panel.add(gruppeNameText);

		registerButton = new JButton("Register");
		registerButton.setBounds(185, 110, 80, 25);
		registerButton.addActionListener(new RegistrationActionListener());
		panel.add(registerButton);

		this.add(panel);

	}

	class RegistrationActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (userPnrText.getText().isEmpty() || userNameText.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill out all required (*) fields.");
			} else {
				dispose();
				RegistrationHandler regHandler = new RegistrationHandler(userPnrText.getText(),
						userNameText.getText(), gruppeNameText.getText());
				regHandler.registerClient();
			}
		}
	}

}
