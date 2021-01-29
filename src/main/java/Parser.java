import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Makes sense of the user command
 */
public class Parser {

    /**
     * Responds to the user command accordingly
     * @param input command-line input supplied by the user
     * @param ui    user interface in charge of printing out responses to the user
     * @throws DukeException if user command is invalid
     */
    public static String handleUserCommand(String input, Ui ui) throws DukeException {
        if (input.equals("bye")) {
            Duke.isFinished = true;

            return ui.showGoodbyeMessage();
        } else if (input.equals("list")) {
            //ui.showTaskList();
            return ui.showTaskList();
        } else if (input.startsWith("find")) {
            String keyword = getKeyword(input);

            ArrayList<String> matchedTasks = TaskList.findMatchingTasks(keyword);

            //ui.showMatchingTasks(matchedTasks);
            return ui.displayArraylist(matchedTasks);
        } else if (input.startsWith("done ")) {
            Task finishedTask = getFinishedTask(input);

            TaskList.setDone(finishedTask);

            //ui.showTaskDone(finishedTask);
            return ui.displayMessage("Nice! I've marked this task as done:" + finishedTask);
        } else if (input.startsWith("todo ")) {
            if (input.split(" ").length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String todoTask = getTodoTask(input);

            TaskList.createTodoTask(todoTask);

            //ui.showTaskAdded();
            //ui.showNumberOfTasks();
            return ui.displayMessage("added!\n" + "you have " + TaskList.updatedTaskList.size()
                    + " tasks in your list");
        } else if (input.startsWith("event ")) {
            String eventTask = getEventTask(input);
            String date = getEventDate(input);

            TaskList.createEventTask(eventTask, date);

            //ui.showTaskAdded();
            //ui.showNumberOfTasks();
            return ui.displayMessage("added!\n" + "you have " + TaskList.updatedTaskList.size()
                    + " tasks in your list");
        } else if (input.startsWith("deadline ")) {
            String deadlineTask = getDeadlineTask(input);
            LocalDate deadline = getDeadlineDate(input);

            TaskList.createDeadlineTask(deadlineTask, deadline);

            //ui.showTaskAdded();
            //ui.showNumberOfTasks();
            return ui.displayMessage("added!\n" + "you have " + TaskList.updatedTaskList.size()
                    + " tasks in your list");
        } else if (input.startsWith("delete ")) {
            int taskNumber = getTaskNumberToBeDeleted(input);
            Task taskDeleted = getTaskToBeDeleted(taskNumber);

            TaskList.deleteTask(taskNumber);

            //ui.showTaskDeleted(taskDeleted);
            //ui.showNumberOfTasks();
            return ui.displayMessage("Noted. I've removed this task: " + taskDeleted + "\n" +
                    "you have " + TaskList.updatedTaskList.size() + " tasks in your list");
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static String getKeyword(String input) {
        return input.substring(6);
    }

    /**
     * Returns the completed task from the task list
     * @param input supplied by the user command
     * @return the task description of the completed task
     */
    public static Task getFinishedTask(String input) {
        int taskNumber = Integer.parseInt(input.substring(5));
        return TaskList.updatedTaskList.get(taskNumber - 1);
    }

    /**
     * Returns the task number to be deleted upon filtering the input
     * @param input supplied by the user command
     * @return task number to be deleted
     */
    public static int getTaskNumberToBeDeleted(String input) {
        return Integer.parseInt(input.substring(7));
    }

    /**
     * Returns the task to be deleted from the task lsit
     * @param taskNumber of the task (in the task list) to be deleted
     * @return the task to be deleted
     */
    public static Task getTaskToBeDeleted(int taskNumber) {
        return TaskList.updatedTaskList.get(taskNumber - 1);
    }

    /**
     * Returns the todo task description upon filtering the input
     * @param input supplied by the user command
     * @return todo task description
     */
    public static String getTodoTask(String input) {
        return input.substring(5);
    }

    /**
     * Returns the event task description upon filtering the input
     * @param input supplied by the user command
     * @return event task description
     */
    public static String getEventTask(String input) {
        return input.substring(6).split("/")[0];
    }

    /**
     * Returns the event date upon filtering the input
     * @param input supplied by the user command
     * @return event date
     */
    public static String getEventDate(String input) {
        return input.substring(6).split("/")[1].substring(3);
    }

    /**
     * Returns the deadline task description upon filtering the input
     * @param input supplied by the user command
     * @return deadline task description
     */
    public static String getDeadlineTask(String input) {
        return input.substring(9).split("/", 2)[0];
    }

    /**
     * Returns the deadline date upon filtering the input
     * @param input supplied by the user command
     * @return deadline date
     */
    public static LocalDate getDeadlineDate(String input) {
        return LocalDate.parse(input.substring(9).split("/", 2)[1].substring(3)
                .replaceAll("/", "-"));
    }



}
