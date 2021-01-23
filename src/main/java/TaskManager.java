import java.util.List;
import java.util.ArrayList;

/**
 * A controller class that manages the Tasks of each User of the DukeBot.
 *
 * The purpose of this class is to allow DukeBot to be used concurrently
 * by multiple users by separating static-level Task members that should
 * not be shared by all Users of the Task class.
 *
 * Note, this class assumes that it has been correctly called by the DukeBot
 * and that the only possible source of errors are in the User description
 * String. (I.e. Assumes the Commands provided are correct or appropriate)
 *
 * Functionality:
 * 1. Create, store, and delete Tasks
 * 2. Store meta data about Tasks (e.g. count)
 * 3. Print outputs after each action
 */
public class TaskManager {
    protected boolean isActive;
    protected List<Task> taskList;

    public TaskManager() {
        this.isActive = true;
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Main access point of the TaskManager API which handles all Task functionality.
     *
     * @param taskType A Command Enum representing the relevant command for the given description
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
                    this.addTodo(description);
                    break;
                case EVENT:
                    this.addEvent(description);
                    break;
                case DEADLINE:
                    this.addDeadline(description);
                    break;
                case DONE:
                    this.markAsDone(description);
                    break;
                case DELETE:
                    this.delete(description);
                    break;
                case END:
                    this.end(description);
                    break;
                default:
                    // If this error is reached that means that a valid Command Enum was created and has not been
                    // included in the switch statements above
                    String errorStr = "The Command given was not recognised. Try updating the version";
                    throw new DukeException(errorStr);
            }
        } catch (DukeException e) {
            // Print any errors encountered in the user input string
            System.out.println(e);
            System.out.println("\n");
        }
    }

    /**
     * Returns true if the TaskManager is active.
     * The TaskManager becomes inactive once a user sends the END Command.
     *
     * @return true if the TaskManager is active and false otherwise
     */
    public boolean isActive() {
        return this.isActive == true;
    }

    /**
     * Sets the current TaskManager to be inactive, effectively switching it off.
     */
    public void setInactive() {
        this.isActive = false;
    }

    /**
     * Sets the current TaskManager to be active, effectively switching it on.
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
            System.out.println();  // Add space between user input and DukeBot response
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
     * Adds a Todo Task to the task list.
     *
     * @param description The description of the Todo Task
     * @throws DukeException If the description string is empty
     */
    protected void addTodo(String description) throws DukeException {
        if (description.equals("")) {
            String errorStr = "The description of a 'todo' cannot be empty.";
            throw new DukeException(errorStr);
        } else {
            Task newTodo = new Todo(description);
            taskList.add(newTodo);
            System.out.println();  // Add space between user input and DukeBot response
            System.out.println("New to-do added:\n" + newTodo);
            System.out.println("\n");
        }
    }

    /**
     * Adds an Event Task to the task list.
     *
     * @param description The description of the Event Task
     * @throws DukeException If the description string does not follow the correct format for specifying an Event
     */
    protected void addEvent(String description) throws DukeException {
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
        System.out.println();  // Add space between user input and DukeBot response
        System.out.println("New event added:\n" + newEvent);
        System.out.println("\n");
    }

    /**
     * Adds a Deadline Task to the task list.
     *
     * @param description The description of the Deadline Task
     * @throws DukeException If the description string does not follow the correct format for specifying a Deadline
     */
    protected void addDeadline(String description) throws DukeException {
        String errorStr = "";
        String descriptor = description.split("/by", 2)[0];
        String by = description.split("/by", 2)[1];
        if (description.equals("")) {
            errorStr = "The description of a 'deadline' cannot be empty.";
        } else if (!description.contains("/by")) {
            errorStr = "The description of a 'deadline' must contain a /by term separating " +
                    "the descriptor from the Deadline time. For example: " +
                    "event Submit financial reports /by Friday, 6pm";
        } else if (descriptor.equals("") || by.equals("")) {
            errorStr = "Neither the descriptor nor the Deadline time can be empty.\n" +
                    "Received the following: " + description;
        }

        if (!errorStr.equals("")) throw new DukeException(errorStr);

        Task newDeadline = new Deadline(descriptor, by);
        taskList.add(newDeadline);
        System.out.println();  // Add space between user input and DukeBot response
        System.out.println("New deadline added:\n" + newDeadline);
        System.out.println("\n");
    }

    /**
     * Marks an existing Task as done.
     *
     * @param description A String containing the (1-indexed) list index of the target task
     * @throws DukeException If the description string cannot be parsed as an integer or is outside the valid range
     */
    protected void markAsDone(String description) throws DukeException {
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(description);
            doneIndex--; // Subtract 1 to account for 0 indexing

            Task doneTask = taskList.get(doneIndex);
            doneTask.markAsDone();

            System.out.println();  // Add space between user input and DukeBot response
            System.out.println("The following task has been marked as done: ");
            System.out.println(doneTask + "\n");
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
     * Deletes an existing Task and removes it from the task list.
     *
     * @param description A String containing the (1-indexed) list index of the target task
     * @throws DukeException If the description string cannot be parsed as an integer or is outside the valid range
     */
    protected void delete(String description) throws DukeException {
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(description);
            deleteIndex--; // Subtract 1 to account for 0 indexing

            Task deletedTask = taskList.remove(deleteIndex);

            System.out.println();  // Add space between user input and DukeBot response
            System.out.println("The following task has been deleted: ");
            System.out.println(deletedTask);
            System.out.println("There are now " + taskList.size() + " items in the list.\n");

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
     * Deactivates the TaskManager effectively shutting it down.
     *
     * @param description The remainder of the user input String (after removing the command) which should be empty
     * @throws DukeException If the user input String contains a non-empty description of if the TaskManager is already
     * inactive.
     */
    protected void end(String description) throws DukeException {
        String errorStr = "";
        if (!description.equals("")) {
            errorStr = "The terminate command 'bye' should be " +
                    "called without any further descriptor. However, it " +
                    "received the following descriptor: " + description;
        } else if (!this.isActive) {
            errorStr = "The DukeBot is currently inactive.";
        }
        if (!errorStr.equals("")) throw new DukeException(errorStr);

        // Otherwise, set the DukeBot to be inactive
        this.setInactive();
        System.out.println();
        System.out.println("DukeBot shutting down.\n\n");
    }

}
