import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean isDone;
    private String taskDetail;
    Task(String taskDetail){
        this.taskDetail = taskDetail;
        this.isDone = false;
    }

    public boolean getTaskStatus(){
        return this.isDone;
    }

    public String getTaskDetail(){
        return this.taskDetail;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }
//    accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15) and
//    print in a different format such as MMM dd yyyy e.g., (Oct 15 2019)
    protected static LocalDate dateStringToDate (String inputDate) {
            String customInputDate = inputDate.replaceAll("-", " ");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate date = LocalDate.parse(customInputDate, format);
            return date;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getTaskDetail();
    }

}
