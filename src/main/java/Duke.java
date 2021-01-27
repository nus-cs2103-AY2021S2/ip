import java.util.List;
import java.util.Scanner;

public class Duke {

    /** List of tasks added by the user */
    private static List<Task> tasks = Storage.getData();

    /**
     * Performs the specified action.
     *
     * @param command Command input by the user.
     * @throws InvalidCommandException If the command cannot be recognised.
     */
    public static void runCommand(String command) throws InvalidCommandException{
        if (command.equals("list")) {
            TaskList.printList(tasks);
        } else if (Parser.parseCommand(command).equals("done")) {
            int index = Parser.parseDoneIndex(command);
            TaskList.markDone(index, tasks);
        } else if (Parser.parseCommand(command).equals("todo")) {
            try {
                TaskList.addTodo(command, tasks);
            } catch (InvalidTodoException e) {
                Ui.printEmptyTodoMessage();
            }
        } else if (Parser.parseCommand(command).equals("deadline")) {
            try {
                TaskList.addDeadline(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                Ui.printInvalidDateFormatMessage();
            }
        } else if (Parser.parseCommand(command).equals("event")) {
            try {
                TaskList.addEvent(command, tasks);
            } catch (InvalidDateTimeFormatException e) {
                Ui.printInvalidDateFormatMessage();
            }
        } else if (Parser.parseCommand(command).equals("delete")) {
            TaskList.deleteTask(command, tasks);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            try {
                runCommand(command);
            } catch (InvalidCommandException e) {
                Ui.printInvalidCommandMessage();
            }
            command = scanner.nextLine();
        }
        scanner.close();
        Ui.printExitMessage();
    }
}