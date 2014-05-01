package wrappers;

import java.util.HashMap;
import java.util.Map;

public class TempStorage {

	private Map<Integer, Userbean> status_log = new HashMap<Integer, Userbean>();

	public void setStatus_log(Userbean user) {
		this.status_log.put((status_log.size()+1), user);
	}

	public Userbean getStatus_log(int number) {
		return status_log.get(number);
	}

}
