package ch.css.pomodoro.client.ui.allstatus;

import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jvnet.hk2.component.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.RegisteredUser;
import ch.css.pomodoro.client.service.StatusRegisteredUsersService;

public class UsersStatusUI extends JFrame {

	private static final long serialVersionUID = -3510538483492789960L;
	private static Logger logger = LoggerFactory.getLogger(UsersStatusUI.class);
	private String testKeyLocal;

	public UsersStatusUI() {
		logger.info("Starting Status UI");
	}

	public void showAllUserStatusUI() {
		this.setTitle("Status");
		this.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);

		StatusRegisteredUsersService service = new StatusRegisteredUsersService();
		List<RegisteredUser> mylist = service.callStatusRegisteredUsers();
		
		UserToGroupMapper mappedUsers = new UserToGroupMapper();
		MultiMap<String,RegisteredUser> groupedUsers = mappedUsers.mapToGroup(mylist);
		
		JComboBox<String> groupList = new JComboBox<String>();
		for (String key: groupedUsers.keySet()){
			groupList.addItem(key);
			testKeyLocal = key;
		}
		
		
		groupList.setBounds(5, 5, 390, 20);
		
		panel.add(groupList);
		

		String[] header = new String[]{"State", "Name", "P-Number", "Remaining Time"};
		 
		JTable userTable = new JTable();
		userTable.setBounds(5, 30, 390, 365);
		
		DefaultTableModel dtm = new DefaultTableModel(0, header.length + 1);
		dtm.setColumnIdentifiers(header);
		userTable.setModel(dtm);
		for (RegisteredUser user : groupedUsers.get(testKeyLocal)){
			dtm.addRow(user.getUserRow());
		}
		
		
		
		panel.add(new JScrollPane(userTable));
		
		
		
		this.add(panel);
		
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		logger.info(mylist.toString());

	}

}
