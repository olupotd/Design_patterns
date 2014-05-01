/**
 * 
 */
package connections;

/**
 * @author new
 * 
 */
public class User {

	private String user;
	private String pass;
	private String email;
	private String fname;
	private String lname;
	private String date;
	private boolean valid;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param values
	 *            the date to set
	 */
	public void setDate(String values) {
		this.date = values;
	}

	public String getUser() {
		return user;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public String getEmail() {
		return email;
	}

	public User(String user, String pass, String email) {
		super();
		this.user = user;
		this.pass = pass;
		this.email = email;
	}

}
