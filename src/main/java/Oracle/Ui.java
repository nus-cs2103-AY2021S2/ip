package Oracle;

import Entry.Task;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    Map<String, String> commandMap = Map.of(
            "ExitCommand", "    bye",
            "ListCommand", "    list",
            "TodoCommand", "    todo {description}",
            "DeadlineCommand", "deadline {description} /{day} {month} {year} {hour}{minute}",
            "EventCommand", "   event {description} /{day} {month} {year} {hour}{minute}",
            "MarkDoneCommand", "done {taskIndex}",
            "DeleteCommand", "  delete {taskIndex}"
            );

    /**
     * Create a new Ui object
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Informs the user that the load store file could not be found
     */
    public void showLoadingError() {
        System.out.println("Oracle.Storage file could not be found, starting fresh database");
    }

    /**
     * Introduction message for the user. This is supposed to look like a 3D donut
     */
    public void showWelcome() {
        // INTRODUCTORY GREETINGS
        String logo =
                  "              $$$@@@@@@@\n"
                + "         ##########$$$$$$@@$\n"
                + "       #**!!!!!!!!**####$$$$$$#\n"
                + "     **!!==!=;;=;;!!!**###$$$$$$#\n"
                + "    **!!==;::~~::;;;;=!!*####$$$$##\n"
                + "   !!!!=;::~-,,,,--:;;=!!**########*\n"
                + "  !!!!=;:~,........-~:==!!**#######**\n"
                + " =!*!!=;:~,.........-:;=!!***######**\n"
                + " !***!!=:~,...     .-:;=!!****####***=\n"
                + " !*###**=;=..       -:;=!!**********!=\n"
                + ":!*###$##*=:       -:;=!!!********!!!=\n"
                + ":!##$$$$$$#*~     :;===!!*********!!=;\n"
                + "~=*#$$@@@@$$#*! ====!!!!!*****!!*!!==\n"
                + " ;!*#$$@@@$$##**!!!!!!!!!!*!*!!!!!==:\n"
                + " :;!*#$$$$$$###******!!!!!!!!!!!==;:\n"
                + "  ;!=!*#######******!!!!!!!!!===;;:,\n"
                + "   :;==!!********!!!!!!!!====;;;:~\n"
                + "    -:;!====!=!!!!!!!======;;::~,\n"
                + "      -~:;===;======;=;;;:::~-,\n"
                + "        .-~~::::;:::::~:~--.\n";
        System.out.println(logo + "\nGreetings Neo, what can the Oracle.Oracle do for you?");
    }

    /**
     * Terminating message for the user
     */
    public void showGoodbye() {
        System.out.println("Very well, we shall meet again");
    }

    /** This is our main interaction with the user, it employs a Scanner to get input from the user
     * @return Raw String of what the user typed in
     */
    public String readCommand() {
        if (scanner.hasNextLine()){
            return scanner.nextLine();  // Read user input
        } else {
            return "bye";
        }
    }

    /** showList hands the ListCommand
     * @param tasks the tasks, which can all print its own identity.
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println("You have forgotten quickly, but the Oracle.Oracle Remembers");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /** Shows the task which has just been marked "Done"
     * @param i the index of the Task
     * @param t the Task marked "Done"
     */
    public void showMarkDone(int i, Task t) {
        System.out.println((i + 1) + ". " + t);
    }

    /** Called when user inputs an index which is out of bounds
     * @param size max acceptable size of the underlying List
     * @param domain domain of the list, eg. "tasks"
     */
    public void showIndexException(int size, String domain) {
        System.out.println("You only have " + size + " " + domain + ". Give a valid index");
    }

    /** Called when the number indicated it not of the correct form
     * @param domain domain of underlying list, eg. "tasks"
     */
    public void showNumberFormatException(String domain) {
        System.out.println("Give The Oracle.Oracle the index of the " + domain);
    }

    /** Show recently deleted task
     * @param i index of deleted task
     * @param t deleted Task
     */
    public void showDeleteTask(int i, Task t) {
        System.out.println("Erased: " + (i + 1) + ". " + t);
    }

    /** Clarifies the format of the given command to the user
     * @param command Command which user typed in wrong format of, to be clarified
     */
    public void showFormatException(String command) {
        String format = commandMap.get(command);
        System.out.printf("%s: %s%n", command.substring(0, command.length()-7), format);
    }

    /** Show recently created Task
     * @param size index of the Task
     * @param task Task
     */
    public void showNewTask(int size, Task task) {
        System.out.println(size + ". " + task);
    }

    /**
     * Shows all the formats of the commands. Called when user types in rubbish.
     */
    public void showFormatException() {
        for (String command : commandMap.keySet()) {
            showFormatException(command);
        }
    }
}
