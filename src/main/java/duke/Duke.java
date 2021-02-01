package duke;

import java.util.Scanner;

import duke.command.Command;

public class Duke {
    /** Stores list of tasks */
    private TaskList list;

    /**
     * Initializes a Duke object with an empty TaskList.
     */
    public Duke() {
        try {
            this.list = new TaskList();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        greet();
    }

    public void executeCommand(Command command) throws DukeException {
        command.execute(this.list);
    }

    private void greet() {
        Ui.printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }

    /**
     * Initializes a Duke object to execute commands that are passed in by the user.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Command command;
            try {
                command = Parser.handleInput(input);
                duke.executeCommand(command);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                Ui.printWithStyle(e.getMessage());
            }
        }
    }
}
