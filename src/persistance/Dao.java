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

import controller.AppController;
import controller.LogController;
import model.Score;
import model.User;

public class Dao {
	
	private interface SupplierExceptionThrower<T> {
		T get() throws Exception;
	}

	private static Dao instance;
	
	private File usersFile = new File("resources" + File.separator + "passwords.xml");
	private File scoresFile = new File("resources" + File.separator + "scores.xml");
	private SAXBuilder builder = new SAXBuilder();
	private DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	
	List<User> users = new ArrayList<>();
	List<Score> scores = new ArrayList<>();
		
	public static Dao getInstance() {
		if (instance == null)
			instance = new Dao();
		return instance;
	}
	
	private Dao() {}
	
//	private void loadUsers(Document doc) {
//		List<Element> elems = doc.getRootElement().getChildren();
//		elems.forEach(
//				e -> users.add(new User(e.getAttributeValue("name"),e.getAttributeValue("psw")))
//		);
//	}
//	
//	private void loadScores(Document doc) throws ParseException {
//		List<Element> elems = doc.getRootElement().getChildren();
//		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		for (Element elem: elems) {
//			String name = elem.getAttributeValue("name");
//			Date date = format.parse(elem.getChildText("time"));
//			int points = Integer.parseInt(elem.getChildText("points"));
//			scores.add(new Score(name,date,1));
//		};
//	}
	
	public boolean auth(User user) {
		return execute(() -> {
			List<Element> elems = builder.build(usersFile).getRootElement().getChildren();
			for (Element elem: elems) {
				User tmp = new User(elem.getAttributeValue("name"), elem.getAttributeValue("psw"));
				if (user.equals(tmp))
					return true;
			}
			return false;
		});
	}
	
	private <T> T execute(SupplierExceptionThrower<T> exec) {
		try { return exec.get(); } 
		catch (Exception e) { AppController.getInstance().fatalError(); }
		return null;
	}

	public List<Score> getScores(User user) {
		return null;
	}
}
