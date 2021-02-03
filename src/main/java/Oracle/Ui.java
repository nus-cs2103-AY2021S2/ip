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

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("Oracle.Storage file could not be found, starting fresh database");
    }

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

    public void showGoodbye() {
        System.out.println("Very well, we shall meet again");
    }

    public String readCommand() {
        if (scanner.hasNextLine()){
            return scanner.nextLine();  // Read user input
        } else {
            return "bye";
        }
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println("You have forgotten quickly, but the Oracle.Oracle Remembers");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showMarkDone(int i, Task t) {
        System.out.println((i + 1) + ". " + t);
    }

    public void showIndexException(int size, String domain) {
        System.out.println("You only have " + size + " " + domain + ". Give a valid index");
    }

    public void showNumberFormatException(String domain) {
        System.out.println("Give The Oracle.Oracle the index of the " + domain);
    }

    public void showDeleteTask(int i, Task t) {
        System.out.println("Erased: " + (i + 1) + ". " + t);
    }

    public void showFormatException(String command) {
        String format = commandMap.get(command);
        System.out.printf("%s: %s%n", command.substring(0, command.length()-7), format);
    }

    public void showNewTask(int size, Task task) {
        System.out.println(size + ". " + task);
    }

    public void showFormatException() {
        for (String command : commandMap.keySet()) {
            showFormatException(command);
        }
    }
}
