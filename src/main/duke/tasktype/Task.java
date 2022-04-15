package main.duke.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime due;

    /**
     * Constructor for task
     * @param name task information
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for task with date time format
     * @param name task information
     * @param dateTime Date time format
     */
    public Task(String name, String dateTime){
        this.name = name;
        this.isDone = false;
        this.due = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
    }

    /**
     * Set task as done
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * @return Get task information
     */
    public String getName(){
        return name;
    }

    /**
     * @return Get the status of task in symbol form
     */
    public String getStatusIcon(){
        return (isDone ? "O" : "X");
    }

    /**
     * Get the current status of task
     * @return current status of task
     */
    public String getStatus(){
        if(due == null){
            return " [" + type + "] " + " [" + getStatusIcon() + "] " + getName();
        }
        else{
            return " [" + type + "] " + " [" + getStatusIcon() + "] " + getName() + " "
                    + due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        }
    }
    public LocalDateTime getDue(){
        return due;
    }

    /**
     * Changes task information to the suitable format for a file
     * @return String containing the suitable format
     */
    public String convertToFile(){
        if(due==null){
            return String.format("%s~%s~%s", type, isDone ? 1:0, getName());
        }
        return String.format("%s~%s~%s~%s", type, isDone ? 1:0, getName(),
                due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")));
    }
}
