package Modelo;

import java.io.File;
import java.io.Serializable;

public class Song implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private File file;
	
//	private String title;
//	private int duration;
	
	public Song(File f) {
		this.file = f;
	}
	
//	public String getTitle() {
//		return this.title;
//	}
//
//	public int getDuration() {
//		return duration;
//	}
}
