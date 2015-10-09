package ch.css.pomodoro.client;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationUI {

	private static Logger logger = LoggerFactory.getLogger(RegistrationUI.class);

	public void registerClient() {
		logger.info("Starting client registration!");

		createRegistrationUI();

		// readDataFromUI();

		// assertAllRequiredeDataFilled();

		// boolean reponse = callRegistrationService(pNr, name, gruppe);

		// reactToResponse(response);

	}

	private void createRegistrationUI() {
		JFrame frame = new JFrame("Registration");
		frame.setIconImage(IconFactory.createAppIconBig().getImage());
		// Center on Screen
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 180);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		frame.setVisible(true);
	}

	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);

		JLabel userPnrLabel = new JLabel("P-Nummer");
		userPnrLabel.setBounds(10, 10, 80, 25);
		panel.add(userPnrLabel);

		JTextField userPnrText = new JTextField();
		userPnrText.setDocument(new JTextFieldLimit(6));
		userPnrText.setBounds(100, 10, 160, 25);
		panel.add(userPnrText);

		JLabel userNameLabel = new JLabel("Name");
		userNameLabel.setBounds(10, 40, 80, 25);
		panel.add(userNameLabel);

		JTextField userNameTextField = new JTextField();
		userNameTextField.setDocument(new JTextFieldLimit(50));
		userNameTextField.setBounds(100, 40, 160, 25);
		panel.add(userNameTextField);

		JLabel gruppeNameLabel = new JLabel("Gruppe");
		gruppeNameLabel.setBounds(10, 70, 80, 25);
		panel.add(gruppeNameLabel);

		JTextField gruppeNameLabelField = new JTextField();
		gruppeNameLabelField.setDocument(new JTextFieldLimit(50));
		gruppeNameLabelField.setBounds(100, 70, 160, 25);
		panel.add(gruppeNameLabelField);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(10, 110, 80, 25);
		panel.add(registerButton);
	}
}
