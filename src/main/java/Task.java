import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class Task {
    private String content;
    private boolean isDone;

    Task(String content) {
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

    abstract String toFileString();

    String parseDate(String string){
        try{
            LocalDate date = LocalDate.parse(string);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return string;
        }
    }

    protected void setDone() {
        this.isDone = true;
    }

    protected String getDesc() {
        return this.content;
    }

    protected boolean getDone() {
        return this.isDone;
    }
}
