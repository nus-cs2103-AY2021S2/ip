package duke.ui;

import duke.task.Task;

/**
 * UI class to handle interaction with users
 */
public class UI {

    /**
     * Display the greeting message upon launching the application
     */
    public static String displayWelcomeMessage() {
        String output = "Hello! I am Will, your personal assistant." + "\n"
                + "What can I do for you today?";
        return output;
    }

    /**
     * Display bye message
     */
    public static String displayEndMessage() {
        return "Bye. Hope to see you again!" + "\n";
    }

    /**
     * Display a particular task in list with its customized format
     *
     * @param index index of task in task list
     * @param task  task
     */
    public String displayTask(int index, Task task) {
        return "\n" + (index + 1) + "." + task.toString();
    }

    /**
     * Display message to inform users that there is are no task in list
     */
    public String displayNoTaskInList() {
        return "There are no tasks in list.";
    }

    /**
     * Display message to inform users that there is a duplicated task
     */
    public String displayDuplicatedMessage() {
        return "Existing entries with same task description was found."
                + " Please add a new task.";
    }

    /**
     * Display message to inform users that this are no task
     */
    public static String displayNoTaskFoundMessage() {
        return "No such task is found.";
    }

    /**
     * Display header to show the tasks in the current list
     */
    public static String displayHeader(String type) {
        if(type.equals("find")){
            return "Here are the matching tasks in your list:";

        } else {
            return "Here are the tasks in your list:";
        }
    }

    /**
     * Display message to inform users that task index is invalid
     */
    public static String displayInvalidTaskIndex() {
        return "Task index starts from 1. Please try again with a valid task index.";
    }

    /**
     * Display message to inform users that there are no known commands
     */
    public static String displayUnknownCommand() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Display message to inform users that more parameters should be specified
     */
    public static String displayInvalidParameter(String command) {

        if (command.equals("find")) {
            return "Please input task description to be searched.";
        } else if (command.equals("done")) {
            return "Please input a valid task number to mark the task as complete.";
        } else if (command.equals("delete")) {
            return "Please input a valid task number to delete the task.";
        }  else if (command.equals("wrongCommand")) {
            return "Wrong command to add deadline or start/end time. "
                    + "Use /by for deadline and /at for event";
        } else if (command.equals("noDescription")) {
            return  "OOPS!!! The description of a task cannot be empty.";
        } else if(command.equals("invalidTimeFormat")) {
            return "Please specify a valid time format (HHmm) for the task.";
        }  else if (command.equals("invalidDate")) {
            return "Please specify a valid due date in the format (DD/MM/YYYY) " +
                    "and the date must not have already passed.";
        } else if(command.equals("dateExtendTotalDateInMonth")){
            return "Please specify a valid day in the month.";
        } else if (command.equals("noDueTime")) {
            return "The due time for the task is missing.";
        } else if (command.equals("noDueDate")) {
            return "The due date for the task is missing.";
        } else{
            return null;
        }
    }



        /**
         * Display message to inform users that more parameters should be specified
         */
    public static String displayDeadlineInvalidParameter(String command) {
        if (command.equals("extraParameter")) {
            return "Please follow the given format to add deadlines or "
                    + "add one deadline at a time.";
        } else if (command.equals("noDueDateOrWrongFormat")) {
            return "Please follow the given format to add deadlines: "
                    + "event task description /at DD/MM/YYYY HHmm)";
        } else{
            return null;
        }
    }

    /**
     * Display message to inform users that more parameters should be specified
     */
    public static String displayEventInvalidParameter(String command) {

        if (command.equals("extraParameter")) {
            return "Please follow the given format to add events or "
                    + "add one event at a time.";
        } else if (command.equals("lesserParameterGiven")) {
            return "Please follow the given format to add events or "
                    + "add one task at a time.";
        }else if (command.equals("errorSeparatingTime")){
            return "Please specify the start and end time in the expected format. For ie, 1800-2000 or include start/end date";
        }  else if (command.equals("wrongTimeFormat")) {
            return "Please specify a valid start and end time (HHmm) for the task.";
        } else if (command.equals("invalidTimeInput")) {
            return "Unable to add event with start time later than or equal to end time.";
        } else if (command.equals("noDueDateOrWrongFormat")) {
            return "Please follow the given format to add events: "
                    + "event task description /at DD/MM/YYYY HHmm-HHmm)";
        }else {
            return null;
        }
    }

    /**
     * Display message to inform users that task has been marked as complete
     */
    public static String displayMarkingCompletedAsDone() {
        return "The task has already been marked as complete.";
    }

    /**
     * Display message upon successful addition of task to task list
     * @param task task
     * @param size amount of items in task list
     */
    public static String displayAddedTaskMessage(Task task, int size) {
        return "Got it. I've added this task: \n" + task.toString() + "\nNow you have "
                + size + " tasks in your list";
    }

    /**
     * Display message upon successful deletion of task
     * @param task task
     */
    public static String displayDeletedTaskMessage(Task task) {
        return "Nice! I've removed this task: \n" + task.toString();
    }

    /**
     * Display message when task status is changed to completed
     * @param task task
     */
    public String displayDoneTaskMessage(Task task) {
        return "Nice! I'll make this task as completed: " + task.toString();
    }

    /** Display exception messages upon encountering errors
     * @param e exception messages
     * @return
     */
    public String showError(String e) {
        return e;
    }

}
