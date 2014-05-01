package dbfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wrappers.Userbean;

public class SQLITELogger implements Layer {

	private Connection con;
	private Statement st;
	private ResultSet result;

	public SQLITELogger() {
		super();
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:user_logger.db");
			if (!con.isClosed())
				st = con.createStatement();
			st
					.executeUpdate("create table if not exists message( id INTEGER PRIMARY KEY AUTOINCREMENT , time integer, user varchar(20), message varchar(20))");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean LogUser(Userbean bean) {
		boolean done = false;
		try {
			int ok = st
					.executeUpdate("insert into message (time, user, message) values('"
							+ bean.getDate()
							+ "','"
							+ bean.getUser()
							+ "','"
							+ bean.getMessage() + "')");
			if (ok > 0)
				done = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return done;
	}

	public Userbean getLogger(Userbean use) {
		// TODO Auto-generated method stub
		try {
			result = st.executeQuery("select * from  message where id = '"
					+ use.getNum() + "'");
			while (result.next()) {
				use.setUser(result.getString(3));
				use.setMessage(result.getString(4));
				use.setDate(result.getString(2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return use;
	}

}
