package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.ui.ErrorBox;
import duke.ui.Ui;


public class TaskList {
    private static List<Task> tasks = new ArrayList<>();


    public static void addTask(Task t) {
        tasks.add(t);
        assert tasks.size() >= 1 : "Task is not added successfully";
    }


    /**
     * This method marks a task in the list as done.
     *
     * @param i index labelling of the task in list.
     */
    public static String markDone(int i) {
        try {
            Task t = tasks.get(i - 1);
            tasks.get(i - 1).markDone();
            assert t.getDoneStatus() == "X" : "Task is not marked as done successfully";
            String result = Ui.doneTask(t);
            return result;
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
            ErrorBox.display(e.getMessage());
        }
        return "";
    }

    /**
     * This method finds all relevant tasks according to the keyword.
     *
     * @param keyword keyword that the user want to search.
     * @return the matched tasks according to the keyword in String.
     */
    public static String findTasks(String keyword) {
        int index = 1;
        StringBuilder sb = new StringBuilder("");
        for (Task task : tasks) {
            String taskName = task.getTaskName();
            if (taskName.contains(keyword)) {
                String prefix = task.toString().substring(0, 6);
                sb.append(prefix + " " + index + ". " + task.getTaskName() + task.getDate() + "\n");
                index++;
            }
        }
        if (sb.equals("")) {
            sb.append("~~~~~Sorry ah, no match.~~~~~");
        } else {
            sb.insert(0, "Here are the matches for your search: \n");
        }

        return sb.toString();

    }


    /**
     * Display all the tasks in the list.
     */
    public static String listTasks() {
        StringBuilder sb = new StringBuilder("");

        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
            sb.append(task.toString() + "\n");
        }

        return sb.toString();
    }

    /**
     * Delete a task in the task list with the given index.
     *
     * @param i the index labelling of the task.
     */
    public static String deleteTask(int i) {
        try {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            Ui.deleteTask(t);
            for (Task task : tasks) {
                if (task.getIndex() > i) {
                    task.changeIndex(task.getIndex() - 1);
                }
            }
            return Ui.deleteTask(t);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
            ErrorBox.display(e.getMessage());
            return "";
        }
    }

    public static void clearAllTask() {
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
