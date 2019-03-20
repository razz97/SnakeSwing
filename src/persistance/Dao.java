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
	private AppController controller;
	private File usersFile = new File("resources" + File.separator + "passwords.xml");
	private File scoresFile = new File("resources" + File.separator + "scores.xml");
	private SAXBuilder builder = new SAXBuilder();
	private List<Score> addedScores = new ArrayList<>();
		
	public static Dao getInstance(AppController controller) {
		if (instance == null)
			instance = new Dao(controller);
		return instance;
	}
	
	private Dao(AppController controller) {
		this.controller = controller;
	}
	
	private <T> T execute(SupplierExceptionThrower<T> exec) {
		try { return exec.get(); } 
		catch (Exception e) { controller.fatalError(); }
		return null;
	}
	
	private List<Element> getElementList(File file) throws Exception {
		return builder.build(file).getRootElement().getChildren();
	}
	
	private void writeDocument(Document doc, File file) throws Exception {
        FileWriter writer = new FileWriter(file);
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(doc, writer);
        writer.close();
	}
	
	private Score parseScore(Element elem) {
		String name = elem.getAttributeValue("name");
		Date date = execute(() -> controller.formatter.parse(elem.getChildText("time")));
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
		time.setText(controller.formatter.format(score.getDate()));
		Element points = new Element("points");
		points.setText(String.valueOf(score.getPoints()));
		root.addContent(time);
		root.addContent(points);
		return root;
	}
	
	private Element serializeUser(User user) {
		Element root = new Element("score");
		root.setAttribute("name", user.getName());
		root.setAttribute("psw", user.getPassword());
		return root;
	}
		
	public boolean auth(User user) {
		return execute(() -> {
			for (Element elem: getElementList(usersFile)) {
				if (user.equals(parseUser(elem)))
					return true;
			}
			return false;
		});
	}
	
	public void register(User user) {
		execute(() -> {
			Document doc = builder.build(usersFile);
			doc.getRootElement().addContent(serializeUser(user));
			writeDocument(doc, usersFile);
			return null;
		});
	}
	
	public boolean isUsername(String username) {
		return execute(() -> {
			return getElementList(usersFile).stream().filter(e -> e.getAttributeValue("name").equals(username)).findAny().isPresent();
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
	
	public void commit() {
		execute(() -> {
			List<Element> newElements = addedScores.stream().map(s -> serializeScore(s)).collect(Collectors.toList());
			Document doc = builder.build(scoresFile);
			doc.getRootElement().addContent(newElements);
			writeDocument(doc, scoresFile);
	        addedScores.clear();
	        controller.changesCommitted();
			return null;
		});
	}
	
	public boolean areChangesCommitted() {
		return addedScores.isEmpty();
	}
}
