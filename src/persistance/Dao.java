package persistance;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import controller.LogController;

public class Dao {

	private static Dao instance;
	
	List<Element> users = Collections.emptyList();
	List<Element> scores = Collections.emptyList();
		
	public static Dao getInstance() {
		if (instance == null)
			instance = new Dao();
		return instance;
	}
	
	private Dao() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document docUsers = builder.build(new File("resources" + File.separator + "passwords.xml"));
			Document docScores = builder.build(new File("resources" + File.separator + "scores.xml"));
			users = docUsers.getRootElement().getChildren();
			scores = docScores.getRootElement().getChildren();
		} catch (JDOMException | IOException e) {
			LogController.getInstance().log("There was an error loading data into memory: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean auth(String username, char[] password) {
		for (Element user: users) {
			String name = user.getAttributeValue("name");
			if (name.equals(username)) 
				return isCorrectPassword(user.getAttributeValue("psw").toCharArray(), password);
		}
		return false;
	}
	
	private boolean isCorrectPassword(char[] real, char[] input) {
		boolean correct = true;
		if (real.length != input.length) 
			correct = false;
		else
			correct = Arrays.equals(real, input);
		Arrays.fill(real, '0');
		return correct;
	}
	
}
