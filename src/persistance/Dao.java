package persistance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import controller.LogController;
import model.Score;
import model.User;

public class Dao {

	private static Dao instance;
	
	List<User> users = new ArrayList<>();
	List<Score> scores = new ArrayList<>();
		
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
			loadScores(docScores);
			loadUsers(docUsers);
		} catch (JDOMException | IOException | ParseException e) {
			LogController.getInstance().log("There was an error loading data into memory: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void loadUsers(Document doc) {
		List<Element> elems = doc.getRootElement().getChildren();
		elems.forEach(
				e -> users.add(new User(e.getAttributeValue("name"),e.getAttributeValue("psw")))
		);
	}
	
	private void loadScores(Document doc) throws ParseException {
		List<Element> elems = doc.getRootElement().getChildren();
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		for (Element elem: elems) {
			String name = elem.getAttributeValue("name");
			Date date = format.parse(elem.getChildText("time"));
			int points = Integer.parseInt(elem.getChildText("points"));
			scores.add(new Score(name,date,1));
		};
	}
	
	public boolean auth(User user) {
		return users.contains(user);
	}

	public List<Score> getScores() {
		return new ArrayList<>(scores);
	}
}
