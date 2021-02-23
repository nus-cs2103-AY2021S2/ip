package duke.tasktype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String name;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime due;
    
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, String dateTime){
        this.name = name;
        this.isDone = false;
        this.due = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
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
    public String convertToFile(){
        if(due==null){
            return String.format("%s~%s~%s~%s", type, isDone ? 1:0, getName());
        }
        return String.format("%s~%s~%s~%s", type, isDone ? 1:0, getName(),
                due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")));
    }
}
