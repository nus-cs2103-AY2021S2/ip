package duke.task;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = stringToDatetime(by);
    }

    public Deadline(String description, String by, boolean status){
        super(description,status);
        this.by = stringToDatetime(by);
    }


    public Deadline(String description, LocalDateTime by, boolean status){
        super(description,status);
        this.by = by;
    }

    private LocalDateTime stringToDatetime(String by){
        return LocalDateTime.parse(by,DF1);
    }

    private String datetimeToString(LocalDateTime by){
        return DF2.format(by);
    }


    public LocalDateTime getBy(){
        return by;
    }


    @Override
    public String toString() {
        return "[D]" + super.getTaskName() + " (by: " + datetimeToString(by) + ")";
    }
}
