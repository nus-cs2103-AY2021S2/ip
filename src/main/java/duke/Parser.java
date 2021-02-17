package duke;

import java.util.ArrayList;

/**
 * Parser class is an abstraction of an object that parses and interprets text messages into commands.
 */

public class Parser {
    private TaskList taskList;
    private boolean isExit;

    /**
     * Instantiates the Parser with attributes.
     * @param taskList the task list with a list of commands
     */
    public Parser (TaskList taskList) {
        this.taskList = taskList;
        this.isExit = false;
    }
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * This parses and executes relevant comments from the tasklist
     * @param command the command given by the user
     * @throws DukeException an exception due to errors in parsing text
     */
    public String executeCommand(String command) throws DukeException {
        String[] arr = command.split(" ", 2);
        String firstWord = arr[0];
        String reply = "";
        switch (firstWord) {
        case "bye":
            this.isExit = true;
            reply = "bye";
            break;
        case "list":
            reply = taskList.displayTasks();
            break;
        case "done":
            try {
                String num = arr[1];
                //TODO exception handling if num is not a number
                reply = taskList.markAsDone(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                throw new DukeException("Enter an integer only");
            }
            break;
        case "find":
            String toFind = arr[1];
            reply = taskList.findTasks(toFind);
            break;
        case "delete":
            try {
                String num = arr[1];
                ArrayList<Integer> tasksToDelete = getTaskNumbers(num);
                reply = taskList.deleteMultipleTasks(tasksToDelete);
            } catch (NumberFormatException e) {
                throw new DukeException("Enter an integer only");
            }
            break;
        case "todo":
            if (arr.length == 1) {
                throw new DukeException("Sorry, description of a todo cannot be empty");
            }
            String toDo = arr[1];
            reply = taskList.addTask(new ToDo(toDo));
            break;
        case "deadline":
            if (arr.length == 1) {
                throw new DukeException("Sorry description of a deadline cannot be empty");
            }
            String deadline = arr[1];
            reply = taskList.addTask(new Deadline(getMessage(deadline, " /by "), getDateTime(deadline, " /by ")));
            break;
        case "event":
            if (arr.length == 1) {
                throw new DukeException("Sorry description of an event cannot be empty");
            }
            String event = arr[1];
            reply = taskList.addTask(new Event(getMessage(event, " /at "), getDateTime(event, " /at ")));
            break;
        default:
            taskList.complain();
        }
        return reply;
    }

    /**
     * Parses the input text to return the deadline or event date/time.
     * @param text input text containing task description and date/time
     * @param delimiter the delimiter to parse the input text
     * @return the date/time in the input text
     * @throws DukeException thrown when the input text does not contain the required delimiter
     */
    public String getDateTime(String text, String delimiter) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(delimiter);
        if (index == -1) {
            throw new DukeException("Statement does not contain " + delimiter);
        }
        return text.substring(index + 5);
    }

    /**
     * Parses the input text to return the task description.
     * @param text input text containing task description and date/time
     * @param delimiter the delimiter to parse the input text
     * @return the task description from the input text
     * @throws DukeException thrown when the input text does not contain the required delimiter
     */
    public String getMessage(String text, String delimiter) throws DukeException {
        //TODO handle case when text does not contain "/at" or "/by"
        int index = text.indexOf(delimiter);
        if (index == -1) {
            throw new DukeException("Statement does not contain " + delimiter);
        }
        return text.substring(0, index);
    }

    /**
     * Converts a string of numbers to integer format and stores it an ArrayList.
     * @param num a string of numbers
     * @return an ArrayList of integers
     */
    public ArrayList<Integer> getTaskNumbers(String num) {
        String[] splitNum = num.split(" ");
        ArrayList<Integer> listOfTaskNumbers = new ArrayList<>();
        for (String value: splitNum) {
            listOfTaskNumbers.add(Integer.valueOf(value));
        }
        return listOfTaskNumbers;
    }
}
