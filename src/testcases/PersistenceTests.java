package testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import wrappers.Userbean;
import wrappers.Wrapper;
import dbfiles.Layer;

public class PersistenceTests {

	Wrapper wrap = new Wrapper();
	Userbean user = new Userbean();

	@Test
	public void testCSVQuery() {
		Userbean user = new Userbean();
		Layer layer = wrap.getObject("CSV");
		user.setNum(7);
		Assert.assertNotNull(layer.getLogger(user));
	}

	@Test
	public void testCSVInsert() {
		Layer layer = wrap.getObject("CSV");
		user.setUser("Sam");
		user.setDate(new SimpleDateFormat("HH:mm").format(new Date()));
		user.setMessage("Welcome to Java");
		Assert.assertTrue(layer.LogUser(user));
	}

	@Test
	public void testSQLITEInsert() {
		Layer layer = wrap.getObject("SQLITE");
		user.setUser("Anvil");
		user.setDate(new SimpleDateFormat("HH:mm").format(new Date()));
		user.setMessage("Welcome to Java");
		Assert.assertTrue(layer.LogUser(user));
	}

	@Test
	public void testSQLITEQuery() {
		Userbean user = new Userbean();
		Layer layer = wrap.getObject("SQLITE");
		user.setNum(6);
		Assert.assertNotNull(layer.getLogger(user));
	}

	@Test
	public void testQuery() {
		Layer layer = wrap.getObject("XML");
		user.setNum(4);
		Assert.assertNotNull(layer.getLogger(user));
	}

	@Test
	public void testXMLInsert() {
		Layer layer = wrap.getObject("XML");
		user.setDate(new SimpleDateFormat("HH:mm").format(new Date()));
		user.setMessage("Hi again");
		user.setUser("Peter");
		Assert.assertTrue(layer.LogUser(user));
	}

}
