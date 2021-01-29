import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exception.DukeException;

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
    public boolean determineAction(String userInput) {
        List<Task> tasks = this.taskList.getNewStorage();
        String[] inputBreakdown = userInput.split(" ");
        if (inputBreakdown[0].equals("bye")) {
            indentInput("Bye. Hope to see you again!");
            storage.updateHardDrive(storage.getFilePath(), this.taskList);
            return false;
        } else if (inputBreakdown[0].equals("list")) {
            System.out.println("Here are the tasks in your list:");
            taskList.listTask(tasks);
        } else if (inputBreakdown[0].equals("done")) {
            System.out.println("Nice! I've marked this task as done:");
            int selectedIndex = Integer.valueOf(inputBreakdown[1]) - 1;
            taskList.setTaskAsDone(selectedIndex);
            taskList.printTask(selectedIndex, tasks.get(selectedIndex));
        } else if (inputBreakdown[0].equals("delete")) {
            taskList.deleteTask(userInput);
        } else {
            taskList.addTask(userInput);
        }
        return true;
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
        possibleActionInputs.add("done");
        possibleActionInputs.add("delete");
        possibleSingleInputs.add("bye");
        possibleTaskInputs.add("todo");
        possibleTaskInputs.add("event");
        possibleTaskInputs.add("deadline");
        possibleSingleInputs.add("list");
        if (!possibleActionInputs.contains(input[0]) && !possibleSingleInputs.contains(input[0]) && !possibleTaskInputs.contains(input[0])) {
            throw new DukeException("user action is not recognised!");
        } else if ((possibleTaskInputs.contains(input[0]) || possibleActionInputs.contains(input[0])) && input.length == 1) {
            throw new DukeException("no description added!");
        } else if (possibleSingleInputs.contains(input[0]) && input.length > 1) {
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
        }
    }

    public static void indentInput(String input) {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("added: " + input);
        System.out.println(line);
    }
}
