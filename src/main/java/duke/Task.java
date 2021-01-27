package duke;

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
	
	/**
	* Constructor for the Task class. This constructor is used to create Tasks of types Event and Deadline.
	* <p>All tasks created are not done initially.
	* @param n the name of the task
	* @param t the type of the task ('D' for deadline and 'E' for event)
	* @param dt the date of the deadline or event
	*/
	public Task(String n, char t, LocalDate dt) {
		name = n;
		type = t;
		dateTime = dt;
		done = false;
	}
	
	/**
	* Constructor for the Task class. This constructor is used to create Tasks of type Todo.
	* <p>All tasks created are not done initially.
	* @param n the name of the Todo task
	*/
	public Task(String n) {
		name = n;
		type = 'T';
		done = false;
	}
	
	/**
	* Getter for the type of the Task object
	* @return Type of Task. 'T' - Todo, 'E' - Event, 'D' - Deadline.
	*/
	public char getType() {
		return type;
	}
	
	/**
	* Getter for the name of the Task object
	* @return Name of task.
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Getter for the Date of the Task object. Returns null when object is of todo type.
	* @return Date of task.
	*/
	public String getDate() {
		return dateTime.toString();
	}
	
	/**
	* Indicates whether Task object has been marked as done.
	* @return Whether Task is marked done.
	*/
	public boolean getDone() {
		return done;
	}
	
	/**
	* Sets the Task as done
	*/
	public void mark() {
		done = true;
	}

	/**
	* This method checks whether the two Task objects are the same.
	* @param t Task object to be compared
	* @return Whether the Task object is same
	*/
	public boolean equals(Task t) {
		if (this.type == t.type) {
			if (!this.name.equals(t.name))
				return false;

			if (this.type == 'D' || this.type == 'E') {
				if (this.dateTime.compareTo(t.dateTime) != 0)
					return false;
			}

			if (this.done != t.done)
				return false;

			return true;

		} else {
			return false;
		}
	}

	/**
	* This method returns a String object listing the properties of the Task.
	* @return Properties of the Task
	*/
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