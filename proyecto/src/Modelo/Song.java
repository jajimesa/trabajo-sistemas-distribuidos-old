package Modelo;

import java.io.Serializable;

public class Song implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private int duration;
	
	public Song(String title, String author, int duration) {
		this.title = title;
		this.author = author;
		this.duration = duration;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return author;
	}

	public int getDuration() {
		return duration;
	}
}
