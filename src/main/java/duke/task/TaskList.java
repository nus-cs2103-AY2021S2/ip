package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for storing the taskList which stores the tasks
 */
public class TaskList {
    ArrayList<Task> Tasks;

    /**
     * Initializes the task list as an arraylist
     */
    public TaskList() {
        Tasks = new ArrayList<Task>();
    }

    /**
     * Adds the task to the end of the list
     *
     * @param task
     * @return Success message of adding
     */
    public String addTask(Task task) {
        Tasks.add(task);
        String out = "Got it! Added: \n" + task + "\n"
                + "Now you have " + numOfTasks() + " items in your list";
        return out;
    }

    /**
     * This method silently adds the task to the end of the list
     *
     * @param task
     */
    public void silentAdd(Task task) {
        Tasks.add(task);
    }


    /**
     * This method finds the tasks if they match the keyword
     * As an additional feature, partial match will also yeild results
     * Integrate BetterSearch into the existing findTask method
     *
     * @param keyword
     * @return
     */
    public String findTask(String keyword) {
        String out = "";
        TaskList matches = new TaskList();
        for (Task t : Tasks) {
            String task = t.getTaskName();
            String[] taskName = task.split(" ");
            if (keyword.equals(taskName[0]) || keyword.equals(taskName[1])
                    || keyword.equals(taskName[2])) {
                matches.silentAdd(t);
            } else if (taskName[0].contains(keyword) || taskName[1].contains(keyword)
                    || taskName[2].contains(keyword)) {
                matches.silentAdd(t);
            } else if (task.contains(keyword)) {
                matches.silentAdd(t);
            }
        }
        if (matches.numOfTasks() > 0) {
            out += "We have found the following items\n";
            out += matches.getAllTasks();
        } else {
            out += "Sorry! Could not find any matches :(";
        }
        return out;
    }

    /**
     * Deletes the task at idx
     *
     * @param idx
     * @return Message upon completion of the task
     */
    public String DeleteTask(int idx) {
        assert idx > 0 : "Invalid Index!";
        String out = "";
        if (idx <= Tasks.size()) {
            out += "Noted. Item removed: \n" + Tasks.get(idx - 1) + "\n";
            Tasks.remove(idx - 1);
            out += "Now you have " + numOfTasks() + " items in your list";
        } else {
            out += "Sorry! The system does not have that many elements. Try again :(";
        }
        return out;
    }

    /**
     * Marks task at idx as done
     *
     * @param idx
     * @return Message upon the completion of task
     */
    public String markAsDone(int idx) {
        assert idx > 0 : "Invalid Index!";
        String out;
        if (idx <= Tasks.size()) {
            Tasks.get(idx - 1).markDone();
            out = "Task " + idx + " is complete:\n" + Tasks.get(idx - 1);
        } else {
            out = "Sorry! The system does not have that many elements. Try again :(";
        }
        return out;
    }

    /**
     * Gets the number of tasks
     *
     * @return number of tasks
     */
    public int numOfTasks() {
        return Tasks.size();
    }

    /**
     * Gets the task at a given index
     *
     * @param idx
     * @return task at idx
     */
    public Task getTask(int idx) {
        assert idx >= 0 : "Invalid Index!";
        return Tasks.get(idx);
    }

    /**
     * Checks the tasks' due dates with current date
     * If the the task is after/on current date, push reminder
     *
     * @return List of all the due tasks
     */
    public String getReminders() {
        String out = "These are the due tasks: \n";
        TaskList dues = new TaskList();
        LocalDate today = LocalDate.now();
        for (Task t : Tasks) {
            if (!t.isDone()) {
                if (t.getType().toLowerCase().equals("deadline")
                        || t.getType().toLowerCase().equals("event")) {
                    if (today.compareTo(t.getDate()) <= 0) {
                        dues.silentAdd(t);
                    }
                }
            }
        }
        out += dues.getAllTasks();
        return out;
    }

    /**
     * The method returns all the tasks in a String representation
     *
     * @return all the tasks
     */
    public String getAllTasks() {
        String out = "Contents: \n";
        for (int j = 0; j < Tasks.size(); j++) {
            if (Tasks.get(j) instanceof TodoTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else if (Tasks.get(j) instanceof DeadlineTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else if (Tasks.get(j) instanceof EventTask) {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            } else {
                out += j + 1 + "." + Tasks.get(j) + "\n";
            }
        }
        return out;
    }
}
