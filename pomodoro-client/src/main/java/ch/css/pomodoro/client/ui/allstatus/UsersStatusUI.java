package ch.css.pomodoro.client.ui.allstatus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jvnet.hk2.component.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.css.pomodoro.client.service.RegisteredUser;
import ch.css.pomodoro.client.service.StatusRegisteredUsersService;
import ch.css.pomodoro.client.utility.IconFactory;

public class UsersStatusUI extends JFrame {

	private static final long serialVersionUID = -3510538483492789960L;
	private static Logger logger = LoggerFactory.getLogger(UsersStatusUI.class);
	private MultiMap<String, RegisteredUser> groupedUsers;
	private DefaultTableModel dtm;

	public UsersStatusUI() {
		logger.info("Starting Status UI");
	}

	public void showAllUserStatusUI() {
		this.setTitle("Status");
		this.setResizable(false);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setIconImage(IconFactory.createAppIconBig().getImage());

		prepareUsersByGroup();

		JComboBox<String> groupList = setupComboBox();
		this.add(groupList);

		JScrollPane pane = setupScrollableTable();
		updateTableContent(groupList.getItemAt(0));
		this.add(pane);

		this.setVisible(true);
	}

	@SuppressWarnings("serial")
	private JScrollPane setupScrollableTable() {
		String[] header = new String[] { "State", "Name", "P-Number", "Remaining Time" };
		dtm = new DefaultTableModel(0, header.length + 1) {
			@SuppressWarnings("unchecked")
			@Override
			public Class getColumnClass(int column) {
				if (column == 0)
					return ImageIcon.class;
				return Object.class;
			}
		};
		dtm.setColumnIdentifiers(header);
		JTable userTable = new JTable(dtm);
		JScrollPane pane = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(new Dimension(380, 330));
		return pane;
	}

	private JComboBox<String> setupComboBox() {
		JComboBox<String> groupList = new JComboBox<String>();
		for (String key : groupedUsers.keySet()) {
			groupList.addItem(key);
		}

		groupList.setPreferredSize(new Dimension(150, 20));
		groupList.addItemListener(new ComboBoxActionListener());
		return groupList;
	}

	private void prepareUsersByGroup() {
		StatusRegisteredUsersService service = new StatusRegisteredUsersService();
		List<RegisteredUser> mylist = service.callStatusRegisteredUsers();

		UserToGroupMapper mappedUsers = new UserToGroupMapper();
		groupedUsers = mappedUsers.mapToGroup(mylist);
	}

	public void updateTableContent(String groupName) {
		// clean table before adding data
		dtm.setRowCount(0);
		for (RegisteredUser user : groupedUsers.get(groupName)) {
			logger.info(user.toString());
			dtm.addRow(user.getUserRow());
		}
	}

	class ComboBoxActionListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				String groupName = (String) event.getItem();
				updateTableContent(groupName);
			}
		}

	}

}
