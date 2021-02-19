package switchblade;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Main class of the chatbot
 *
 * @author leeyueyang
 */
public class SwitchBlade {

    public static String processCommand(String input, myList taskList) {
        assert input.length() > 0;

        String command = input.split("\\s+")[0];

        switch (command.toLowerCase(Locale.ROOT)) {
        case "list":
            return Ui.printList(taskList);
        case "done":
            if (input.split("\\s+").length == 2) {
                int index = Integer.parseInt(input.split("\\s+")[1]);
                return taskList.markCompleted(index - 1);
            } else {
                return Ui.argumentError();
            }
        case "todo":
            return addTask(input,taskList);
        case "deadline":
            return addDeadline(input, taskList);
        case "event":
            return addEvent(input, taskList);
        case "delete":
            List<Integer> eventsToDelete = Parser.findTaskToDelete(input);
            return taskList.delete(eventsToDelete);
        case "find":
            ArrayList<Task> foundTasks = taskList.findTasks(Parser.findDescription(input));
            return Ui.printFoundTasks(foundTasks);
        case "bye":
            return Ui.shutdown();
        default:
            return Ui.unknownCommand();
        }
    }

    private static String addTask(String input, myList taskList) {
        assert input != null;
        assert input.length() > 0;

        if (input.replaceAll("todo", "").length() > 0) {
            return taskList.addTask(input);
        } else {
            return Ui.todoError();
        }
    }

    private static String addDeadline(String input, myList taskList) {
        assert input.length() > 0;

        if (input.contains("/by") && (Parser.findDeadlineDatetime(input) != null)) {
            String datetime = Parser.findDeadlineDatetime(input);
            String description = Parser.findDescription(input);

            return taskList.addDeadline(description, datetime);
        } else {
            return Ui.deadlineError();
        }
    }

    private static String addEvent(String input, myList taskList) {
        assert input.length() > 0;

        if (input.contains("/at") && (Parser.findEventDatetime(input) != null)) {
            String[] datetimeArr = Parser.findEventDatetime(input);
            String description = Parser.findDescription(input);

            if (datetimeArr != null && datetimeArr[0] != null && datetimeArr[1] != null)
                return taskList.addEvent(description, datetimeArr[0], datetimeArr[1]);
            else
                return "";
        } else {
            return Ui.eventError();
        }
    }

    /**
     *
     * @param args User defined arguments when running the project from command line
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
