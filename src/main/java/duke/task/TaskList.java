package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.ui.Ui;


public class TaskList {
    private static List<Task> tasks = new ArrayList<>();


    public static void addTask(Task t) {
        tasks.add(t);
    }


    /**
     * This method marks a task in the list as done.
     *
     * @param i index labelling of the task in list.
     */
    public static void markDone(int i) {
        try {
            Task t = tasks.get(i - 1);
            tasks.get(i - 1).markDone();
            Ui.doneTask(t);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
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
    public static void listTasks() {
        System.out.println(Ui.UPPER);
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
        }
        System.out.println(Ui.LOWER);
    }

    /**
     * Delete a task in the task list with the given index.
     *
     * @param i the index labelling of the task.
     */
    public static void deleteTask(int i) {
        try {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            Ui.deleteTask(t);
            for (Task task : tasks) {
                if (task.getIndex() > i) {
                    task.changeIndex(task.getIndex() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
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
