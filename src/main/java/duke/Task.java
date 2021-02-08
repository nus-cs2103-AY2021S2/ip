package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Task {

    private String name;
    private char type;
    private LocalDate dateTime;
    private boolean isDone;
    
    /**
    * Constructor for the Task class. This constructor is used to create Tasks of types Event and Deadline.
    * <p>All tasks created are not done initially.
    * @param name the name of the task
    * @param task the type of the task ('D' for deadline and 'E' for event)
    * @param dateTime the date of the deadline or event
    */
    public Task(String name, char task, LocalDate dateTime) {
        this.name = name;
        this.type = task;
        this.dateTime = dateTime;
        isDone = false;
    }
    
    /**
    * Constructor for the Task class. This constructor is used to create Tasks of type Todo.
    * <p>All tasks created are not done initially.
    * @param name the name of the Todo task
    */
    public Task(String name) {
        this.name = name;
        this.type = 'T';
        isDone = false;
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
        return isDone;
    }
    
    /**
    * Sets the Task as done
    */
    public void mark() {
        isDone = true;
    }

    /**
    * This method checks whether the two Task objects are the same.
    * @param task Task object to be compared
    * @return Whether the Task object is same
    */
    public boolean equals(Task task) {
        if (type == task.type) {
            if (!name.equals(task.name))
                return false;

            if (type == 'D' || type == 'E') {
                if (this.dateTime.compareTo(task.dateTime) != 0)
                    return false;
            }

            if (isDone != task.isDone)
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
        
        String returnStr = "[" + type + "]";
        
        if (isDone)
            returnStr += "[X] " + name;
        else
            returnStr += "[ ] " + name;
        
        switch (type) {
        case 'D':
            returnStr += " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
            break;
        case 'E':
            returnStr += " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
            break;
        }
        
        return returnStr;
        
    }
}