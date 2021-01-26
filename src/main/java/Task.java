import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Task {
	private String name;
	private char type;
	private LocalDate dateTime;
	private boolean done;
	
	public Task(String n, char t, LocalDate dt) {
		name = n;
		type = t;
		dateTime = dt;
		done = false;
	}
	
	public Task(String n) {
		name = n;
		type = 'T';
		done = false;
	}
	
	public char getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDate() {
		return dateTime.toString();
	}
	
	public boolean getDone() {
		return done;
	}
	
	public void mark() {
		done = true;
	}
	
	public String toString() {
		
		String str = "["+type+"]";
		
		if (done)
			str += "[X] "+name;
		else
			str += "[ ] "+name;
		
		switch (type) {
			case 'D':
				str += " (by: "+dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
				break;
			case 'E':
				str += " (at: "+dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))+")";
				break;
		}
		
		return str;
		
	}
}