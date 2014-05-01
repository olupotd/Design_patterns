package dbfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import wrappers.Userbean;

public class CSVLogger implements Layer {

	private File log_file;

	public CSVLogger() {
		super();
		log_file = new File("logFile.csv");
		try {
			if (!log_file.exists())
				log_file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean LogUser(Userbean user) {
		boolean done = false;
		try {
			PrintWriter printer = new PrintWriter(
					new FileWriter(log_file, true));
			printer.println(getLastNo() + ";" + user.getDate() + ";"
					+ user.getUser() + ";" + user.getMessage());
			printer.flush();
			done = true;
			printer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return done;
	}

	private int getLastNo() {
		int lines = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(log_file));
			while (br.readLine() != null) {
				lines++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (lines + 1);

	}

	public Userbean getLogger(Userbean date) {
		BufferedReader reader;
		try {
			log_file = new File("logFile.csv");
			reader = new BufferedReader(new FileReader(log_file));
			String line;
			line = reader.readLine();
			while (line != null) {
				String[] values = line.split(";");
				if (Integer.parseInt(values[0]) == date.getNum()) {
					date.setDate(values[1]);
					date.setUser(values[2]);
					date.setMessage(values[3]);
					break;
				}
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
