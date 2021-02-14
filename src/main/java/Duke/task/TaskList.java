package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.common.Command;
import duke.common.Response;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTypeOfTask;
import duke.parser.ListParser;
import duke.parser.Parser;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
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

    /**
     * Adds task to tasklist.
     *
     * @param p
     * @throws EmptyDescription
     */
    public void add(Parser p) throws EmptyDescription, InvalidTypeOfTask {
        Command command = p.getCommand();
        String description = p.getDescription();
        if (description.equals("")) {
            throw new EmptyDescription(p.getTypeOfTask());
        } else {
            LocalDateTime time = p.getTime();
            Task newTask;
            switch (command) {
            case TODO:
                newTask = new Todo(description);
                break;
            case DEADLINE:
                newTask = new Deadline(description, time);
                break;
            case EVENT:
                newTask = new Event(description, time);
                break;
            default:
                throw new InvalidTypeOfTask();
            }
            // check for duplicate task input
            if (!detectDuplicates(newTask)) {
                tasks.add(newTask);
                String instructions = Response.ADD.toString() + newTask + "\n" + this.status();
                enclose(instructions);
            }
        }
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

    /**
     * Removes task from taskList.
     *
     * @param p
     */
    public void delete(Parser p) {
        int i = Integer.parseInt(p.getDescription()) - 1;
        Task task = tasks.get(i);
        assert task != null : "task should not be empty";
        tasks.remove(i);

        String instructions = Response.DELETE.toString() + task + "\n" + this.status();
        enclose(instructions);
    }

    /**
     * Marks task as DONE.
     *
     * @param p
     * @throws EmptyDescription
     */
    public void markAsDone(Parser p) {
        try {
            if (p.getDescription().equals("")) {
                throw new EmptyDescription(p.getTypeOfTask());
            }
            int i = Integer.parseInt(p.getDescription()) - 1;
            tasks.set(i, tasks.get(i).setDone());
            enclose(Response.DONE.toString() + tasks.get(i) + "\n");
        } catch (EmptyDescription e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Locates tasks matched with keyword.
     * @param parser
     */
    public void find(Parser parser) {
        String keyword = parser.getDescription();
        SearchList searchList = new SearchList();

        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getMsg();

            if (description.contains(keyword)) {
                searchList.add(tasks.get(i));
            }
        }
        searchList.list();
    }

    /**
     * Prints list.
     */
    public void list() {
        String msg = "";
        for (int i = 0; i < tasks.size(); i++) {
            msg += (i + 1) + "." + tasks.get(i) + "\n";
        }
        enclose(Response.LIST.toString() + msg);
    }

    /**
     * Prints output to user in generic format.
     */
    public void enclose(String reply) {
        System.out.println("---------------------------------------");
        System.out.println(reply);
        System.out.println("---------------------------------------\n");
    }

    public String status() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }
}
