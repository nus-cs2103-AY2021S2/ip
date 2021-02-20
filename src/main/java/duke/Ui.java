package duke;

/**
 * Encompasses interactions with the user by returning Strings.
 */
public class Ui {
    /**
     * Returns the custom welcome message to the user.
     *
     * @return welcome message.
     */
    public static String showWelcome() {
        return "Hello from\n"
                + "____            _        \n"
                + "|  _  \\ _    _ |  | _____ \n"
                + "|  |  |  |  |  | |  | / / _ \\\n"
                + "|  |_|  |  |_|  |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Prints each Task in the list with its index, and remarks if the list is empty.
     *
     * @param tasks The existing list of tasks.
     * @return string of listed entries.
     */
    public static String printList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your list is empty!";
        } else {
            String result = "Here are the tasks in your list: \n";
            for (int i = 1; i <= tasks.size(); i++) {
                result = result.concat(i + "." + tasks.get(i - 1) + "\n");
            }
            return result;
        }
    }

    /**
     * Throws IllegalArgumentException if the first word of the command is wrong or absent.
     *
     * @param command User input provided.
     * @throws IllegalArgumentException if the first word of the command is wrong or absent.
     */
    public static void throwIllegalArgumentEx(String command) throws IllegalArgumentException {
        if (command.length() > 0) {
            throw new IllegalArgumentException("That is not a valid command!\n"
                    + "These are the possible commands:\n"
                    + "\"list\" \"done\" \"delete\" \"todo\" \"deadline\" \"event\" \"reminders\" \"find\" \"bye\" ");
        } else {
            throw new IllegalArgumentException("What are you trying to say?\n"
                    + "Please enter something ...");
        }
    }
}
