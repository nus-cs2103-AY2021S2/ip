import java.util.List;

public class Ui {
    /**
     * Prints the logo and welcome messages.
     */
    void showWelcome() {
        String logo = "          ____                               \n" +
                "        ,'  , `.                                   \n" +
                "     ,-+-,.' _ |                                   \n" +
                "  ,-+-. ;   , ||                                   \n" +
                " ,--.'|'   |  ;|                                   \n" +
                "|   |  ,', |  ':  ,--.--.        .--,    ,--.--.   \n" +
                "|   | /  | |  || /       \\     /_ ./|   /       \\  \n" +
                "'   | :  | :  |,.--.  .-. | , ' , ' :  .--.  .-. | \n" +
                ";   . |  ; |--'  \\__\\/: . ./___/ \\: |   \\__\\/: . . \n" +
                "|   : |  | ,     ,\" .--.; | .  \\  ' |   ,\" .--.; | \n" +
                "|   : '  |/     /  /  ,.  |  \\  ;   :  /  /  ,.  | \n" +
                ";   | |`-'     ;  :   .'   \\  \\  \\  ; ;  :   .'   \\\n" +
                "|   ;/         |  ,     .-./   :  \\  \\|  ,     .-./\n" +
                "'---'           `--`---'        \\  ' ; `--`---'    \n" +
                "                                 `--` ";

        System.out.println(logo);

        System.out.println("    ____________________________________");
        System.out.println("    Hello, I'm Maya! \n    What can I do for you?");
        System.out.println("    ____________________________________");
    }

    /**
     * Prints the line separator.
     */
    void showLine() {
        System.out.println("    ____________________________________");
    }

    /**
     * Prints the exit message.
     */
    void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     */
    void showError(String err) {
        System.out.println("    " + err);
    }

    /**
     * Prints the given list argument.
     *
     * @param list the List<Task> to be displayed.
     * @param isSearch a boolean representing whether
     *                 this command is a search command.
     */
    void showList(List<Task> list, boolean isSearch) {
        if (isSearch) {
            if (list.isEmpty()) {
                System.out.println("    Sorry, there are no matching tasks in your list");
            } else {
                System.out.println("    Here are matching tasks in your list:");
            }
        } else {
            System.out.println("    Here are the tasks in your list:");
        }
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("    %d.%s\n", i, list.get(i - 1));
        }
    }
}
