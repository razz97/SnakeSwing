package controller;

import java.util.ArrayList;
import java.util.List;

public class LogController {
	
	public interface LogListener {
		public void onNewLogMessage(String message);
	}
	
	private static LogController instance;
	
	private List<LogListener> listeners = new ArrayList<>();
		
	public static LogController getInstance() {
		if (instance == null)
			instance = new LogController();
		return instance;
	}
	
	private LogController() {}
	
	public void addListener(LogListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(LogListener listener)  {
		listeners.remove(listener);
	}
	
	public void log(String message) {
		listeners.forEach(l -> l.onNewLogMessage(message));
	}
	
	

}
