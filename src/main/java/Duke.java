import static java.lang.System.exit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import storage.Storage;
import task.Deadlines;
import task.Events;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialise Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty(
                "user.dir") + "/data/", "duke.txt");
        try {
            this.taskList = storage.loadFromHardisk();
        } catch (Exception e) {
            System.out.println("Error loading from storage,"
                    + "starting with new tasklist");
            this.taskList = new TaskList();
        }
    }

    /**
     * Gives the corresponding output for the given task.
     *
     * @param input
     * @return output on the Ui based on what the user has typed
     */
    public Response getResponse(String input) {

        Response response;

        String[] tokens = input.split(" ", 2);

        String command = tokens[0];
        String argument = tokens.length == 2 ? tokens[1] : null;

        switch (command) {
        case "bye":
            response = executeByeCommand();
            break;
        case "list":
            response = executeListCommand();
            break;
        case "done":
            response = executeDoneCommand(argument);
            break;
        case "todo":
            response = executeTodoCommand(argument);
            break;
        case "deadline":
            response = executeDeadlineCommand(argument);
            break;
        case "event":
            response = executeEventCommand(argument);
            break;
        case "delete":
            response = executeDeleteCommand(argument);
            break;
        case "find":
            response = executeFindCommand(argument);
            break;
        default:
            response = new Response("OOPS!!! I'm sorry, "
                    + "but I don't know what that means :-(", false);
            break;
        }

        return response;
    }

    /**
     * Executes Bye Command.
     *
     * @return String to represent termination of the program
     */
    private Response executeByeCommand() {
        storage.saveToHardisk(taskList);
        return new Response("bye", true);
    }

    /**
     * Lists out all tasks.
     *
     * @return String to represent termination of the program
     */
    private Response executeListCommand() {

        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            sb.append("There are no tasks in your task list\n");
        } else {
            sb.append("Here are the tasks in your task list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(Integer.toString(i + 1) + ". "
                        + taskList.get(i).getTypeIcon()
                        + taskList.get(i).getStatusIcon() + " "
                        + taskList.get(i).getDescription() + "\n"
                );
            }
        }
        return new Response(sb.toString(), false);

    }

    /**
     * Executes Done Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeDoneCommand(String argument) {
        // check for correct number of arguments
        assert argument != null : "OOPS!!! The description of "
                + "a done cannot be empty.";
        // check if argument is an integer
        int taskId;
        try {
            taskId = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            return new Response("OOPS!!! The id of a done"
                    + " must be an integer.", false);
        }
        // check if integer is within bounds
        if (taskId >= taskList.size() || taskId < 0) {
            return new Response("OOPS!!! That is an invalid task id.", false);
        }

        Task task = taskList.get(taskId);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();

        sb.append("Nice! I've marked this task as done: \n"
                + task.getStatusIcon() + " "
                + task.getDescription()
        );

        return new Response(sb.toString(), false);
    }

    /**
     * Executes Todo Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeTodoCommand(String argument) {
        assert argument != null : "OOPS!!! The description of a "
                + "done cannot be empty.";
        String[] split = argument.split("/tag", 2);
        String description = split[0];
        String tag = split.length > 1 ? split[1].strip() : null;

        Task task = new Todo(description, tag);
        taskList.add(task);

        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list");

        return new Response(sb.toString(), false);
    }

    /**
     * Executes Deadline Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeDeadlineCommand(String argument) {
        assert argument != null : "OOPS!!! The description of a "
                + "deadline cannot be empty.";
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        StringBuilder sb = new StringBuilder();

        String[] split = argument.split("/tag", 2);
        String arg1 = split[0];
        String tag = split.length > 1 ? split[1].strip() : null;

        split = arg1.split("/by", 2);
        String description = split[0];
        String by = split.length > 1 ? split[1].strip() : null;

        if (by != null) {
            try {
                LocalDateTime.parse(by, formatter);
            } catch (Exception e) {
                return new Response("Incorrect data "
                        + "format: d/M/yyyy HHmm", false);
            }
        }


        Task task = new Deadlines(description, by, tag);
        taskList.add(task);

        sb.append("Got it. I have added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");

        return new Response(sb.toString(), false);
    }

    /**
     * Executes Events Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeEventCommand(String argument) {

        assert argument != null : "OOPS!!! The description of "
                + "an event cannot be empty.";
        String[] split = argument.split("/tag", 2);
        String arg1 = split[0];
        String tag = split.length > 1 ? split[1].strip() : null;

        split = arg1.split("/at", 2);
        String description = split[0];
        String at = split.length > 1 ? split[1].strip() : null;

        Task task = new Events(description, at, tag);
        taskList.add(task);

        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I have added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");

        return new Response(sb.toString(), false);
    }

    /**
     * Executes Delete Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeDeleteCommand(String argument) {
        assert argument != null : "OOPS!!! The description of a delete"
                + "cannot be empty.";

        int taskId;
        try {
            taskId = Integer.parseInt(argument.strip()) - 1;
        } catch (NumberFormatException e) {
            return new Response("OOPS!!! The id of a delete "
                    + "must be an integer.", false);
        }

        if (taskId >= taskList.size() || taskId < 0) {
            return new Response("OOPS!!! That is an invalid task id.",
                    false);
        }

        Task task = taskList.remove(taskId);
        StringBuilder sb = new StringBuilder();

        sb.append("Noted. I've removed this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");

        return new Response(sb.toString(), false);
    }

    /**
     * Executes find Command.
     *
     * @param argument command arguments
     * @return String to represent termination of the program
     */
    private Response executeFindCommand(String argument) {
        assert argument != null : "OOPS!!! The description of a"
                + "find cannot be empty.";

        TaskList subList = new TaskList();
        for (Task task : taskList.getList()) {
            String description = task.getDescription();
            String tag = task.getTag();
            if (tag != null && tag.contains(argument)) {
                subList.add(task);
            } else if (description.contains(argument)) {
                subList.add(task);
            }
        }

        StringBuilder sb = new StringBuilder();

        if (subList.size() == 0) {

            return new Response("There are no tasks that matches your search",
                    false);
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < subList.size(); i++) {
                sb.append(Integer.toString(i + 1) + ". "
                        + subList.get(i).getTypeIcon()
                        + subList.get(i).getStatusIcon() + " "
                        + subList.get(i).getDescription() + "\n"
                );
            }
        }

        return new Response(sb.toString(), false);
    }
    /**
     *  * App shuts down after sleeptime when bye is typed by user.
     */
    public void shutDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exit(0);
    }
}
