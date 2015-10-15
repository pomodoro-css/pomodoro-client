package ch.css.pomodoro.client.ui.registration;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.utility.IconFactory;
import ch.css.pomodoro.client.utility.UserInfo;

public class RegistrationUI extends JFrame {

	private static final long serialVersionUID = 5894670110315552416L;
	private static Logger logger = LoggerFactory.getLogger(RegistrationUI.class);
	private static JTextField userPnrText;
	private static JTextField userNameText;
	private static JTextField gruppeNameText;
	private static JButton registerButton;
	private static JButton cancelButton;

	public RegistrationUI() {
		logger.info("Starting client registration!");
	}

	public void showRegistrationUI() {
		this.setTitle("Registration");
		this.setIconImage(IconFactory.createAppIconBig().getImage());
		// Center on Screen
		this.pack();
		this.setLocationRelativeTo(null);
		this.setSize(280, 170);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setupContent();
		this.setVisible(true);
	}

	private void setupContent() {

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel userPnrLabel = new JLabel("P-Nummer *:");
		userPnrLabel.setBounds(10, 10, 90, 25);
		Font pnrFont = new Font(userPnrLabel.getFont().getName(), Font.BOLD, userPnrLabel.getFont()
				.getSize());
		userPnrLabel.setFont(pnrFont);
		panel.add(userPnrLabel);

		userPnrText = new JTextField();
		userPnrText.setDocument(new JTextFieldLimit(6));
		userPnrText.setBounds(110, 10, 160, 25);
		panel.add(userPnrText);

		JLabel userNameLabel = new JLabel("Name *:");
		userNameLabel.setBounds(10, 40, 90, 25);
		Font nameFont = new Font(userNameLabel.getFont().getName(), Font.BOLD, userNameLabel
				.getFont().getSize());
		userNameLabel.setFont(nameFont);
		panel.add(userNameLabel);

		userNameText = new JTextField();
		userNameText.setDocument(new JTextFieldLimit(30));
		userNameText.setBounds(110, 40, 160, 25);
		panel.add(userNameText);

		JLabel gruppeNameLabel = new JLabel("Gruppe *:");
		gruppeNameLabel.setBounds(10, 70, 90, 25);
		Font gruppeFont = new Font(gruppeNameLabel.getFont().getName(), Font.BOLD, gruppeNameLabel
				.getFont().getSize());
		gruppeNameLabel.setFont(gruppeFont);
		panel.add(gruppeNameLabel);

		gruppeNameText = new JTextField();
		gruppeNameText.setDocument(new JTextFieldLimit(50));
		gruppeNameText.setBounds(110, 70, 160, 25);
		panel.add(gruppeNameText);

		registerButton = new JButton("Register");
		registerButton.setBounds(10, 110, 100, 25);
		registerButton.addActionListener(new RegistrationActionListener());
		panel.add(registerButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(170, 110, 100, 25);
		cancelButton.addActionListener(new CancelActionListener());
		panel.add(cancelButton);

		this.add(panel);

	}

	class RegistrationActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (StringUtils.isBlank(userPnrText.getText())
					|| StringUtils.isBlank(userNameText.getText())
					|| StringUtils.isBlank(gruppeNameText.getText())) {
				JOptionPane.showMessageDialog(null, "Please fill out all required (*) fields.");
			} else {
				dispose();
				UserInfo.setPNummer(StringUtils.trim(userPnrText.getText()));
				UserInfo.setName(StringUtils.trim(userNameText.getText()));
				UserInfo.setGroupName(StringUtils.trim(gruppeNameText.getText()));
				RegistrationHandler regHandler = new RegistrationHandler();

				try {
					regHandler.registerClient();
				} catch (UnsupportedEncodingException e1) {
					JOptionPane.showMessageDialog(null,
							"Please don't use unkown symbols/characters.");
				}

			}
		}
	}

	class CancelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}

}
