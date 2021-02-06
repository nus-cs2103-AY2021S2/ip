package duke.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime dateTime;
    
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, Stirng dateTime){
        this.name = name;
        this.isDone = false;
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
    }

    public void setDone(){
        isDone = true;
    }

    public String getName(){
        return name;
    }

    public String getStatusIcon(){
        return (isDone ? "✓" : "X");
    }

    public String getStatus(){
        if(dateTime == null){
            return " [" + type + "] " + " [" + getStatusIcon() + "] " + getname(); 
        }
        else{
            return " [" + type + "] " + " [" + getStatusIcon() + "] " + getname() + " " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")); 
        }
    }
}
