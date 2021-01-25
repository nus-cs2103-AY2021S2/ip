import java.util.Scanner;

public class Duke {
    /** Stores list of tasks */
    TaskList list;

    public Duke() {
        greet();
        try {
            this.list = new TaskList();
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    public void executeCommand(Command command) throws DukeException {
        command.execute(this.list);
    }

    private void greet() {
        Ui.printWithStyle(new String[]{"Hello! I'm Duke", "What can I do for you?"});
    }

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
