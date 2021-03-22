import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Class that deals with the user input and pushes out the appropriate command
 */

public class Parser {

    Scanner sc;
    static boolean warning;

    public Parser() {
        this.sc = new Scanner(System.in);
        this.warning = false;
    }

    /**
     * method that deals with the user input
     *
     * @param tasks the TaskList obj that contains the current list
     * @param fm    the data.txt save file of the current user
     */
    Result readCommands(TaskList tasks, Storage fm, String input) {
        // splits the input string into 2 parts, separating the command and the info of the command.
        String[] commands = input.split(" ", 2);
        String command = commands[0];
        Result result = null;
            try {
                switch (command) {
                case "bye":
                    fm.saveFile(tasks);
                    ByeCommand byeCommand = new ByeCommand(sc);
                    result = byeCommand.execute();
                    Thread.sleep(500);
                    exit(0);
                    break;
                case "list":
                    ListCommand lstCmd = new ListCommand(tasks);
                    result = lstCmd.execute();
                    break;
                case "todo":
                    String info = commands[1];
                    AddCommand todo = new AddCommand(tasks, new ToDo(info));
                    result = todo.execute();
                    break;
                case "deadline":
                    String rest = commands[1];
                    String[] parts = rest.split("/by");
                    AddCommand deadline = new AddCommand(tasks, new Deadline(parts[0], parts[1]));
                    result = deadline.execute();
                    break;
                case "event":
                    String rest1 = commands[1];
                    String[] parts1 = rest1.split("/at");
                    AddCommand event = new AddCommand(tasks, new Event(parts1[0], parts1[1]));
                    result = event.execute();
                    break;
                case "done":
                    int number = Integer.valueOf(commands[1]);
                    DoneCommand dneCmd = new DoneCommand(tasks, number);
                    result = dneCmd.execute();
                    break;
                case "delete":
                    int no = Integer.valueOf(commands[1]);
                    DeleteCommand delCmd = new DeleteCommand(tasks, no);
                    result = delCmd.execute();
                    break;
                case "find":
                    String keyword = commands[1];
                    FindCommand fc = new FindCommand(tasks, keyword);
                    result = fc.execute();
                    break;
                case "clear":
                    ClearCommand clrCmd = new ClearCommand(tasks);
                    result = clrCmd.execute();
                    break;
                default:
                    result = new Result("Invalid command!");
                    warning = true;
                    break;
                }
            } catch (DukeException | InterruptedException E) {
                return new Result(E.getMessage());
            } catch (ArrayIndexOutOfBoundsException E) {
                return new Result("Input cannot be empty.");
            }
            return result;
    }
}
