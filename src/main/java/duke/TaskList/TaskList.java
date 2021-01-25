package duke.TaskList;

import duke.UI.UI;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArraylist = new ArrayList<Task>();
    private static ArrayList<Task> findTaskArraylist = new ArrayList<Task>();

    private static UI ui = new UI();

    public void addToDo(String description)  {
        taskArraylist.add(new ToDos(description));
    }

    public void addDeadline(String description, LocalDate dueDate, LocalTime endTime) {
        taskArraylist.add(new Deadlines(description,dueDate, endTime));
    }

    public void addEvent(String description, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        taskArraylist.add(new Events(description,false,dueDate, startTime, endTime));
    }

    public void showAllTask(String type){
        ArrayList<Task> taskList;

        if(type.equals("search")){
            ui.printSearchHeader();
            taskList = findTaskArraylist;
        }else{
            ui.printListHeader();
            taskList = taskArraylist;
        }
        for (int i = 0; i <taskList.size(); i++) {
            ui.printTask(i,taskList.get(i) );
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

    public void findTask(String searchValue){
        for (int i=0; i< taskArraylist.size(); i++){
            if(taskArraylist.get(i).getTaskName().contains(searchValue)){
                findTaskArraylist.add(taskArraylist.get(i));
            }
        }
        if(findTaskArraylist.size() >0) {
            showAllTask("search");
        }else{
            ui.displayNoItemFoundMessage();
        }
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
