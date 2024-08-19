package Server;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private int year;
	private double score;

	public Student(int id, String name, int year, double score) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public double getScore() {
		return score;
	}

}
