package wrappers;

import dbfiles.CSVLogger;
import dbfiles.Layer;
import dbfiles.SQLITELogger;
import dbfiles.XMLLogger;

public class Wrapper {

	public Layer getObject(String type) {
		if (type.equalsIgnoreCase("SQLITE"))
			return new SQLITELogger();
		if (type.equalsIgnoreCase("XML"))
			return new XMLLogger();
		if (type.equalsIgnoreCase("CSV"))
			return new CSVLogger();
		if (type.equalsIgnoreCase(null))
			return null;
		return null;
	}
}
