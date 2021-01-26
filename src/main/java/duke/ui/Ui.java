package duke.ui;

import duke.Apollo;
import duke.util.Parser;

import java.util.Scanner;

public class Ui {

    public static final String INDENTATION = "    ";

    public static void showMessageWithIndentation(String s) {
        System.out.println(INDENTATION + " " + s);
    }

    public static void showLine() {
        String line = "____________________________________________________________";
        System.out.println(INDENTATION + line);
    }

    public static void showMessageBetweenLines(String... strings) {
        showLine();

        for (int i = 0; i < strings.length; i++) {
            showMessageWithIndentation(strings[i]);
        }

        showLine();
    }

    public static void showTaskAddedText(String taskInfo, Integer numberOfTasks) {
        showMessageBetweenLines("Got it. I've added this task:",
                INDENTATION + taskInfo,
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }

    public static void showTaskDeletedText(String taskInfo, Integer numberOfTasks) {
        showMessageBetweenLines("Noted. I've removed this task:",
                INDENTATION + taskInfo,
                "Now you have " + numberOfTasks.toString() + " tasks in the list.");
    }

    public static void showErrorMessage(String s) {
        showMessageBetweenLines(s);
    }

    public static void displayGoodbyeText() {
        Ui.showMessageWithIndentation("Goodbye! Hope to see you again soon!");
    }

    public void displayWelcomeText(String botName) {
        Ui.showMessageBetweenLines("Hello! I'm " + botName + "!", "What would you like to do today?");
    }

    public void showLoadingError() {
        Ui.showMessageBetweenLines("Saved data could not be found. Starting fresh!");
    }

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
