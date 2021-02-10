package tasklist;

import exceptions.InvalidArgumentException;
import format.Ui;
import tasks.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskArrayList.add(t);
    }

    // is used in junit tests
    // abstract away anything else that uses size directly on arraylist?
    public int size() {
        return this.taskArrayList.size();
    }

    // need to get rid of this get for OOP
    public Task get(int i) {
        return this.taskArrayList.get(i);
    }

    // todo rm
    public Task remove(int i) {
        return this.taskArrayList.remove(i);
    }

    public boolean isEmpty() {
        return this.taskArrayList.isEmpty();
    }



    /**
     * Adds task to array list and prints success message with task details
     * @param t task object to add
     */
    public String addTask(Task t) {
        taskArrayList.add(t);

        return Ui.formatMultiLineMessages(
                "Success. I've added this task:",
                t.toString()
        );
    }

    /**
     * Deletes a task in the list
     * @param i index of task to be deleted
     * @throws InvalidArgumentException
     */
    public String deleteTask(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskArrayList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskArrayList.size()));
        }

//        Ui.print(
//            new String[]{
//                "Got you. I've deleted this task:",
//                Ui.EXTRA_INDENT + taskArrayList.get(i - 1)
//            }
//        );
//
//        taskArrayList.remove(i - 1);

        return Ui.formatMultiLineMessages(
                "Got you. I've deleted this task:",
                taskArrayList.remove(i - 1)
        );
    }


    /**
     * Marks a task in the list done
     * @param i index of task to mark done
     * @throws InvalidArgumentException
     */
    public String markDone(int i) throws InvalidArgumentException {
        if (i < 1 || i > taskArrayList.size()) {
            throw new InvalidArgumentException(invalidNumErrMsg(i, 1, taskArrayList.size()));
        }

        taskArrayList.get(i - 1).markAsDone();

        // todo checkstyle doesn't allow 8 space formatting
//        Ui.print(
//            new String[]{
//                "Good work! I've marked this task done:",
//                Ui.EXTRA_INDENT + taskArrayList.get(i - 1)
//            }
//        );

        return Ui.formatMultiLineMessages(
                "Good work! I've marked this task done:",
                taskArrayList.get(i - 1)
        );
    }

    /**
     * Formats error message if invalid list index provided
     * @param i provided list index
     * @param min minimum valid index
     * @param max maximum valid index
     * @return error message
     */
    private static String invalidNumErrMsg(int i, int min, int max) {
        String errMsg = "Invalid task number given: " + i
                + ". Number needs to be between " + min + " and " + max + " (inclusive). ";
        return errMsg;
    }

    /**
     * Finds tasks whose description match a user-inputted string, and prints all
     * matching tasks.
     * @param s Search keyword, inputted by user
     */
    public String findTasks(String s) {
        TaskList filtered = new TaskList();
        for (Task t : taskArrayList) {
            if (t.getDescription().contains(s)) {
                filtered.add(t);
            }
        }
        return Ui.stringifyTaskList(filtered);
    }
}
