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
    public void addToDo(String description) {
        taskArraylist.add(new ToDo(description));
    }

    /**Add a deadline task to arraylist
     * @param description
     * @param dueDate
     * @param endTime
     */
    public void addDeadline(String description, LocalDate dueDate, LocalTime endTime) {
        taskArraylist.add(new Deadline(description, dueDate, endTime));
    }

    /**Add an event task to arraylist
     * @param description
     * @param dueDate
     * @param startTime
     * @param endTime
     */
    public void addEvent(String description, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        taskArraylist.add(new Event(description, false , dueDate, startTime, endTime));
    }

    /**
     * Print out all task in array list
     * @param type
     */
    public void showAllTask(String type) {
        ArrayList<Task> taskList;

        if (type.equals("find")) {
            ui.printFindHeader();
            taskList = findTaskArraylist;
        } else {
            ui.printListHeader();
            taskList = taskArraylist;
        }
        for (int i = 0; i < taskList.size(); i++) {
            ui.printTask(i, taskList.get(i));
        }
        ui.displayLines();
    }

    /** Update specific task to completed
     * @param index  position(index) of task in array list
     */
    public void markAsDone(int index) {
        taskArraylist.get(index).setCompleted();
        ui.displayDoneTaskMessage(taskArraylist.get(index));
        taskArraylist.get(index).setCompleted();
    }

    /** Delete specific task given the index of task in array list
     * @param index  position(index) of task in array list
     */
    public void deleteTask(int index) {
        ui.displayDeletedTaskMessage(taskArraylist.get(index));
        taskArraylist.remove(index);
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
     */
    public void findTask(String input, TaskList t) {
        findTaskArraylist.clear();
        taskArraylist = t.getTaskListArray();
        for (int i = 0; i < taskArraylist.size(); i++) {
            if (taskArraylist.get(i).getTaskName().contains(input)) {
                findTaskArraylist.add(taskArraylist.get(i));
            }
        }
        showAllTask("find");
    }

    /** Return specific task given the index of task in array list
     * @param index position(index) of task in array list
     * @return
     */
    public Task getTask(int index) {
        return taskArraylist.get(index);
    }
}
