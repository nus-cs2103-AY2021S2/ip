import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TaskTime {
    protected ArrayList<Task> taskList;

    public TaskTime(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // These are unfinished methods.
    public void getDeadlines(LocalDate date){
        System.out.println("What date? (Please key in: yyyy-MM-dd HHmm.)");
    }
    public void getEvents(LocalDate date){
        System.out.println("What date? (Please key in: yyyy-MM-dd HHmm.)");
    }
}
