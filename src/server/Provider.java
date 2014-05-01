package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import wrappers.TempStorage;
import wrappers.Userbean;
import wrappers.Wrapper;
import dbfiles.Layer;

public class Provider {

	Vector<String> users = new Vector<String>();
	Vector<HandleClient> clients = new Vector<HandleClient>();
	Socket client;

	public Provider() throws Exception {
		ServerSocket server = new ServerSocket(8008);
		System.out.println("Server Started...");
		while (true) {
			client = server.accept();
			// add incoming client to connected clients vector.
			HandleClient c = new HandleClient(client);
			clients.add(c);
		} //
	}

	public void broadcast(String user, String message) {
		// send message to all connected users
		for (HandleClient c : clients) {
			c.sendMessage(user, message);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Provider();
	}

	class HandleClient extends Thread {

		String name = "";
		String tech = "";
		BufferedReader input;
		PrintWriter output;// send output to client
		Wrapper wrap = new Wrapper();
		Userbean user;
		TempStorage temp = new TempStorage();

		private void process(String name, String messg) {
			user = new Userbean();
			user.setDate(new SimpleDateFormat("YYYY//mm/dd HH:mm")
					.format(new Date()));
			user.setMessage(messg);
			user.setUser(name);
			// load them into temporal storage
			temp.setStatus_log(user);
			Layer layer = wrap.getObject(tech);
			// save the users to a database
			layer.LogUser(user);
			// keep a copy of the message
			layer = wrap.getObject("SQLITE");
			layer.LogUser(user);
		}

		private String getInfo(String date) {
			Userbean bean = new Userbean();
			bean.setNum(Integer.parseInt(date));
			Layer layer = wrap.getObject("SQLITE");
			layer.getLogger(bean);
			String message = "Sent on:" + bean.getDate() + " by:"
					+ bean.getUser() + "\nMessage:" + bean.getMessage();
			return message;
		}

		public HandleClient(Socket client) throws Exception {
			// get input and output streams
			input = new BufferedReader(new InputStreamReader(client
					.getInputStream()));
			output = new PrintWriter(client.getOutputStream(), true);
			// read name
			String inp = input.readLine();
			String[] values = inp.split(";");
			name = values[0];
			tech = values[1];
			broadcast(name, " has joined ");
			start();
		}

		public void sendMessage(String uname, String msg) {
			output.println(uname + ": " + msg);
			// keep record of the message sent
			process(uname, msg);
		}

		public void getOnlineUsers() {
			for (@SuppressWarnings("unused")
			HandleClient c : clients) {
				for (int i = 0; i < users.size(); i++) {
					broadcast("", users.get(i));
				}
			}
		}

		public String getUserName() {
			return name;
		}

		public void run() {
			String line;
			try {
				while (true) {
					line = input.readLine();
					if (line.equals("end")) {
						// notify all for user disconnection
						broadcast(name, " Has disconnected!");
						clients.remove(this);
						users.remove(name);
						break;
					} else if (line.equals("getusers")) {
						getOnlineUsers();
					} else if (line.startsWith("get;")) {
						String values[] = line.split(";");
						sendMessage(name, getInfo(values[1]));
					} else {
						broadcast(name, line); // method of outer class - send
					}
				} // end of while
			} // try
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} // end of run()
	} // end of inner class

}
