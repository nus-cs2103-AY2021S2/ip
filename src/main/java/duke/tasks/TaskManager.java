package duke.tasks;

import duke.commands.Command;
import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Todo;

import duke.exceptions.DukeException;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.nio.file.Path;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

/**
 * TODO:
 * 1. Refactor IO into separate functions that handle all exceptions
 *    * Input should return a Scanner and handle any missing directory (createIfMissing) / file exceptions
 *    * Output should return void but throw an error if it encounters any problems (and no createIfMissing)
 */

/**
 * A controller class that manages the Tasks of each User of the duke.DukeBot.
 * <p>
 * The purpose of this class is to allow duke.DukeBot to be used concurrently
 * by multiple users by separating static-level duke.tasks.Task members that should
 * not be shared by all Users of the duke.tasks.Task class.
 * <p>
 * Note, this class assumes that it has been correctly called by the duke.DukeBot
 * and that the only possible source of errors are in the User description
 * String. (I.e. Assumes the Commands provided are correct or appropriate)
 * <p>
 * Functionality:
 * 1. Create, store, and delete Tasks
 * 2. Store meta data about Tasks (e.g. count)
 * 3. Print outputs after each action
 */
public class TaskManager {
    protected boolean isActive;
    protected List<Task> taskList;
    protected final Path PATH;

    public TaskManager(Path PATH) {
        this.isActive = true;
        this.taskList = new ArrayList<>(100);
        this.PATH = PATH;
        downloadStoredDataIfExists(PATH);
    }

    /**
     * Loads in Task data from a text file and populates the Task List with it.
     *
     * @param PATH Path instance that points to the text file containing Task data.
     */
    private void downloadStoredDataIfExists(Path PATH) {
        // Check if the data/ parent directory exists, if not create it
        File directory = new File(String.valueOf(PATH.getParent()));
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            // Initialise scanner on the file
            File dataFile = new File(PATH.toString());
            Scanner scanner = new Scanner(dataFile);

            // Count how many Tasks are loaded in
            int count = 0;

            // Populate taskList
            while (scanner.hasNext()) {
                String storedTaskArr[] = scanner.nextLine().split("\\|", 3);
                String taskType = storedTaskArr[0].strip();
                String taskIsDone = storedTaskArr[1].strip();
                String taskDescription = storedTaskArr[2].strip();

                // Handle each possible Command and its corresponding Task
                Task newTask;
                Command command = Command.get(taskType);
                switch (command) {
                case TODO:
                    newTask = this.addTodo(taskDescription, false);
                    break;
                case EVENT:
                    newTask = this.addEvent(taskDescription, false);
                    break;
                case DEADLINE:
                    newTask = this.addDeadline(taskDescription, false);
                    break;
                default:
                    throw new DukeException("A unrecognised Command was created, update 'downloadStoredDataIfExists'.");
                }
                if (taskIsDone.equals("done")) {
                    newTask.markAsDone();
                }
                count++;
            }

            if (count > 0) {
                System.out.println(count + " tasks were loaded from storage.");
            } else {
                System.out.println("No tasks found in storage, initialising empty Task List.");
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such storage file exists: " + PATH.toString() + "\nProceeding without.");
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }

    /** Saves the Tasks in taskList to the PATH location. */
    public void saveData() {
        // Check if the data/ parent directory exists, if not create it
        File directory = new File(String.valueOf(PATH.getParent()));
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(PATH.toString());
            fw.write(this.taskListToString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all the Tasks in the taskList as a single String that can be written to a file.
     *
     * @return A String that contains every Task in the taskList, formatted to be written to a file.
     */
    private String taskListToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskList) {
            String taskDone = task.getIsDone() ? "done" : "not-done";
            String taskString = task.getClass().getSimpleName().toLowerCase() +
                    " | " + taskDone +
                    " | " + task.getConstructorString() +
                    "\n";
            stringBuilder.append(taskString);
        }
        return stringBuilder.toString();
    }

    /**
     * Main access point of the duke.tasks.TaskManager API which handles all duke.tasks.Task functionality.
     *
     * @param taskType    A duke.commands.Command Enum representing the relevant command for the given description
     * @param description User input String that should NOT contain the command string
     *                    E.g. If the user supplies "todo Eat cake" then the description is "eat cake"
     */
    public void handleCommand(Command taskType, String description) {
        description = description.strip(); // Remove surrounding whitespace
        try {
            switch (taskType) {
            case LIST:
                this.listAll(description);
                break;
            case TODO:
                this.addTodo(description, true);
                this.saveData();
                break;
            case EVENT:
                this.addEvent(description, true);
                this.saveData();
                break;
            case DEADLINE:
                this.addDeadline(description, true);
                this.saveData();
                break;
            case DONE:
                this.markAsDone(description, true);
                this.saveData();
                break;
            case DELETE:
                this.delete(description, true);
                this.saveData();
                break;
            case END:
                this.end(description);
                break;
            default:
                // If this error is reached that means that a valid duke.commands.Command Enum was created and has not been
                // included in the switch statements above
                String errorStr = "The duke.commands.Command given was not recognised. Try updating the version";
                throw new DukeException(errorStr);
            }
        } catch (DukeException e) {
            // Print any errors encountered in the user input string
            System.out.println(e);
            System.out.println("\n");
        }
    }

    /**
     * Returns true if the duke.tasks.TaskManager is active.
     * The duke.tasks.TaskManager becomes inactive once a user sends the END duke.commands.Command.
     *
     * @return true if the duke.tasks.TaskManager is active and false otherwise
     */
    public boolean isActive() {
        return this.isActive == true;
    }

    /**
     * Sets the current duke.tasks.TaskManager to be inactive, effectively switching it off.
     */
    public void setInactive() {
        this.isActive = false;
    }

    /**
     * Sets the current duke.tasks.TaskManager to be active, effectively switching it on.
     */
    public void setActive() {
        this.isActive = true;
    }

    /**
     * Prints out all existing Tasks that are being stored in the task list.
     *
     * @param description The user input which should be an empty String
     * @throws DukeException If the description is not an empty String
     */
    protected void listAll(String description) throws DukeException {
        if (!description.equals("")) {
            String errorStr = "The list command should be called without " +
                    "any further descriptor. However, it received the " +
                    "following descriptor: " + description;
            throw new DukeException(errorStr);
        } else {
            System.out.println();  // Add space between user input and duke.DukeBot response
            System.out.println("Here are the tasks in your list:");
            int count = 1;
            for (Task task : taskList) {
                System.out.println(count + ". " + task);
                count++;
            }
            System.out.println("\n");
        }
    }

    /**
     * Adds a duke.commands.Todo duke.tasks.Task to the task list.
     *
     * @param description The description of the duke.commands.Todo duke.tasks.Task
     * @throws DukeException If the description string is empty
     */
    protected Task addTodo(String description, boolean isVerbose) throws DukeException {
        if (description.equals("")) {
            String errorStr = "The description of a 'todo' cannot be empty.";
            throw new DukeException(errorStr);
        } else {
            Task newTodo = new Todo(description);
            taskList.add(newTodo);
            if (isVerbose) {
                System.out.println();  // Add space between user input and duke.DukeBot response
                System.out.println("New to-do added:\n" + newTodo);
                System.out.println("\n");
            }
            return newTodo;
        }
    }

    /**
     * Adds an duke.commands.Event duke.tasks.Task to the task list.
     *
     * @param description The description of the duke.commands.Event duke.tasks.Task
     * @throws DukeException If the description string does not follow the correct format for specifying an duke.commands.Event
     */
    protected Task addEvent(String description, boolean isVerbose) throws DukeException {
        String errorStr = "";
        String descriptor = description.split("/at", 2)[0];
        String at = description.split("/at", 2)[1];
        if (description.equals("")) {
            errorStr = "The description of an 'event' cannot be empty.";
        } else if (!description.contains("/at")) {
            errorStr = "The description of an 'event' must contain an /at term separating " +
                    "the descriptor from the Date & Location. For example: " +
                    "event Office party /at Marriott Hotel Thursday, 2pm";
        } else if (descriptor.equals("") || at.equals("")) {
            errorStr = "Neither the descriptor nor the Date & Location can be empty.\n" +
                    "Received the following: " + description;
        }

        if (!errorStr.equals("")) throw new DukeException(errorStr);

        Task newEvent = new Event(descriptor, at);
        taskList.add(newEvent);
        if (isVerbose) {
            System.out.println();  // Add space between user input and duke.DukeBot response
            System.out.println("New event added:\n" + newEvent);
            System.out.println("\n");
        }
        return newEvent;
    }

    /**
     * Adds a duke.commands.Deadline duke.tasks.Task to the task list.
     *
     * @param description The description of the duke.commands.Deadline duke.tasks.Task
     * @throws DukeException If the description string does not follow the correct format for specifying a duke.commands.Deadline
     */
    protected Task addDeadline(String description, boolean isVerbose) throws DukeException {
        String errorStr = "";
        String descriptor = description.split("/by", 2)[0];
        String by = description.split("/by", 2)[1];
        if (description.equals("")) {
            errorStr = "The description of a 'deadline' cannot be empty.";
        } else if (!description.contains("/by")) {
            errorStr = "The description of a 'deadline' must contain a /by term separating " +
                    "the descriptor from the duke.commands.Deadline time. For example: " +
                    "event Submit financial reports /by Friday, 6pm";
        } else if (descriptor.equals("") || by.equals("")) {
            errorStr = "Neither the descriptor nor the duke.commands.Deadline time can be empty.\n" +
                    "Received the following: " + description;
        }

        if (!errorStr.equals("")) throw new DukeException(errorStr);

        Task newDeadline = new Deadline(descriptor, by);
        taskList.add(newDeadline);

        if (isVerbose) {
            System.out.println();  // Add space between user input and duke.DukeBot response
            System.out.println("New deadline added:\n" + newDeadline);
            System.out.println("\n");
        }
        return newDeadline;
    }

    /**
     * Marks an existing duke.tasks.Task as done.
     *
     * @param description A String containing the (1-indexed) list index of the target task
     * @throws DukeException If the description string cannot be parsed as an integer or is outside the valid range
     */
    protected Task markAsDone(String description, boolean isVerbose) throws DukeException {
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(description);
            doneIndex--; // Subtract 1 to account for 0 indexing

            Task doneTask = taskList.get(doneIndex);
            doneTask.markAsDone();

            if (isVerbose) {
                System.out.println();  // Add space between user input and duke.DukeBot response
                System.out.println("The following task has been marked as done: ");
                System.out.println(doneTask + "\n");
            }
            return doneTask;
        } catch (NumberFormatException e) {
            throw new DukeException("The value that follows 'done' must be an integer");
        } catch (IndexOutOfBoundsException e) {
            int count = taskList.size();
            String errorStr;
            if (count == 0) {
                errorStr = "The list is currently empty so there is no task to be marked as done";
            } else if (count == 1) {
                errorStr = "The list only has one item. To mark it as done type 'done 1'";
            } else {
                errorStr = "The value that follows 'done' must be between 1 and " + count;
            }
            throw new DukeException(errorStr);
        }
    }

    /**
     * Deletes an existing duke.tasks.Task and removes it from the task list.
     *
     * @param description A String containing the (1-indexed) list index of the target task
     * @throws DukeException If the description string cannot be parsed as an integer or is outside the valid range
     */
    protected Task delete(String description, boolean isVerbose) throws DukeException {
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(description);
            deleteIndex--; // Subtract 1 to account for 0 indexing

            Task deletedTask = taskList.remove(deleteIndex);

            if (isVerbose) {
                System.out.println();  // Add space between user input and duke.DukeBot response
                System.out.println("The following task has been deleted: ");
                System.out.println(deletedTask);
                System.out.println("There are now " + taskList.size() + " items in the list.\n");
            }
            return deletedTask;
        } catch (NumberFormatException e) {
            throw new DukeException("The value that follows 'delete' must be an integer");
        } catch (IndexOutOfBoundsException e) {
            int count = taskList.size();
            String errorStr;
            if (count == 0) {
                errorStr = "The list is currently empty so there is nothing to delete";
            } else if (count == 1) {
                errorStr = "The list only has one item. To delete it type 'delete 1'";
            } else {
                errorStr = "The value that follows delete must be between 1 and " + count;
            }
            throw new DukeException(errorStr);
        }
    }

    /**
     * Deactivates the duke.tasks.TaskManager effectively shutting it down.
     *
     * @param description The remainder of the user input String (after removing the command) which should be empty
     * @throws DukeException If the user input String contains a non-empty description of if the duke.tasks.TaskManager is already
     *                       inactive.
     */
    protected void end(String description) throws DukeException {
        String errorStr = "";
        if (!description.equals("")) {
            errorStr = "The terminate command 'bye' should be " +
                    "called without any further descriptor. However, it " +
                    "received the following descriptor: " + description;
        } else if (!this.isActive) {
            errorStr = "The duke.DukeBot is currently inactive.";
        }
        if (!errorStr.equals("")) throw new DukeException(errorStr);

        // Otherwise, set the duke.DukeBot to be inactive
        this.setInactive();
        System.out.println();
        System.out.println("DukeBot shutting down.\n\n");
    }

}
