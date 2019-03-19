package persistance;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import controller.AppController;
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
	
	private List<Score> addedScores = new ArrayList<>();
		
	public static Dao getInstance() {
		if (instance == null)
			instance = new Dao();
		return instance;
	}
	
	private Dao() {}
	
	public boolean auth(User user) {
		return execute(() -> {
			for (Element elem: getElementList(usersFile)) {
				if (user.equals(parseUser(elem)))
					return true;
			}
			return false;
		});
	}
	
	public List<Score> getScores(User user) {
		return execute(() ->  {
			List<Score> scores = getElementList(scoresFile).stream()
					.filter(e -> user.getName().equals(e.getAttributeValue("name")))
					.map(e -> parseScore(e))
					.collect(Collectors.toList());
			scores.addAll(addedScores);
			return scores;
		});
	}
	
	public List<User> getUsers() {
		return execute(() ->  {
			return getElementList(usersFile).stream()
					.map(e -> parseUser(e))
					.collect(Collectors.toList());
		});
	}
	
	private List<Element> getElementList(File file) throws Exception {
		return builder.build(file).getRootElement().getChildren();
	}
	
	private Score parseScore(Element elem) {
		String name = elem.getAttributeValue("name");
		Date date = execute(() -> AppController.getInstance().formatter.parse(elem.getChildText("time")));
		int points = Integer.parseInt(elem.getChildText("points"));
		return new Score(name,date,points);
	}
	
	private User parseUser(Element elem) {
		return new User(elem.getAttributeValue("name"), elem.getAttributeValue("psw"));
	}
	
	private Element serializeScore(Score score) {
		Element root = new Element("score");
		root.setAttribute("name", score.getUser());
		Element time = new Element("time");
		time.setText(AppController.getInstance().formatter.format(score.getDate()));
		Element points = new Element("points");
		points.setText(String.valueOf(score.getPoints()));
		root.addContent(time);
		root.addContent(points);
		return root;
	}
	
	private <T> T execute(SupplierExceptionThrower<T> exec) {
		try { return exec.get(); } 
		catch (Exception e) { e.printStackTrace(); AppController.getInstance().fatalError(); }
		return null;
	}

	public void commit() {
		execute(() -> {
			List<Element> newElements = addedScores.stream().map(s -> serializeScore(s)).collect(Collectors.toList());
			Document doc = builder.build(scoresFile);
			doc.getRootElement().addContent(newElements);
	        FileWriter writer = new FileWriter(scoresFile);
	        XMLOutputter outputter = new XMLOutputter();
	        outputter.setFormat(Format.getPrettyFormat());
	        outputter.output(doc, writer);
	        writer.close(); 
	        addedScores.clear();
			return null;
		});

	}

	public Score getBestScore() {
		return execute(() -> {
			Score score = new Score(null, null, -1);
			for (Element elem: getElementList(scoresFile)) {
				Score tmp = parseScore(elem);
				if (tmp.compareTo(score) > 0)
					score = tmp;
			}
			return score;
		});
	}

	public void addScore(Score score) {
		addedScores.add(score);
	}
}
