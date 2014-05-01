package clientfiles;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2147670646735712101L;
	String uname;
	PrintWriter pw;
	BufferedReader br;
	JTextArea taMessages, taUserList;
	JTextField tfInput;
	JButton btnSend, btnExit, btn;
	Socket client;

	public User(String user, String tech) throws UnknownHostException,
			IOException {
		// // TODO Auto-generated constructor stub
		super("Connected as: " + user); // set title for frame
		this.uname = user;
		client = new Socket("127.0.0.1", 8008);
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		pw = new PrintWriter(client.getOutputStream(), true);
		pw.println(uname + ";" + tech); // send name to server
		// bring up the chat interface
		buildInterface();
		new MessagesThread().start();

		// System.out.println(new SimpleDateFormat("YYYY/mm/dd HH:mm")
		// .format(new Date()));
	}

	public void buildInterface() {
		btnSend = new JButton("Send");
		btnExit = new JButton("Exit");
		btn = new JButton("Get Log");
		// chat area
		taMessages = new JTextArea();
		taMessages.setRows(10);
		taMessages.setColumns(50);
		taMessages.setEditable(false);
		// online users list
		taUserList = new JTextArea();
		taUserList.setRows(10);
		taUserList.setColumns(10);
		taUserList.setEditable(false);
		// top panel (chat area and online users list
		JScrollPane chatPanel = new JScrollPane(taMessages,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane onlineUsersPanel = new JScrollPane(taUserList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel tp = new JPanel(new FlowLayout());
		tp.add(chatPanel);
		tp.add(onlineUsersPanel);
		add(tp, "Center");
		// user input field
		tfInput = new JTextField(50);
		// button panel (input field, send and exit)
		JPanel bp = new JPanel(new FlowLayout());
		bp.add(tfInput);
		bp.add(btnSend);
		bp.add(btnExit);
		bp.add(btn);
		add(bp, "South");
		btnSend.addActionListener(this);
		tfInput.addActionListener(this);// allow user to press Enter key in
		// order to send message
		btnExit.addActionListener(this);
		btn.addActionListener(this);
		setSize(500, 300);
		setVisible(true);
		pack();
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		String user = JOptionPane.showInputDialog(null, "Enter username: ",
				" e.g samson", JOptionPane.PLAIN_MESSAGE);
		String techn = JOptionPane.showInputDialog(null,
				"Enter Technology for storage: ", "examples SQlite, XML, CSV)",
				JOptionPane.PLAIN_MESSAGE);

		new User(user, techn);
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnExit) {
			pw.println("end"); // inform server about termination
			System.exit(0);
		} else if (tfInput.getText().contains("getusers")) {
			pw.println("getusers");
		} else if (evt.getSource() == btn) {
			String msg_num = JOptionPane
					.showInputDialog(null, "Enter Messaget number ", "eg 1",
							JOptionPane.PLAIN_MESSAGE);
			pw.println("get;" + msg_num);
		} else {
			// send message to server
			pw.println(tfInput.getText());
			tfInput.setText(null);
		}

	}

	class MessagesThread extends Thread {

		@Override
		public void run() {
			String line;
			try {
				while (true) {
					line = br.readLine();
					taMessages.append(line + "\n");
					taMessages.setCaretPosition(taMessages.getDocument()
							.getLength());// auto scroll to last message
				} // end of while
			} catch (Exception ex) {
			}
		}
	}

}
