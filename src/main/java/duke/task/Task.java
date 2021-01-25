package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isCompleted;

    private static DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Task(){

    }

    public Task(String title){
        this.description = title;
        this.isCompleted = false;
    }

    public Task(String title, boolean b){
        this.description = title;
         this.isCompleted = b;
    }

    public void setCompleted(){
        this.isCompleted = true;
    }

    public String getIsCompleted(){
        return (this.isCompleted ? "\u2718" : " ");
    }

    public String getTaskName(){
        return this.description;
    }

    public String changeFormat(){
        return "," +  this.isCompleted + "," + this.getTaskName();
    }

    public Task changeToTaskFormat(String string_task) {

        if(string_task.charAt(0) == 'T'){
            String[] tasks = string_task.split(",");
            return new ToDo(tasks[2], Boolean.parseBoolean(tasks[1]));
        } else if(string_task.charAt(0) == 'D'){
            String[] tasks = string_task.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);

            return new Deadline(tasks[2], Boolean.parseBoolean(tasks[1]),date, startTime);
        }else if(string_task.charAt(0) == 'E'){
            String[] tasks = string_task.split(",");

            LocalDate date = LocalDate.parse(tasks[3], dateFormatter);
            LocalTime startTime = LocalTime.parse(tasks[4], timeFormatter);
            LocalTime endTime = LocalTime.parse(tasks[5], timeFormatter);

            return new Event(tasks[2], Boolean.parseBoolean(tasks[1]), date, startTime,endTime);
        } else{
            return new Task();
        }
    }

    @Override
    public String toString() {
        return "[" + this.getIsCompleted() + "] " + this.getTaskName();
    }
}
