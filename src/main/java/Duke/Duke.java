package duke;

import java.time.LocalDateTime;

import duke.common.Command;
import duke.common.Response;
import duke.exception.DuplicateTask;
import duke.exception.EmptyDescription;
import duke.exception.InvalidTaskNumber;
import duke.exception.InvalidTypeOfTask;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Duke program maintains storage for user to track tasks.
 * Able to perform add, delete, markasDone, list, find tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Initialise Duke program.
     */
    public Duke() {
        storage = new Storage();
        taskList = storage.load();
    }

    /**
     * Saves taskList changes to Storage.
     */
    public void save() {
        storage.save(taskList.getTasks());
    }

    /**
     * Adds task to taskList.
     *
     * @param p Parser object.
     * @return String
     * @throws EmptyDescription
     * @throws InvalidTypeOfTask
     * @throws DuplicateTask
     */
    public String addTask(Parser p) throws EmptyDescription, InvalidTypeOfTask, DuplicateTask {
        String reply = "";
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
            if (taskList.detectDuplicates(newTask)) {
                throw new DuplicateTask();
            } else if (!taskList.detectDuplicates(newTask)) {
                taskList.add(newTask);
                String instructions = Response.ADD.toString() + newTask + "\n" + this.status();
                reply = instructions;
            } else {
                throw new DuplicateTask();
            }
        }
        return reply;
    }
    /**
     * Marks task as DONE.
     *
     * @param p
     * @return String
     * @throws InvalidTaskNumber
     */
    public String markAsDone(Parser p) throws InvalidTaskNumber {
        String reply = "";
        try {
            if (p.getDescription().equals("")) {
                throw new EmptyDescription(p.getTypeOfTask());
            }
            int i = Integer.parseInt(p.getDescription()) - 1;
            reply = taskList.markAsDone(i);
        } catch (EmptyDescription e) {
            System.out.println(e.toString());
        }
        return reply;
    }
    /**
     * Delete Task from list.
     *
     * @param p
     * @return String
     * @throws InvalidTaskNumber
     */
    public String deleteTask(Parser p) throws InvalidTaskNumber {
        int i = Integer.parseInt(p.getDescription()) - 1;
        return taskList.delete(i);
    }

    /**
     * Prints list.
     *
     * @return String
     */
    public String list() {
        return taskList.list();
    }
    /**
     * Locates tasks matched with keyword.
     *
     * @param parser
     */
    public String find(Parser parser) {
        String keyword = parser.getDescription();
        return taskList.find(keyword);
    }

    /**
     * Return current number of tasks.
     *
     * @return string
     */
    public String status() {
        return "Now you have " + taskList.getNumberOfTasks() + " tasks in the list.\n";
    }

    /**
     * Generate a response to user input on duke chat GUI.
     *
     * @param input
     * @return String
     */
    public String getResponse(String input) {

        Ui ui = new Ui(this);
        String response = ui.readCommand(input);
        return response;
    }


    /**
     * Greets user.
     */
    public String greet() {
        return Response.GREET.toString();
    }

    /**
     * Say bye bye
     */
    public String exit() {
        return Response.EXIT.toString();
    }
}
