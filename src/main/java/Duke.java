/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * This methods prints horizontal line whenever it's called.
     */
    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This method greets the user upon execution.
     */
    private static void greetCommand() {
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * This is the execution method, where the user's inputs are split up into arrays and processed accordingly.
     * @param inputLine the inputs that user have typed in
     * @param taskList the task list to be maintained
     * @throws DukeException whenever the user typed in invalid command.
     */
    private static void echoCommand(String inputLine, List<Task> taskList) throws DukeException {
        String[] inputs = inputLine.split(" ");
        String command = inputs[0].toUpperCase();
        if (!DukeCommand.isContains(command)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else {
            DukeCommand dukeCommand = DukeCommand.valueOf(command);
            String[] actions = Arrays.copyOfRange(inputs, 1, inputs.length);
            String actionString = String.join(" ", actions);
            dukeCommand.runCommand(actionString, taskList);
        }
    }

    /**
     * This method bids farewell to the user whenever the "bye" command is inputted.
     */
    private static void exitCommand() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * This method automatically greets the user upon execution of the programme. It maintains the list of tasks and handles the capturing of inputs from users.
     * It also handles the error handling.
     */
    public static void main(String[] args) {
        greetCommand();
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            printHorizontalLine();
            try {
                echoCommand(command, taskList);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            printHorizontalLine();
        }
        exitCommand();
        sc.close();
    }
}