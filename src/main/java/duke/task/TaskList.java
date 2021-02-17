package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.ui.ErrorBox;
import duke.ui.Ui;

/**
 * This class encapsulate a list of Task.
 */
public class TaskList {
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Add a user task to the list.
     * @param task user task keyed in.
     */
    public static void addTask(Task task) {
        tasks.add(task);
        assert tasks.size() >= 1 : "Task is not added successfully";
    }


    /**
     * This method marks a task in the list as done.
     *
     * @param i index labelling of the task in list.
     */
    public static String markDone(int i) {
        String response = "";
        try {
            Task t = tasks.get(i - 1);
            tasks.get(i - 1).markDone();
            assert t.getDoneStatus().equals("X") : "Task is not marked as done successfully";
            response = Ui.doneTask(t);
        } catch (IndexOutOfBoundsException e) {
            ErrorBox.display(Ui.TASK_ERROR);
        }
        return response;
    }

    /**
     * This method finds all relevant tasks according to the keyword.
     *
     * @param keyword keyword that the user want to search.
     * @return the matched tasks according to the keyword in String.
     */
    public static String findTasks(String keyword) {
        int index = 1;
        StringBuilder response = new StringBuilder();

        //Finds matching tasks in task list and appends to the response.
        for (Task task : tasks) {
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                String prefix = task.toString().substring(0, 6);
                response.append(prefix + " " + index + ". " + task.getTaskName() + task.getDate() + "\n");
                index++;
            }
        }

        boolean hasMatchTask = response.capacity() > 0;
        if (!hasMatchTask) {
            response.append("~~~~~Sorry ah, no match.~~~~~");
        } else {
            response.insert(0, "Here are the matches for your search: \n");
        }

        return response.toString();
    }

    /**
     * This method displays all the tasks in the list.
     */
    public static String listTasks() {
        StringBuilder response = new StringBuilder();

        if (tasks.size() == 0) {
            response.append(Ui.EMPTY_LIST);
        }

        //Lists all tasks in the task list and appends them to the response.
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
            response.append(task.toString() + "\n");
        }

        return response.toString();
    }

    /**
     * This method delete a task in the task list with the given index.
     *
     * @param i the index labelling of the task.
     */
    public static String deleteTask(int i) {
        String response = "";
        try {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            response = Ui.deleteTask(t);
            for (Task task : tasks) {
                if (task.getIndex() > i) {
                    task.changeIndex(task.getIndex() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            ErrorBox.display(Ui.TASK_ERROR);
        }
        return response;
    }


    public static void clearAllTasks() {
        tasks.clear();
    }

    public static List<Task> getTaskList() {
        return tasks;
    }


    /**
     * Obtain the total number of tasks in the list.
     * @return the number of tasks in the list.
     */
    public static int getTasksSize() {
        return tasks.size();
    }

}
