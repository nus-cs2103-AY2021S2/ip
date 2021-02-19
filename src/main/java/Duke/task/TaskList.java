package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.common.Response;
import duke.exception.InvalidTaskNumber;
import duke.parser.ListParser;

/**
 * Represents a list of task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for arraylist of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns number of tasks in taskList.
     *
     * @return int
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
    /**
     * Adds task from storage to tasklist.
     *
     * @param p ListParser.
     */
    public void populate(ListParser p) {
        String typeOfTask = p.getTypeOfTask();
        Boolean isDone = p.getIsDone();
        String description = p.getDescription();
        LocalDateTime time = p.getTime();

        switch(typeOfTask) {
        case "T":
            tasks.add(new Todo(description, isDone));
            break;
        case "D":
            tasks.add(new Deadline(description, isDone, time));
            break;
        case "E":
            tasks.add(new Event(description, isDone, time));
            break;
        default:
            System.out.println("wrong typeOfTask");
        }
    }

    /**
     * Adds task to taskList.
     *
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks for duplicate task input.
     *
     * @param newTask
     * @return isDuplicate Boolean.
     */
    public Boolean detectDuplicates(Task newTask) {
        Boolean isDuplicate = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(newTask)) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    /**
     * Deletes task from taskList.
     *
     * @param i
     * @return String
     * @throws InvalidTaskNumber
     */
    public String delete(int i) throws InvalidTaskNumber {
        if (i >= tasks.size() || i < 0) {
            throw new InvalidTaskNumber(tasks.size());
        } else {
            Task task = tasks.get(i);
            assert task != null : "task should not be empty";
            tasks.remove(i);

            String reply = Response.DELETE.toString() + task + "\n" + this.status();
            return reply;
        }
    }

    /**
     * Marks Task as done.
     *
     * @param i
     * @return String
     * @throws InvalidTaskNumber
     */
    public String markAsDone(int i) throws InvalidTaskNumber {
        String reply = "";
        if (i >= tasks.size() || i < 0) {
            throw new InvalidTaskNumber(tasks.size());
        } else {
            tasks.set(i, tasks.get(i).setDone());
            reply = Response.DONE.toString() + tasks.get(i) + "\n";
        }
        return reply;
    }
    /**
     * Locates tasks matched with keyword.
     * @param keyword
     * @return String
     */
    public String find(String keyword) {
        SearchList searchList = new SearchList();
        String reply = "";

        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getMsg();

            if (description.contains(keyword)) {
                searchList.add(tasks.get(i));
            }
        }
        if (searchList.list().equals("")) {
            reply = "Keyword cannot be found in tasklist";
        } else {
            reply = searchList.list();
        }
        return reply;
    }

    /**
     * Prints list.
     */
    public String list() {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        return Response.LIST.toString() + msg;
    }

    public String status() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }
}
