import java.util.Scanner;

/**
 * Class that deals with the user input and pushes out the appropriate command
 */

public class Parser {

    Scanner sc;

    public Parser() {
        this.sc = new Scanner(System.in);
    }

    /**
     * method that deals with the user input
     *
     * @param tasks the TaskList obj that contains the current list
     * @param fm    the data.txt save file of the current user
     */
    String readCommands(TaskList tasks, Storage fm, String input) {
        // splits the input string into 2 parts, separating the command and the info of the command.
        String[] commands = input.split(" ", 2);
        String command = commands[0];
        String output = "";
            try {
                switch (command) {
                case "bye":
                    fm.saveFile(tasks);
                    output += terminate();
                    break;
                case "list":
                    output += tasks.listItems();
                    break;
                case "todo":
                    String info = commands[1];
                    output += tasks.add(new ToDo(info));
                    break;
                case "deadline":
                    String rest = commands[1];
                    String[] parts = rest.split("/by");
                    output += tasks.add(new Deadline(parts[0], parts[1]));
                    break;
                case "event":
                    String rest1 = commands[1];
                    String[] parts1 = rest1.split("/at");
                    output += tasks.add(new Event(parts1[0], parts1[1]));
                    break;
                case "done":
                    int number = Integer.valueOf(commands[1]);
                    output += tasks.done(number);
                    break;
                case "delete":
                    int no = Integer.valueOf(commands[1]);
                    output += tasks.delete(no);
                    break;
                case "find":
                    String keyword = sc.nextLine();
                    FindCommand fc = new FindCommand();
                    output += fc.find(tasks, keyword);
                    break;
                default:
                    break;
                }
            } catch (DukeException E) {
                return E.getMessage();
            }
            return output;
    }

    /**
     * Method that exits the program.
     */
    String terminate() {
        sc.close();
        return "Goodbye. See you again!";
    }
}
