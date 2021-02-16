package duke.ui;

import java.util.LinkedList;
import java.util.Queue;

import duke.Duke;
import duke.util.Parser;

/**
 * Handles Ui related input and output tasks.
 */
public class Ui {

    public static final String INDENTATION = "    ";
    private static Queue<String> messageQueue = new LinkedList<>();

    /**
     * Displays a message with an indentation of 4 spaces.
     * @param s Message to be displayed.
     */
    public static void showMessageWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
        Ui.addToMessageQueue(s);
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void showLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
        Ui.addToMessageQueue("");
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
     * Passes input from Duke's GUI to Parser to execute related commands.
     * @param apollo Instance of Duke.
     * @param input User input to be passed to Parser.
     */
    public void handleInput(Duke apollo, String input) {
        assert apollo != null : "apollo should not be null!";

        try {
            Parser.handleInput(input, apollo.getTasks().getTaskList(), apollo);
        } catch (Exception e) {
            Ui.showMessageBetweenLines("An error occurred:", e.getMessage());
        }
    }

    /**
     * Returns available messages in messageQueue as a String else returns empty String.
     * @return String of messages in messageQueue else returns empty String.
     */
    public String getMessages() {
        StringBuilder message = new StringBuilder();

        if (!this.hasPendingMessage()) {
            return "";
        }

        while (this.hasPendingMessage()) {
            message.append(messageQueue.poll() + "\n");
        }

        return message.toString().trim();
    }

    /**
     * Adds message to messageQueue.
     * @param s Message to be added to messageQueue.
     */
    public static void addToMessageQueue(String s) {
        try {
            messageQueue.offer(s);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns true if messageQueue is not empty.
     * @return True if messageQueue is not empty.
     */
    public static boolean hasPendingMessage() {
        return !messageQueue.isEmpty();
    }
}
