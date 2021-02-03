package duke.ui;

import java.util.Scanner;

import duke.Apollo;
import duke.util.Parser;

/**
 * Handles Ui related input and output tasks.
 */
public class Ui {

    public static final String INDENTATION = "    ";

    /**
     * Displays a message with an indentation of 4 spaces.
     * @param s Message to be displayed.
     */
    public static void showMessageWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void showLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    /**
     * Displays multiple messages between horizontal line separators.
     * @param strings Strings of Messages.
     */
    public static void showMessageBetweenLines(String... strings) {
        showLine();

        for (int i = 0; i < strings.length; i++) {
            showMessageWithIndentation(strings[i]);
        }

        showLine();
    }


    /**
     * Displays a message specific to the Task that has been added.
     * @param taskInfo Information of the Task.
     * @param numberOfTasks Number of Tasks left in the TaskList.
     */
    public static void showTaskAddedText(String taskInfo, Integer numberOfTasks) {
        showMessageBetweenLines("Got it. I've added this task:",
                INDENTATION + taskInfo,
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }

    /**
     * Displays a message specific to the Task that has been deleted.
     * @param taskInfo Information of the Task.
     * @param numberOfTasks Number of Tasks left in the TaskList.
     */
    public static void showTaskDeletedText(String taskInfo, Integer numberOfTasks) {
        showMessageBetweenLines("Noted. I've removed this task:",
                INDENTATION + taskInfo,
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }

    /**
     * Displays an error message.
     * @param s Message to be displayed.
     */
    public static void showErrorMessage(String s) {
        showMessageBetweenLines(s);
    }

    /**
     * Displays a goodbye message.
     */
    public static void displayGoodbyeText() {
        Ui.showMessageWithIndentation("Goodbye! Hope to see you again soon!");
    }

    /**
     * Displays a welcome message.
     * @param botName Name of the chat bot.
     */
    public void displayWelcomeText(String botName) {
        Ui.showMessageBetweenLines("Hello! I'm " + botName + "!", "What would you like to do today?");
    }

    /**
     * Displays an error message that saved data could not be loaded.
     */
    public void showLoadingError() {
        Ui.showMessageBetweenLines("Saved data could not be found. Starting fresh!");
    }

    /**
     * Starts the input manager which handles user input.
     * @param apollo The chat bot that the input manager is to handle inputs for.
     */
    public void startInputManager(Apollo apollo) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            try {
                Parser.handleInput(input, apollo.getTasks().getTaskList(), apollo);
            } catch (Exception e) {
                Ui.showMessageBetweenLines("An error occurred:", e.getMessage());
            }
        }
    }
}
