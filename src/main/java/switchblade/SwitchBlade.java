package switchblade;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Main class of the chatbot
 *
 * @author leeyueyang
 */
public class SwitchBlade {

    private static void processCommand(String input, myList taskList) {
        String command = input.split("\\s+")[0];

        switch (command.toLowerCase(Locale.ROOT)) {
            case "list":
                Ui.printList(taskList);
                break;
            case "done":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.markCompleted(index - 1);
                } else {
                    Ui.argumentError();
                }
                break;
            case "todo":
                addTask(input,taskList);
                break;
            case "deadline":
                addDeadline(input, taskList);
                break;
            case "event":
                addEvent(input, taskList);
                break;
            case "delete":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.delete(index - 1);
                } else {
                    Ui.argumentError();
                }
                break;
            case "find":
                ArrayList<Task> foundTasks = taskList.findTasks(Parser.findDescription(input));
                Ui.printFoundTasks(foundTasks);
                break;
            default:
                Ui.unknownCommand();
        }
    }

    private static void addTask(String input, myList taskList) {
        if (input.replaceAll("todo", "").length() > 0) {
            taskList.addTask(input);
        } else {
            Ui.todoError();
        }
    }

    private static void addDeadline(String input, myList taskList) {
        if (input.contains("/by") && (Parser.findDeadlineDatetime(input) != null)) {
            String datetime = Parser.findDeadlineDatetime(input);
            String description = Parser.findDescription(input);

            taskList.addDeadline(description, datetime);
        } else {
            Ui.deadlineError();
        }
    }

    private static void addEvent(String input, myList taskList) {
        if (input.contains("/at") && (Parser.findEventDatetime(input) != null)) {
            String[] datetimeArr = Parser.findEventDatetime(input);
            String description = Parser.findDescription(input);

            if (datetimeArr != null && datetimeArr[0] != null && datetimeArr[1] != null)
                taskList.addEvent(description, datetimeArr[0], datetimeArr[1]);
        } else {
            Ui.eventError();
        }
    }

    /**
     *
     * @param args User defined arguments when running the project from command line
     */
    public static void main(String[] args) {

        Ui.init();
        myList taskList = new myList();
        taskList.retrieve();

        Scanner sc = new Scanner(System.in);
        String input = "bye";

        if (sc.hasNext()) {
            input = sc.nextLine();
        }

        while (!input.equalsIgnoreCase("bye")) {
            processCommand(input, taskList);

            if (sc.hasNext()) {
                input = sc.nextLine();
            }
        }

        Ui.shutdown();
    }
}
