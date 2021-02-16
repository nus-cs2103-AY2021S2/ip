package duke.tasklist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.UI;

/**
 * Interact with task class to facilitate the creation, deletion and update of task
 */
public class TaskList {

    private static ArrayList<Task> taskArraylist = new ArrayList<Task>();
    private static ArrayList<Task> findTaskArraylist = new ArrayList<Task>();

    private static UI ui = new UI();

    /**
     * Add a todo task into the task list
     * @param description task description
     * @return Boolean true if the task is duplicated in the task list
     */
    public Boolean addToDo(String description) {

        Task toDo = new ToDo(description);
        boolean isDuplicate = false;
        for (int i = 0; i < taskArraylist.size(); i++) {
            isDuplicate = this.getTaskListArray().get(i).isSameTask(toDo);
            if (isDuplicate) {
                break;
            }
        }
        if (!isDuplicate) {
            taskArraylist.add(toDo);
        }
        return isDuplicate;
    }

    /**
     * Add a deadline task into the task list
     * @param description task description
     * @param dueDate due date
     * @param endTime end time
     * @return Boolean true if the task is duplicated in the task list
     */
    public Boolean addDeadline(String description, LocalDate dueDate, LocalTime endTime) {
        Task deadline = new Deadline(description, dueDate, endTime);
        boolean isDuplicate = false;
        for (int i = 0; i < taskArraylist.size(); i++) {
            isDuplicate = this.getTaskListArray().get(i).isSameTask(deadline);
            if (isDuplicate) {
                break;
            }
        }
        if (!isDuplicate) {
            taskArraylist.add(deadline);
        }
        return isDuplicate;
    }

    /**
     * Add an event task into the task list
     * @param description task description
     * @param dueDate due date
     * @param startTime start time
     * @param endTime end time
     * @return Boolean true if the task is duplicated in the task list
     */
    public Boolean addEvent(String description, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        Task event = new Event(description, false , dueDate, startTime, endTime);
        boolean isDuplicate = false;
        for (int i = 0; i < taskArraylist.size(); i++) {
            isDuplicate = this.getTaskListArray().get(i).isSameTask(event);
            if (isDuplicate) {
                break;
            }
        }

        if (!isDuplicate) {
            taskArraylist.add(event);
        }

        return isDuplicate;
    }

    /**
     * Display all task in the task list in their string representation
     * @param commandType type of command
     * @return
     */
    public String showAllTask(String commandType) {
        ArrayList<Task> taskList;
        String allTask = "";

        if (commandType.equals("find")) {
            taskList = findTaskArraylist;
        } else {
            taskList = taskArraylist;
        }

        if (taskList.size() > 0) {
            if (commandType.equals("find")) {
                allTask = ui.displayFindHeader();
            } else {
                allTask = ui.displayListHeader();
            }

            for (int i = 0; i < taskList.size(); i++) {
                allTask = allTask + "\n" + ui.displayTask(i, taskList.get(i));
            }
        }else {
            allTask = ui.DisplayNoTaskMessage();
        }
        return allTask;
    }

    /**
     *  Update the task status of a specific task to completed given the index of a task
     * @param index position of task in the task list
     * @return
     */
    public String markAsDone(int index) {
        taskArraylist.get(index).setCompleted();
        taskArraylist.get(index).setCompleted();
        return ui.displayDoneTaskMessage(taskArraylist.get(index));
    }

    /**
     * Delete a specific task given the index of a task in the task list
     * @param index position of task in list
     * @return
     */
    public String deleteTask(int index) {
        String output = ui.displayDeletedTaskMessage(taskArraylist.get(index));
        taskArraylist.remove(index);
        return output;
    }

    /**
     * Returns the list containing all task
     * @return list of task
     */
    public ArrayList<Task> getTaskListArray() {
        return this.taskArraylist;
    }

    /** Change the current array list of task to given array list
     * @param TaskArrayList array list of task
     */
    public void setTaskList(ArrayList<Task> TaskArrayList) {
        this.taskArraylist = TaskArrayList;
    }

    /**
     * Find tasks that matches or contains keywords searched by the user
     * @param input
     * @param list task list
     * @return String string representation of specific task
     */
    public String findTask(String input, TaskList list) {
        findTaskArraylist.clear();
        taskArraylist = list.getTaskListArray();
        for (int i = 0; i < taskArraylist.size(); i++) {
            if (taskArraylist.get(i).getTaskDescription().contains(input)) {
                findTaskArraylist.add(taskArraylist.get(i));
            }
        }
        return showAllTask("find");
    }

    /**
     * Returns a specific task given the index of the task in the task list
     * @param index index of task in the task list
     * @return task specific task given the index
     */
    public Task getTask(int index) {
        return taskArraylist.get(index);
    }
}
