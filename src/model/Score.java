package model;

import java.util.Date;

public class Score implements Comparable<Score>{

	private String user;
	private Date date;
	private int points;
	
	public Score(String user, Date date, int points) {
		this.user = user;
		this.date = date;
		this.points = points;
	}

	public String getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public int compareTo(Score other) {
		return points - other.points;
	}

	@Override
	public String toString() {
		return user + ": " + points;
	}
}
