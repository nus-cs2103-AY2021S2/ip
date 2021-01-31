package dukeproject;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
* The Duke application is a to do list application where users are able to
* create, remove and see their to do list.
*
* @author  Low En Haoa
* @version 0.1
* @since   2021-01-26
*/
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Returns the duke object with access to the data in the file path.
     * If the data is not empty, load the file content into the task list.
     * Else, load an empty task list where the user can start using the application.
     *
     * @param filePath File path in the hard disk to store the current task list.
     * @throws DukeException for any error.
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadFileContent());
        } catch (FileNotFoundException | DukeException ex) {
            ui.printLoadingError();
            taskList = new TaskList();
        } catch (Exception ex) {
            throw new DukeException();
        }
    }

    /**
     * Runs the duke application.
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Duke("data/duke.txt").run();
        } catch (DukeException e) {
            System.out.println("Error on the duke application");
        }
    }

    /**
     * Method to run the duke application by showing a greetings to the user first.
     * Runs a while loop that only terminates when the user inputs "bye".
     * Check for keywords at the start of the input such as "list", "todo", "deadline",
     * "events" and perform different action base on it.
     *
     * @throws DukeException If there is any error with duke.
     */
    public void run() throws DukeException {
        // Output initial greeting before asking for input
        ui.showInitialGreeting();

        // Standard Input Scanner
        Scanner sc = new Scanner(System.in);

        // User input data
        String userInput;

        // Loop through and echo the input until the user inputs "bye"
        while (true) {
            userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // Exit when the user inputs "bye"
                ui.goodByeMessage();
                break;
            } else if (userInput.equals("list")) {
                // Print a listing of task
                ui.printTaskList(taskList);
            } else if (userInput.startsWith("todo")) {
                // Create a new task with the description from user input
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(5).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    ToDo newToDoTask = new ToDo(userInput.substring(5));
                    taskList.add(newToDoTask);
                    storage.writeToFile(taskList);

                    // Print a success message
                    ui.generalPrint(newToDoTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    ui.printDescriptionError();
                } catch (FileNotFoundException ex) {
                    // File is empty
                    ui.printFileError();
                }

            } else if (userInput.startsWith("deadline")) {
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(9).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    // Get the description and date from the user's input
                    StringDatePair output = new Parser().parse(userInput, Parser.CommandType.INPUT_DEADLINE);

                    // Add a deadline task
                    Deadline newDeadlineTask = new Deadline(output.getString(), output.getDate());
                    taskList.add(newDeadlineTask);
                    storage.writeToFile(taskList);

                    // Print a success message
                    ui.generalPrint(newDeadlineTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    ui.printDescriptionError();
                } catch (FileNotFoundException ex) {
                    // File is empty
                    ui.printFileError();
                } catch (DateTimeParseException ex) {
                    // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
                    ui.printDateFormatError();
                }
            } else if (userInput.startsWith("event")) {
                try {
                    // Catch error where the user put empty spaces as description
                    if (userInput.substring(5).isBlank()) {
                        throw new StringIndexOutOfBoundsException();
                    }

                    // Get the description and date from the user's input
                    StringDatePair output = new Parser().parse(userInput, Parser.CommandType.INPUT_EVENT);

                    // Add a deadline task
                    Event newEventTask = new Event(output.getString(), output.getDate());
                    taskList.add(newEventTask);
                    storage.writeToFile(taskList);

                    // Print a success message
                    ui.generalPrint(newEventTask.successMessage(taskList.size()));
                } catch (StringIndexOutOfBoundsException ex) {
                    // Description is empty
                    ui.printDescriptionError();
                } catch (FileNotFoundException ex) {
                    // File is empty
                    ui.printFileError();
                } catch (DateTimeParseException ex) {
                    // Datetime value parsed is not of format "yyyy-MM-dd HHmm"
                    ui.printDateFormatError();
                }
            } else if (userInput.startsWith("done")) {
                try {
                    // Mark the task as done based on the number given after the "done" input
                    int taskIndex = Integer.parseInt(userInput.substring(5));

                    // Mark the task as done
                    taskList.get(taskIndex - 1).markAsDone();
                    storage.writeToFile(taskList);

                    // Print success message that the task was marked as done
                    ui.printTaskSuccess(taskList, taskIndex);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    ui.printTaskNumError();
                } catch (FileNotFoundException ex) {
                    // File is empty
                    ui.printFileError();
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    // Delete task
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    Task taskToBeRemoved = taskList.get(taskIndex);

                    // Remove the appropriate task away from the list of task
                    taskList.remove(taskIndex);
                    storage.writeToFile(taskList);
                    ui.generalPrint(taskToBeRemoved.deleteMessage(taskList.size()));
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    // Task number is empty
                    ui.printTaskNumError();
                } catch (FileNotFoundException ex) {
                    // File is empty
                    ui.printFileError();
                }
            } else if (userInput.startsWith("find")) {
                // Find a return a list of task that is related to the keyword
                String keyword = new Parser().parseForFind(userInput);
                ui.printKeywordTaskList(taskList, keyword);
            } else {
                // Unable to detect the user's input
                ui.printUnreadableError();
            }
        }
        sc.close();
    }
}
