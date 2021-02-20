package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import ui.Ui;
/**
 * Class <code>TaskList</code> represents a list of <code>Task</code>object using <code>ArrayList</code>.
 * Contains <code>addTask</code> and <code>deleteTask</code> which can add/delete tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Find the specified <code>input</code> in the task list and
     * print out the task with matching description.
     *
     * @param input search parameter.
     */
    public String find(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            String curr = task.getDetails();
            if (curr.contains(input)) {
                result.add(task);
            }
        }
        return Ui.printSearch(result);
    }

    /**
     * Returns a string that informs user of the total tally of the task in the task list.
     *
     * @return tally of task in task list.
     */
    public String getTally() {
        return "     Currently you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Appends the specified <code>task</code> into the end of the task list.
     *
     * @param task task being added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.ADD_MSG + task + "\n" + this.getTally();
    }

    /**
     * Mark the task at the specified <code>index</code> in the task list as complete.
     *
     * @param index index of task.
     */
    public String completeTask(int index) {
        Task task = tasks.get(index - 1);
        task.setComplete();
        return Ui.MARK_MSG + "      " + task;
    }

    /**
     * Delete the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task
     */

    public String deleteTask(int index) {
        String task = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        return Ui.DELETE_MSG + task + "\n \n" + getTally();
    }

    /**
     * Returns the number of task in the task list.
     *
     * @return number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task.
     * @return task specified by the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Detects whether the added todo task of specified <code>description</code>
     * clashes with another todo task in the list.
     * @param description description of the task
     * @return true if another similar todo task is found in the task list, otherwise false.
     */
    public boolean compareTodo(String description) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean compareType = task.getType().equals("T");
            if (compareType && compareDetails) {
                return true;
            }
        }
        return false;
    }

    /**
     * Detects whether the added Deadline task of specified <code> description</code> and <code>dateAndTime</code>
     * clashes with another Deadline task in the list.
     * @param description description of task.
     * @param dateAndTime date and time of task.
     * @return true if another similar Deadline task is found in the task list, otherwise false.
     */
    public boolean compareDeadline(String description, String[] dateAndTime) {
        boolean isSame = false;
        for (int i = 0; i < tasks.size() && !isSame; i++) {
            Task task = tasks.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean isDeadline = task.getType().equals("D");
            String time, date;
            if (compareDetails && isDeadline) {
                time = ((Deadline) task).getTime();
                date = ((Deadline) task).getDate();
                try {
                    String inputDate = LocalDate.parse(dateAndTime[0])
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    String inputTime = LocalTime.parse(dateAndTime[1])
                            .format(DateTimeFormatter.ofPattern("hh:mm a"));
                    isSame = (inputTime.equals(time) && date.equals(inputDate));

                } catch (ArrayIndexOutOfBoundsException ex) {
                    isSame = (time == null);
                }
            }
        }
        return isSame;
    }

    /**
     * Detects whether the added Event task of specified <code> description</code> and <code>dateAndTime</code>
     * clashes with another Event task in the list.
     * @param description description of task.
     * @param dateAndTime date and time of task.
     * @return true if another similar Event task is found in the task list, otherwise false.
     */
    public boolean compareEvent(String description, String[] dateAndTime) {
        boolean isSame = false;
        for (int i = 0; i < tasks.size() && !isSame; i++) {
            Task task = tasks.get(i);
            boolean compareDetails = task.getDetails().equals(description);
            boolean isEvent = task.getType().equals("E");
            String time, date;
            if (compareDetails && isEvent) {
                time = ((Event) task).getTime();
                date = ((Event) task).getDate();
                try {
                    String inputDate = LocalDate.parse(dateAndTime[0])
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                    String inputTime = LocalTime.parse(dateAndTime[1])
                            .format(DateTimeFormatter.ofPattern("hh:mm a"));
                    isSame = (inputTime.equals(time) && date.equals(inputDate));
                } catch (Exception ex) {
                    isSame = (time == null);
                }
            }
        }
        return isSame;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
