package dbfiles;

import wrappers.Userbean;


public interface Layer {

	public boolean LogUser(Userbean user);

	public Userbean getLogger(Userbean use);
}
