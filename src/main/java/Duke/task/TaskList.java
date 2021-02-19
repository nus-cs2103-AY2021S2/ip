package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.common.Response;
import duke.parser.ListParser;


public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public int getNumberOfTasks() {
        return tasks.size();
    }


    /**
     * Adds task from storage to tasklist.
     *
     * @param p
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

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks for duplicate task input.
     *
     * @param newTask
     * @return isDuplicate
     */
    public Boolean detectDuplicates(Task newTask) {
        Boolean isDuplicate = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(newTask)) {
                isDuplicate = true;
                System.out.println("Input already exists. Please try again");
                break;
            }
        }
        return isDuplicate;
    }


    public String delete(int i) {
        Task task = tasks.get(i);
        assert task != null : "task should not be empty";
        tasks.remove(i);

        String reply = Response.DELETE.toString() + task + "\n" + this.status();
        return reply;
    }


    public String markAsDone(int i) {
        String reply = "";
        tasks.set(i, tasks.get(i).setDone());
        reply = Response.DONE.toString() + tasks.get(i) + "\n";
        return reply;
    }

    /**
     * Locates tasks matched with keyword.
     */
    public String find(String keyword) {
        SearchList searchList = new SearchList();

        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getMsg();

            if (description.contains(keyword)) {
                searchList.add(tasks.get(i));
            }
        }
        return searchList.list();
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
