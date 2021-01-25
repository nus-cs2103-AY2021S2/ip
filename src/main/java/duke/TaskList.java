package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArraylist = new ArrayList<Task>();
    private static UI ui = new UI();

    public void addToDo(String description)  {
        taskArraylist.add(new ToDos(description));
    }


    public void addDeadline(String description, LocalDate dueDate, LocalTime endTime) {
        taskArraylist.add(new Deadlines (description,dueDate, endTime));
    }

    public void addEvent(String description, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        taskArraylist.add(new Events(description,false,dueDate, startTime, endTime));
    }

    public void showAllTask(){
        ui.printListHeader();
        for (int i = 0; i < taskArraylist.size(); i++) {
            ui.printTask(i,taskArraylist.get(i) );
        }
        ui.displayLines();
    }

    public void markAsDone(int index){
        taskArraylist.get(index).setCompleted();
        ui.displayDoneTaskMessage(taskArraylist.get(index));
        taskArraylist.get(index).setCompleted();
    }

    public void deleteTask(int index){
        ui.displayDeletedTaskMessage(taskArraylist.get(index));
        taskArraylist.remove(index);
    }

    public ArrayList<Task> getTaskListArray(){
        return this.taskArraylist;
    }

    public void setTaskArraylist (ArrayList<Task> al){
       this.taskArraylist = al;
    }
    public Task getTask(int index){
        return taskArraylist.get(index);
    }
}
