package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList tasks;

    public Duke() {
        this.storage = new Storage("C:/Users/Jeremias/Documents/GitHub/ip/data/", "data.txt");
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList();
        storage.loadTaskList(tasks);
    }

    public static void main(String[] args) {
    }


    /**
     * Executes the command that the user inputs.
     *
     * @param input User input.
     */
    private String executeCommand(String input) {
        String command = parser.parseCommand(input);
        Command cmd = null;
        try {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                cmd = new AddTaskCommand(command, input, tasks);
            } else if (command.equals("list")) {
                cmd = new ListTaskCommand(command, input, tasks);
            } else if (command.equals("done")) {
                cmd = new DoneTaskCommand(command, input, tasks);
            } else if (command.equals("delete")) {
                cmd = new DeleteTaskCommand(command, input, tasks);
            } else if (command.equals("help")) {
                cmd = new HelpCommand(command, input, tasks);
            } else if (command.equals("find")) {
                cmd = new FindCommand(command, input, tasks);
            } else if (command.equals("bye")) {
                cmd = new ByeCommand(command, input, tasks);
            } else {
                throw new WrongCommandDukeException();
            }
        } catch (DukeException e) {
            return ui.printError(e);
        }
        String str = "";
        if (cmd != null) {
            str = cmd.execute();
            tasks = cmd.getTaskList();
            save();
        }
        return str;
    }

    /**
     * Saves the task list into the storage file.
     */
    private void save() {
        String str = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            str += tasks.getTask(i).formatToSave() + "\n";
        }
        storage.saveTaskList(str);
    }

    public static String greeting() {
        return new Ui().printGreeting();
    }

    public static String help() {
        return new Ui().printHelp();
    }

    public String getResponse(String input) {
        try {
            return executeCommand(input);
        } catch (Exception e) {
            return ui.printError(e);
        }
    }
}