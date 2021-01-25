package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Stores all type of task
 */
public class Task {
    protected String description;
    protected boolean status;

    private static DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Empty constructor
     */
    public Task(){

    }


    /** Constructor to create task object
     * @param title
     */
    public Task(String title){
        this.description = title;
        this.status = false;
    }

    /**Constructor to create task object for retrieval of task from data file
     * @param title
     * @param b
     */
    public Task(String title, boolean b){
        this.description = title;
         this.status = b;
    }

    /**
     * Set task to completed
     */
    public void setCompleted(){
        this.status = true;
    }

    /** Return cross if task is completed
     * @return
     */
    public String isCompleted(){
        return (this.status ? "\u2718" : " ");
    }

    /** Return description of task
     * @return
     */
    public String getTaskName(){
        return this.description;
    }

    /** Return customized representation of task to add to data file
     * @return
     */
    public String changeFormat(){
        return "," +  this.status + "," + this.getTaskName();
    }

    /** Return the string version of task from data file to task objects
     * @param string_task
     * @return
     */
    public Task changeToTaskFormat(String string_task) {

        if(string_task.charAt(0) == 'T'){
            String[] tasks = string_task.split(",");
            return new ToDos(tasks[2], Boolean.parseBoolean(tasks[1]));

        } else if(string_task.charAt(0) == 'D'){
            String[] tasks = string_task.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);

            return new Deadlines(tasks[2], Boolean.parseBoolean(tasks[1]),date, startTime);

        }else if(string_task.charAt(0) == 'E'){
            String[] tasks = string_task.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);
            LocalTime endTime = LocalTime.parse(tasks[5], timeFormatter);

            return new Events(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime,endTime);
        }
        else{
            return new Task();
        }
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.isCompleted() + "] " + this.getTaskName();
    }
}
