package duke.task;
import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime at;


    public Event(String description, String at) {
        super(description);
        this.at = stringToDatetime(at);
    }


    public Event(String description, String at, boolean status){
        super(description,status);
        this.at = stringToDatetime(at);
    }

    public Event(String description, LocalDateTime at, boolean status){
        super(description,status);
        this.at = at;
    }

    private LocalDateTime stringToDatetime(String at){
        return LocalDateTime.parse(at,DF1);
    }

    private String datetimeToString(LocalDateTime at){
        return DF2.format(at);
    }


    public LocalDateTime getAt(){
        return at;
    }




    @Override
    public String toString() {
        return "[E]" + super.getTaskName() + " (at: " + datetimeToString(at) + ")";
    }
}


