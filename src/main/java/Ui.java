import java.util.List;

public class Ui {
    /**
     * Prints the logo and welcome messages.
     */
    String showWelcome() {
        String logo = "          ____                               \n"
                + "        ,'  , `.                                   \n"
                + "     ,-+-,.' _ |                                   \n"
                + "  ,-+-. ;   , ||                                   \n"
                + " ,--.'|'   |  ;|                                   \n"
                + "|   |  ,', |  ':  ,--.--.        .--,    ,--.--.   \n"
                + "|   | /  | |  || /       \\     /_ ./|   /       \\  \n"
                + "'   | :  | :  |,.--.  .-. | , ' , ' :  .--.  .-. | \n"
                + ";   . |  ; |--'  \\__\\/: . ./___/ \\: |   \\__\\/: . . \n"
                + "|   : |  | ,     ,\" .--.; | .  \\  ' |   ,\" .--.; | \n"
                + "|   : '  |/     /  /  ,.  |  \\  ;   :  /  /  ,.  | \n"
                + ";   | |`-'     ;  :   .'   \\  \\  \\  ; ;  :   .'   \\\n"
                + "|   ;/         |  ,     .-./   :  \\  \\|  ,     .-./\n"
                + "'---'           `--`---'        \\  ' ; `--`---'    \n"
                + "                                 `--` ";

        return String.format(logo + "Hello, I'm Maya! \n    What can I do for you?");
    }

    /**
     * Prints the line separator.
     */
    String showLine() {
        return "    ____________________________________";
    }

    /**
     * Prints the given list argument.
     *
     * @param list the List of Task to be displayed.
     * @param isSearch a boolean representing whether
     *                 this command is a search command.
     */
    String showList(List<Task> list, boolean isSearch) {
        if (isSearch && list.isEmpty()) {
                return "Sorry, there are no matching tasks in your list";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

            for (int i = 1; i <= list.size(); i++) {
                sb.append(i + ". " + list.get(i - 1) + "\n");
            }

            System.out.println(sb.toString());
            return sb.toString();
        }
    }

    String showAddTask(Task task, int listSize) {
        return String.format("Got it. I've added this task:\n"
                + "%s\nNow you have %d tasks in the list.\n", task, listSize);
    }

    String showRemoveTask(Task task, int listSize) {
        return String.format("Noted. I've removed this task:\n"
                + "%s\nNow you have %d tasks in the list.\n", task, listSize);
    }

    String showDone(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Prints the exit message.
     */
    String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}
