package duke.tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.ui.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Store an array list of task
 * Interact with task class to facilitate the creation, update, deletion of task
 */
public class TaskList {

    private static ArrayList<Task> taskArraylist = new ArrayList<Task>();
    private static ArrayList<Task> findTaskArraylist = new ArrayList<Task>();

    private static UI ui = new UI();

    /**Add a todo task to arraylist
     * @param description
     */
    public Boolean addToDo(String description) {

        Task toDo = new ToDo(description);
        boolean isDuplicate = false;
        for(int i=0; i< taskArraylist.size(); i++){
            isDuplicate = this.getTaskListArray().get(i).isSameTask(toDo);
            if(isDuplicate){
                break;
            }
        }
        if(!isDuplicate) {
            taskArraylist.add(toDo);
        }
        return isDuplicate;
    }

    /**Add a deadline task to arraylist
     * @param description
     * @param dueDate
     * @param endTime
     */
    public Boolean addDeadline(String description, LocalDate dueDate, LocalTime endTime) {
        Task deadline = new Deadline(description, dueDate, endTime);
        boolean isDuplicate = false;
        for(int i=0; i< taskArraylist.size(); i++){
            isDuplicate = this.getTaskListArray().get(i).isSameTask(deadline);
            if(isDuplicate){
                break;
            }
        }
        if(!isDuplicate) {
            taskArraylist.add(deadline);
        }
        return isDuplicate;
    }

    /**Add an event task to arraylist
     * @param description
     * @param dueDate
     * @param startTime
     * @param endTime
     */
    public Boolean addEvent(String description, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        Task event = new Event(description, false , dueDate, startTime, endTime);
        boolean isDuplicate = false;
        for(int i=0; i< taskArraylist.size(); i++){
            isDuplicate = this.getTaskListArray().get(i).isSameTask(event);
            if(isDuplicate){
                break;
            }
        }

        if(!isDuplicate) {
            taskArraylist.add(event);
        }

        return isDuplicate;
    }

    /**
     * Print out all task in array list
     * @param type
     * @return
     */
    public String showAllTask(String type) {
        ArrayList<Task> taskList;
        String allTask = "";

        if (type.equals("find")) {
            taskList = findTaskArraylist;
        } else {
            taskList = taskArraylist;
        }

        if(taskList.size() >0) {
            if(type.equals("find")) {
                allTask = ui.printFindHeader();
            }else{
                allTask = ui.printListHeader();
            }

            for (int i = 0; i < taskList.size(); i++) {
                allTask = allTask + "\n" + ui.printTask(i, taskList.get(i));
            }
        }else{
            allTask = ui.printNoTaskMessage();
        }
        return allTask;
    }

    /** Update specific task to completed
     * @param index  position(index) of task in array list
     * @return
     */
    public String markAsDone(int index) {
        taskArraylist.get(index).setCompleted();
        taskArraylist.get(index).setCompleted();
        return ui.displayDoneTaskMessage(taskArraylist.get(index));
    }

    /** Delete specific task given the index of task in array list
     * @param index  position(index) of task in array list
     * @return
     */
    public String deleteTask(int index) {
        String output = ui.displayDeletedTaskMessage(taskArraylist.get(index));
        taskArraylist.remove(index);
        return output;
    }

    /** Return arraylist of task
     * @return arraylist of task
     */
    public ArrayList<Task> getTaskListArray() {
        return this.taskArraylist;
    }

    /** Set current array list of task to given array list from parameter
     * @param al array list of task
     */
    public void setTaskArraylist (ArrayList<Task> al) {
        this.taskArraylist = al;
    }

    /** Find task in array list of task
     * @param input
     * @param t tasklist
     * @return
     */
    public String findTask(String input, TaskList t) {
        findTaskArraylist.clear();
        taskArraylist = t.getTaskListArray();
        for (int i = 0; i < taskArraylist.size(); i++) {
            if (taskArraylist.get(i).getTaskName().contains(input)) {
                findTaskArraylist.add(taskArraylist.get(i));
            }
        }
        return showAllTask("find");
    }

    /** Return specific task given the index of task in array list
     * @param index position(index) of task in array list
     * @return
     */
    public Task getTask(int index) {
        return taskArraylist.get(index);
    }
}
