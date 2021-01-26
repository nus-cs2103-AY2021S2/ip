import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String toString(){
        String string = "";
        //Printing isDone
        string += String.format("[%s] ", isDone?"X":" ");
        string += this.content;
        return string;
    }

    abstract public String toFileString();

    public String parseDate(String string){
        try{
            LocalDate date = LocalDate.parse(string);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return string;
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDesc() {
        return this.content;
    }

    public boolean getDone() {
        return this.isDone;
    }
}
