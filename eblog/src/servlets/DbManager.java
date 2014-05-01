package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connections.User;

public class DbManager {

	private Connection con;
	private Statement st;
	private ResultSet set;
	private File file;
	private String line;
	private BufferedReader br;

	public DbManager() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/news", "root", "justin");
			if (!con.isClosed()) {
				st = con.createStatement();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User register(User user) {

		try {
			file = new File("users.csv");
			// if the file doesn't exist
			if (!file.exists())
				file.createNewFile();
			// open a output stream to the file
			PrintWriter pr = new PrintWriter(new FileWriter(file, true));
			// write the data to the file
			pr.println(user.getUser() + ";" + user.getPass() + ";"
					+ user.getEmail() + ";" + user.getFname() + ";"
					+ user.getLname() + ";" + new Date());
			// flush the stream buffers
			pr.flush();
			// set the newly registered user to valid
			user.setValid(true);
			pr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	// user;pass;email;fname;lname;date
	public User login(User bean) {

		file = new File("users.csv");
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String values[] = ((String) line).split(";");
				if (values[0].trim().equals(bean.getUser().trim())
						&& values[1].trim().equals(bean.getPass().trim())) {
					bean.setValid(true);
					bean.setFname(values[3]);
					bean.setLname(values[4]);
					bean.setDate(values[5]);
					break;
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	/*
	 * (comment, post_d, user_id, date)
	 */

	public boolean createComment(List<String> popst) {
		boolean done = false;
		Date d = new Date();
		try {
			int ok = st
					.executeUpdate("insert into comments(comments, post_id, user, date_posted) values('"
							+ popst.get(0)
							+ "', '"
							+ popst.get(1)
							+ "', '"
							+ popst.get(2)
							+ "', '"
							+ d.getHours()
							+ ":"
							+ d.getMinutes() + "Hours" + "')");
			if (ok > 0)
				done = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return done;
	}

	public boolean createPost(List<String> post) {

		boolean created = false;
		Date d = new Date();
		try {
			int ok = st
					.executeUpdate("insert into posts(headline, body,img,user,time) values( '"
							+ post.get(0)
							+ "', '"
							+ post.get(1)
							+ "', '"
							+ post.get(2)
							+ "', '"
							+ post.get(3)
							+ "', '"
							+ d.getHours() + ":" + d.getMinutes() + "Hours')");
			if (ok > 0)
				created = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return created;
	}
}
