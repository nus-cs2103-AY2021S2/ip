package Duke;

import java.util.Scanner;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage("C:/Users/Jeremias/Documents/GitHub/ip/data/", "data.txt");
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Starts the Duke program.
     */
    private void run() {
        ui.printGreeting();
        storage.loadTaskList(tasks);
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                ui.printBye();
            } else {
                executeCommand(input);
                save();
            }
        }
    }

    /**
     * Executes the command given by the user input.
     * If it is an invalid command, it prints the exception faced.
     * @param input User input.
     */
    private void executeCommand(String input) {
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
            } else {
                throw new WrongCommandDukeException();
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
        cmd.execute();
        tasks = cmd.getTaskList();
    }

    /**
     * Saves the tasks in the task list into the storage file.
     */
    private void save() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.getTask(i).formatToSave() + "\n";
        }
        storage.saveTaskList(str);
    }
}