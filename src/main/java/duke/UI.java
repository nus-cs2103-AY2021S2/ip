package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deals with User interactions, reads user inputs and outputs text
 */
public class UI {

    private static final String WELCOME_MESSAGE = "Hello! I'm Duke o/\nWhat can I do for you today?\n\n" +
            "Enter 'help' if you are lost =)";
    private static final String GOODBYE_MESSAGE = "Good bye and take care o/" +
            "\nThis application will close in 5 seconds.";

    /**
     * Returns a response string
     * @param commandAndParams Parsed user input String array
     * @param list DukeList
     * @return Response String
     */
    public String commandMessage(String[] commandAndParams, DukeList list) {
        switch(commandAndParams[0]) {
        case "done":
            return doneMessage(commandAndParams[1]) + noOfTaskLeft(list);
        case "delete":
            return deleteMessage(commandAndParams[1]) + noOfTaskLeft(list);
        case "reset":
            return resetMessage() + noOfTaskLeft(list);
        case "show":
            return showMessage(list, commandAndParams[1]);
        case "todo":
        case "event":
        case "deadline":
            return addTaskMessage(commandAndParams[1]) + noOfTaskLeft(list);
        case "list":
            return listMessage(list);
        case "find":
            return findMessage(list, commandAndParams[1]);
        case "bye":
            return GOODBYE_MESSAGE;
        default:
            return unknownCommandMessage();
        }
    }

    /**
     * Returns a String containing all tasks with names matching the input String
     * @param list DukeList
     * @param keyword String of the parsed user input
     * @return Response String
     */
    private String findMessage(DukeList list, String keyword) {
        int size = list.getSize();
        int counter = 1;
        StringBuilder fullFindMessage = new StringBuilder("Here are tasks matching the keyword provided");
        for (int i = 0; i < size; i++) {
            if (list.get(i).getTaskName().contains(keyword)) {
                fullFindMessage.append("\n").append(counter).append(".")
                        .append(list.get(i)).append("\n");
                counter++;
            }
        }
        return fullFindMessage.toString();
    }

    /**
     * Returns all tasks in the DukeList
     * @param list list within DukeList
     * @return String of all tasks within the list
     */
    public String listMessage(DukeList list) {
        int size = list.getSize();
        StringBuilder fullListMessage = new StringBuilder("Here are your task!");
        for (int i = 0; i < size; i++) {
            fullListMessage.append("\n").append(i + 1).append(".").append(list.get(i));
        }
        return fullListMessage.toString();
    }

    /**
     * Returns a message string after "done" command
     * @param taskName name of the task
     * @return String message
     */
    public String doneMessage(String taskName) {
        return "Good job! The following task has been marked as done:\n" + taskName;
    }

    /**
     * Returns a message string after "delete" command
     * @param taskName name of the task
     * @return String message
     */
    public String deleteMessage(String taskName) {
        return "Got it! The following task has been deleted:\n" + taskName;
    }

    /**
     * Returns a message string after "reset" command
     * @return String message
     */
    public String resetMessage() {
        return "Got it! All tasks have been deleted";
    }

    /**
     * Returns a message string of all tasks that have the same dates as the input date
     * @param list DukeList
     * @param dateStr String of the date
     * @return String message
     */
    public String showMessage(DukeList list, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        int counter = 1;
        StringBuilder fullShowString = new StringBuilder("Here are your task on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        for (int i = 0; i < list.getSize(); i++) {
            Task curr = list.get(i);
            if (curr instanceof Deadlines && ((Deadlines) curr).getDeadline().equals(date)) {
                fullShowString.append("\n").append(counter).append(".").append(curr);
                counter++;
            } else if (curr instanceof Events && ((Events) curr).getDuration().equals(date)) {
                fullShowString.append("\n").append(counter).append(".").append(curr);
                counter++;
            }
        }
        return fullShowString.toString();
    }

    /**
     * Returns a message string after "add" command
     * @param taskStr name of the task
     * @return String message
     */
    public String addTaskMessage(String taskStr) {
        return "Got it! The following task has been added:\n" + taskStr;
    }

    /**
     * Returns a the number of task left in the list
     * @param list DukeList
     * @return String number of tasks left message
     */
    public String noOfTaskLeft(DukeList list) {
        return "\nYou have " + list.getSize() + " Task(s) left in the list";
    }

    /**
     * Returns the unknown command error message
     * @return String unknown command message
     */
    public String unknownCommandMessage() {
        return "I'm sorry, I do not know what that means";
    }

    /**
     * Returns the missing arguments error message
     * @return String missing argument message
     */
    public String showMissingArgsError() {
        return "Error! Your command has missing argument(s)!\nTry Again!";
    }

    /**
     * Returns the wrong arguments error message
     * @return String wrong argument message
     */
    public String showWrongArgsError() {
        return "Error! Your command has invalid argument(s)!\nTry Again!";
    }

    /**
     * Returns the goodbye message
     * @return String goodbye message
     */
    public String showGoodByeMessage() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns the welcome message
     * @return String welcome message
     */
    public String showWelcomeMessage() {
        return WELCOME_MESSAGE;
    }
}
