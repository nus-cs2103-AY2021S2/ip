import task.*;
import storage.Storage;
import ui.Ui;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.print.DocFlavor;


public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir") + "/data/", "duke.txt");
        this.taskList = storage.load();
    }

    public String getResponse(String input) {

        String response;

            String[] tokens = input.split(" ", 2);

            String command = tokens[0];
            String argument = tokens.length == 2 ? tokens[1] : null;

            if (command.equals("bye")) {
                response = executeByeCommand();

            } else if (command.equals("list")) {
                response = executeListCommand();

            } else if(command.equals("done")) {
                response = executeDoneCommand(argument);

            } else if (command.equals("todo")) {
                response = executeTodoCommand(argument);

            } else if (command.equals("deadline")) {
                response = executeDeadlineCommand(argument);

            } else if (command.equals("event")) {
                response = executeEventCommand(argument);

            } else if (command.equals("delete")) {
                response = executeDeleteCommand(argument);

            } else if (command.equals("find")) {
                response = executeFindCommand(argument);

            } else {
                response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }

        return response;
    }

    /**
     * Executes Bye Command
     *
     * @return String to represent termination of the program
     */
    private String executeByeCommand() {
        storage.save(taskList);
        return "bye";
    }

    /**
     * Lists out all tasks
     *
     * @return String to represent termination of the program
     */
    private String executeListCommand() {

        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            sb.append("There are no tasks in your task list\n");
        } else {
            sb.append("Here are the tasks in your task list:\n");
            for (int i = 0; i < taskList.size() ; i++ ) {
                sb.append(Integer.toString(i + 1) + ". "
                        + taskList.get(i).getTypeIcon()
                        + taskList.get(i).getStatusIcon() + " "
                        + taskList.get(i).getDescription() + "\n"
                );
            }
        }
        return sb.toString();
    }

    /**
     * Executes Done Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeDoneCommand(String argument) {
        // check for correct number of arguments
        if (argument == null) {
            return "OOPS!!! The description of a done cannot be empty.";
        }

        // check if argument is an integer
        int taskId;
        try {
            taskId = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            return "OOPS!!! The id of a done must be an integer.";
        }

        // check if integer is within bounds
        if (taskId >= taskList.size() || taskId < 0) {
            return "OOPS!!! That is an invalid task id.";
        }

        Task task = taskList.get(taskId);
        task.markAsDone();
        StringBuilder sb = new StringBuilder();

        sb.append("Nice! I've marked this task as done: \n"
                + task.getStatusIcon() + " "
                + task.getDescription()
        );

        return sb.toString();
    }

    /**
     * Executes Todo Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeTodoCommand(String argument) {
        if (argument == null) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }

        Task task = new Todo(argument);
        taskList.add(task);

        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list");
        return sb.toString();
    }

    /**
     * Executes Deadline Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeDeadlineCommand(String argument) {
        if (argument == null) {
            return "OOPS!!! The description of a deadline cannot be empty.";
        }

        StringBuilder sb = new StringBuilder();

        String[] split = argument.split("/by", 2);
        String description = split[0];
        String by = split.length > 1 ? split[1].strip() : null;
        Task task = new Deadlines(description, by);
        taskList.add(task);

        sb.append("Got it. I have added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Executes Events Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeEventCommand(String argument) {
        if (argument == null) {
            return "OOPS!!! The description of an event cannot be empty.";
        }

        String[] split = argument.split("/at", 2);
        String description = split[0];
        String at = split.length > 1 ? split[1] : null;
        Task task = new Events(description, at);
        taskList.add(task);

        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I have added this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");

        return sb.toString();
    }

    /**
     * Executes Delete Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeDeleteCommand(String argument) {
        if (argument == null) {
            return "OOPS!!! The description of a delete cannot be empty.";
        }

        int taskId;
        try {
            taskId = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            return "OOPS!!! The id of a delete must be an integer.";
        }

        if (taskId >= taskList.size() || taskId < 0) {
            return "OOPS!!! That is an invalid task id.";
        }

        Task task = taskList.remove(taskId);
        StringBuilder sb = new StringBuilder();

        sb.append("Noted. I've removed this task:\n");
        sb.append("  " + task.getTypeIcon() + task.getStatusIcon() + " "
                + task.getDescription() + "\n");
        sb.append("Now you have " + taskList.size() + " tasks in the list.");

        return sb.toString();
    }

    /**
     * Executes find Command
     *
     * @param argument  command arguments
     * @return String to represent termination of the program
     */
    private String executeFindCommand(String argument) {
        if (argument == null) {
            return "OOPS!!! The description of a find cannot be empty.";
        }

        TaskList subList = new TaskList();
        for (Task task: taskList.getList()) {
            String description = task.getDescription();
            if (description.contains(argument)) {
                subList.add(task);
            }
        }

        StringBuilder sb = new StringBuilder();

        if (subList.size() == 0) {
            return "There are no tasks that matches your search";
        } else {
            sb.append("Here are the matching tasks in your list:");
            for (int i = 0; i < subList.size() ; i++ ) {
                sb.append(Integer.toString(i + 1) + ". "
                        + subList.get(i).getTypeIcon()
                        + subList.get(i).getStatusIcon() + " "
                        + subList.get(i).getDescription() + "\n"
                );
            }
        }

        return sb.toString();
    }
}