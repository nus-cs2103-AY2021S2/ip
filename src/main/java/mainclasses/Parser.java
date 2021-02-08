package mainclasses;

import java.util.ArrayList;
import java.util.List;

import exception.DukeException;


public class Parser {
    private TaskList taskList;
    private Storage storage;


    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Returns a boolean if the user inputs "bye"
     * @param userInput takes in user input as a String
     * @return a boolean to indicate whether user wants to quit the application
     */
    public String determineAction(String userInput) {
        List<Task> tasks = this.taskList.getNewStorage();
        String[] inputBreakdown = userInput.split(" ");
        if (inputBreakdown[0].equals("bye")) {
            storage.updateHardDrive(storage.getFilePath(), this.taskList);
            return ("Bye. Hope to see you again!");
        } else if (inputBreakdown[0].equals("list")) {
            return taskList.listTask(tasks);
        } else if (inputBreakdown[0].equals("done")) {
            int selectedIndex = Integer.valueOf(inputBreakdown[1]) - 1;
            return taskList.setTaskAsDone(selectedIndex);
        } else if (inputBreakdown[0].equals("delete")) {
            return taskList.deleteTask(userInput);
        } else if (inputBreakdown[0].equals("find")) {
            return taskList.findTasksWithKeyword(inputBreakdown[1]);
        } else if (inputBreakdown[0].equals("sort")) {
            return taskList.sortTask(inputBreakdown[1], inputBreakdown[2]);

        } else {
            return taskList.addTask(userInput);
        }
    }

    /**
     * Validate user inputs and throws an exception if user input is invalid
     * @param userInput takes in user input as a String
     * @throws DukeException Exception that contains error message
     */
    public void checkInput (String userInput) throws DukeException {
        String[] input = userInput.split(" ");
        List<String> possibleActionInputs = new ArrayList<>();
        List<String> possibleSingleInputs = new ArrayList<>();
        List<String> possibleTaskInputs = new ArrayList<>();
        List<String> possibleSortInputs = new ArrayList<>();
        possibleActionInputs.add("done");
        possibleActionInputs.add("delete");
        possibleSortInputs.add("find");
        possibleSortInputs.add("sort");
        possibleSingleInputs.add("bye");
        possibleTaskInputs.add("todo");
        possibleTaskInputs.add("event");
        possibleTaskInputs.add("deadline");
        possibleSingleInputs.add("list");
        boolean isUnrecognisedInput = !possibleActionInputs.contains(input[0]) && !possibleSingleInputs.contains(input[0])
                && !possibleTaskInputs.contains(input[0]) && !possibleSortInputs.contains(input[0]);
        boolean hasNoDescription = (possibleTaskInputs.contains(input[0]) || possibleActionInputs.contains(input[0]))
                && input.length == 1;
        boolean hasDescriptionForSingleAction = possibleSingleInputs.contains(input[0]) && input.length > 1;
        if (isUnrecognisedInput) {
            throw new DukeException("user action is not recognised!");
        } else if (hasNoDescription) {
            throw new DukeException("no description added!");
        } else if (hasDescriptionForSingleAction) {
            throw new DukeException("no description should be added for this command!");
        } else if (possibleTaskInputs.contains(input[0])) {
            switch(input[0]) {
            case "deadline":
                if (!userInput.contains("/by")) {
                    throw new DukeException("Deadline entries must have a /by phrase!");
                } else {
                    break;
                }
            case "event":
                if (!userInput.contains("/at")) {
                    throw new DukeException("Event entries must have a /at phrase!");
                } else {
                    break;
                }
            default:
                System.out.println("Todo entered by user!");
            }
        } else if (possibleActionInputs.contains(input[0])) {
            if (input.length > 2) {
                throw new DukeException("enter a specific number");
            } else {
                try {
                    int number = Integer.parseInt(input[1]);
                    if (number < 0 || number > this.taskList.getNewStorage().size()) {
                        throw new DukeException("number entered does not match the list of tasks in list");
                    }
                } catch (NumberFormatException ex) {
                    throw new DukeException("Enter a number!");
                }
            }
        } else if (possibleSortInputs.contains(input[0])) {
            if (input[0].equals("sort")) {
                System.out.println("Sorting");
            } else {
                if (input.length > 2) {
                    throw new DukeException("You can only search by a single word!");
                }
                try {
                    int number = Integer.parseInt(input[1]);
                    throw new DukeException("A number should not be entered for find");
                } catch (NumberFormatException ex) { //If the code comes here means the userInput is not a number which is correct
                    System.out.println("Finding...");
                }
            }

        }
    }


}
