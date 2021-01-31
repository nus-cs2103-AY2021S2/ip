package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.exceptions.DukeException;
import duke.ui.Ui;



public class TaskList {
    private static List<Task> taskList = new ArrayList<>();

    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }


    public static void addTask(Task t) {
        taskList.add(t);
    }


    /**
     * This method marks a task in the list as done.
     * @param i index labelling of the task in list.
     */
    public static void markDone(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.get(i - 1).markDone();
            Ui.doneTask(t);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    /**
     * This method finds all relevant tasks according to the keyword.
     * @param keyword keyword that the user want to search.
     * @return the matched tasks according to the keyword in String.
     */
    public static String find(String keyword) {
        int index = 1;
        StringBuilder sb = new StringBuilder("");
        for (Task task : taskList) {
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
    public static void list() {
        System.out.println(Ui.UPPER);
        for (Task task : taskList) {
            if (task == null) {
                break;
            }
            System.out.println(task);
        }
        System.out.println(Ui.LOWER);
    }

    /**
     * Delete a task in the task list with the given index.
     * @param i the index labelling of the task.
     */
    public static void delete(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.remove(i - 1);
            Ui.deleteTask(t);
            for (Task task : taskList) {
                if (task.getIndex() > i) {
                    task.changeIndex(task.getIndex() - 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    public static void clearAllTask() {
        taskList.clear();
    }

    public static List<Task> getTaskList() {
        return taskList;
    }

}
