package dbfiles;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import wrappers.Userbean;

public class XMLLogger implements Layer {

	File file;
	Document doc;
	DocumentBuilder builder;
	DocumentBuilderFactory docFactory;
	Element root;
	Double count;

	public XMLLogger() {
		super();
		file = new File("log_tracker.xml");
		try {
			docFactory = DocumentBuilderFactory.newInstance();
			builder = docFactory.newDocumentBuilder();

			if (!file.exists()) {
				file.createNewFile();
				// create a new element
				doc = builder.newDocument();
				root = doc.createElement("Users");
				doc.appendChild(root);
				count = 0.0;
			} else {
				// root elements
				doc = builder.parse(file);
				root = doc.getDocumentElement();
				// doc.appendChild(root);
				XPathFactory xpathfactory = XPathFactory.newInstance();
				XPath xpath = xpathfactory.newXPath();
				// get the last message of the
				XPathExpression expr = xpath.compile("count(//message)");
				count = (Double) expr.evaluate(doc, XPathConstants.NUMBER);
			}
			// add a message and time field
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean LogUser(Userbean user) {
		boolean done = false;
		try {
			// create a new message node
			Element mesg = doc.createElement("message");
			// increment the value of id by one
			mesg.setAttribute("id", String.valueOf(count.intValue() + 1));
			mesg.setAttribute("time-sent", user.getDate());
			mesg.setAttribute("user", user.getUser());
			mesg.appendChild(doc.createTextNode(user.getMessage()));
			root.appendChild(mesg);
			// write the document to XML
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			done = true;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return done;
	}

	public Userbean getLogger(Userbean user) {
		// Normalize the document
		doc.getDocumentElement().normalize();
		// load the libraries
		XPathFactory xpathfactory = XPathFactory.newInstance();
		XPath xpath = xpathfactory.newXPath();
		try {
			XPathExpression expr = xpath.compile("//message[@id=\""
					+ user.getNum() + "\"]");
			Object result = expr.evaluate(doc, XPathConstants.NODE);
			Element elem = (Element) result;
			user.setDate(elem.getAttribute("time-sent"));
			user.setMessage(elem.getTextContent());
			user.setUser(elem.getAttribute("user"));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return user;
	}

}
