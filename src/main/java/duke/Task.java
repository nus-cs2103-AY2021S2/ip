package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean status;

    private static DateTimeFormatter dateFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Task(){

    }

    public Task(String title){
        this.description = title;
        this.status = false;
    }

    public Task(String title, boolean b){
        this.description = title;
         this.status = b;
    }

    public void setCompleted(){
        this.status = true;
    }

    public String isCompleted(){
        return (this.status ? "\u2718" : " ");
    }

    public String getTaskName(){
        return this.description;
    }

    public String changeFormat(){
        return "," +  this.status + "," + this.getTaskName();
    }

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

    @Override
    public String toString() {
        return "[" + this.isCompleted() + "] " + this.getTaskName();
    }
}
