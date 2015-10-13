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

import ch.css.pomodoro.client.service.RegistrationService;
import ch.css.pomodoro.client.utility.IconFactory;
import ch.css.pomodoro.client.utility.JTextFieldLimit;

public class RegistrationUI {

	private static Logger logger = LoggerFactory.getLogger(RegistrationUI.class);
	private static JTextField userPnrText;
	private static JTextField userNameText;
	private static JTextField gruppeNameText;
	private static JButton registerButton;
	private static JFrame frame;

	public void registerClient() {
		logger.info("Starting client registration!");

		createRegistrationUI();

		// readDataFromUI();

		// assertAllRequiredeDataFilled();

		// boolean reponse = callRegistrationService(pNr, name, gruppe);

		// reactToResponse(response);

	}

	private void createRegistrationUI() {
		frame = new JFrame("Registration");
		frame.setIconImage(IconFactory.createAppIconBig().getImage());
		// Center on Screen
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 170);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		setupContent(panel);

		frame.setVisible(true);
	}

	private static void setupContent(JPanel panel) {
		panel.setLayout(null);

		JLabel userPnrLabel = new JLabel("P-Nummer *");
		userPnrLabel.setBounds(10, 10, 80, 25);
		panel.add(userPnrLabel);

		userPnrText = new JTextField();
		userPnrText.setDocument(new JTextFieldLimit(6));
		userPnrText.setBounds(100, 10, 160, 25);
		panel.add(userPnrText);

		JLabel userNameLabel = new JLabel("Name *");
		userNameLabel.setBounds(10, 40, 80, 25);
		panel.add(userNameLabel);

		userNameText = new JTextField();
		userNameText.setDocument(new JTextFieldLimit(50));
		userNameText.setBounds(100, 40, 160, 25);
		panel.add(userNameText);

		JLabel gruppeNameLabel = new JLabel("Gruppe");
		gruppeNameLabel.setBounds(10, 70, 80, 25);
		panel.add(gruppeNameLabel);

		gruppeNameText = new JTextField();
		gruppeNameText.setDocument(new JTextFieldLimit(50));
		gruppeNameText.setBounds(100, 70, 160, 25);
		panel.add(gruppeNameText);

		registerButton = new JButton("Register");
		registerButton.setBounds(185, 110, 80, 25);
		panel.add(registerButton);

		addRegisterButtonActionListener();

	}

	private static void addRegisterButtonActionListener() {
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (userPnrText.getText().isEmpty() || userNameText.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill out all required (*) fields.");
				} else {
					RegistrationService regService = new RegistrationService();
					boolean successful = regService.callRegistrationService(userPnrText.getText(),
							userNameText.getText(), gruppeNameText.getText());
					if (successful) {
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"Sorry, we couldn't register you. Please try again later.");
					}

				}
			}
		});
	}
}
