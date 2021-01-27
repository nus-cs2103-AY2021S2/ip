import java.time.format.DateTimeFormatter;


public class Task {
    private final Boolean isDone;
    protected final String name;
    protected DateTimeFormatter Date;
    protected DateTimeFormatter Time;

    Task(String name,Boolean isDone){
        this.isDone = isDone;
        this.name = name;
        this.Date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.Time = DateTimeFormatter.ofPattern("hh:mm");
    }

    String getName(){
        return this.name;
    }
    DateTimeFormatter getDate() { return this.Date; }
    DateTimeFormatter getTime() { return this.Time; }
    Boolean getIsDone(){
        return this.isDone;
    }

    Task finish(){
        return new Task(this.getName(), true) ;
    }

    @Override
    public String toString(){
        if(this.isDone){
            return "[X] " + this.getName();
        }
        else{
            return "[ ] " + this.getName();
        }
    }
}
