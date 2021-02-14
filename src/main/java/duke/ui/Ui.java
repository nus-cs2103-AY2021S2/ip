package duke.ui;

import duke.tasks.TaskList;
import duke.parser.Parser;
import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;

import java.util.Scanner;

/**
 * Ui allows interactions with the user and prompts user for command
 */
public class Ui {
    private final Scanner sc;
    private boolean exit;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.exit = false;
    }

    public String greet() {
        String logo = " ____         _        \n"
                    + "|  _ \\ _   _| |  _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello from\n" + logo;
        greeting += "_____________________________________\n";
        greeting += "Hello! I'm Duke\n";
        greeting += "What can I do for you?\n";
        greeting += "______________________________________\n";
        return greeting;
    }

    public TaskList process(TaskList tasks, Parser parser) {
        if (parser.getTaskType().equals("bye")) {
            return new ByeCommand(tasks).execute();
        } else if (parser.getTaskType().equals("list")) {
            return new ListCommand(tasks).execute();
        } else if (parser.getTaskType().equals("done")) {
            return new DoneCommand(tasks, parser.getTaskIndex()).execute();
        } else if (parser.getTaskType().equals("delete")) {
            return new DeleteCommand(tasks, parser.getTaskIndex()).execute();
        } else if (parser.getTaskType().equals("find")) {
            return new FindCommand(tasks, parser.getDescription()).execute();
        } else if (parser.getTaskType().equals("todo")) {
            return new ToDoCommand(tasks, parser.getDescription()).execute();
        } else if (parser.getTaskType().equals("deadline")) {
            return new DeadlineCommand(tasks, parser.getDescription(), parser.getDueDate()).execute();
        } else if (parser.getTaskType().equals("event")) {
            return new EventCommand(tasks, parser.getDescription(), parser.getDueDate()).execute();
        }
        return tasks;
    }

    public String getOutput(TaskList taskList, Parser parser) {
        Command command;
        if (parser.getTaskType().equals("bye")) {
            command = new ByeCommand(taskList);
        } else if (parser.getTaskType().equals("list")) {
            command = new ListCommand(taskList);
        } else if (parser.getTaskType().equals("done")) {
            command = new DoneCommand(taskList, parser.getTaskIndex());
        } else if (parser.getTaskType().equals("delete")) {
            command = new DeleteCommand(taskList, parser.getTaskIndex());
        } else if (parser.getTaskType().equals("find")) {
            command = new FindCommand(taskList, parser.getDescription());
        } else if (parser.getTaskType().equals("todo")) {
            command = new ToDoCommand(taskList, parser.getDescription());
        } else if (parser.getTaskType().equals("deadline")) {
            command = new DeadlineCommand(taskList, parser.getDescription(), parser.getDueDate());
        } else if (parser.getTaskType().equals("event")) {
            command = new EventCommand(taskList, parser.getDescription(), parser.getDueDate());
        } else {
            return parser.getDescription();
        }
        return this.format(command.toString());
    }

    public String format(String response) {
        String result = "____________________________________________________________\n";
        result += response + "\n";
        result += "____________________________________________________________";
        return result;
    }
}
