import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Makes sense of the user command.
 */
public class Parser {
    protected static boolean hasInputError;
    private static final int TODO_TASK_INDEX = 5;
    private static final int KEYWORD_INDEX = 5;
    private static final int COMPLETED_TASK_INDEX = 5;
    private static final int DELETE_TASK_INDEX = 7;
    private static final int EVENT_TASK_INDEX = 6;
    private static final int EVENT_DATE_INDEX = 3;
    private static final int DEADLINE_TASK_INDEX = 9;
    private static final int DEADLINE_DATE_INDEX = 3;

    /**
     * Returns the response to the user's input.
     *
     * @param input input supplied by the user.
     * @param ui user interface in charge of displaying the responses to the user.
     * @throws DukeException if user command is invalid.
     */
    public static String handleUserCommand(String input, Ui ui) throws DukeException {
        if (input.equals("bye")) {
            Duke.isFinished = true;

            return ui.showGoodbyeMessage();
        } else if (input.equals("list")) {
            return ui.showTaskList();
        } else if (input.startsWith("find")) {
            String keyword = getKeyword(input);
            ArrayList<String> matchedTasksList = TaskList.findMatchingTasks(keyword);

            return ui.showMatchedTasksList(matchedTasksList);
        } else if (input.startsWith("done ")) {
            Task finishedTask = getFinishedTask(input);
            TaskList.setDone(finishedTask);

            return ui.showTaskMarkedDone(finishedTask);
        } else if (input.startsWith("todo ")) {
            if (hasEmptyTaskDescription(input)) {
                setInputError();
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String todoTask = getTodoTask(input);
            TaskList.createTodoTask(todoTask);

            int currentTaskListSize = TaskList.updatedTaskList.size();
            Task taskAdded = TaskList.updatedTaskList.get(currentTaskListSize - 1);
            return ui.showTaskAdded(taskAdded, currentTaskListSize);
        } else if (input.startsWith("event ")) {
            String eventTask = getEventTask(input);
            String date = getEventDate(input);
            TaskList.createEventTask(eventTask, date);

            int currentTaskListSize = TaskList.updatedTaskList.size();
            Task taskAdded = TaskList.updatedTaskList.get(currentTaskListSize - 1);
            return ui.showTaskAdded(taskAdded, currentTaskListSize);
        } else if (input.startsWith("deadline ")) {
            String deadlineTask = getDeadlineTask(input);
            LocalDate deadline = getDeadlineDate(input);
            TaskList.createDeadlineTask(deadlineTask, deadline);

            int currentTaskListSize = TaskList.updatedTaskList.size();
            Task taskAdded = TaskList.updatedTaskList.get(currentTaskListSize - 1);
            return ui.showTaskAdded(taskAdded, currentTaskListSize);
        } else if (input.startsWith("delete ")) {
            int taskNumber = getTaskNumberToBeDeleted(input);
            Task taskDeleted = getTaskToBeDeleted(taskNumber);
            TaskList.deleteTask(taskNumber);

            int currentTaskListSize = TaskList.updatedTaskList.size();
            return ui.showTaskDeleted(taskDeleted, currentTaskListSize);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void setInputError() {
        hasInputError = true;
    }

    public static boolean hasEmptyTaskDescription(String input) {
        return input.split(" ").length == 1;
    }

    public static String getKeyword(String input) {
        return input.substring(KEYWORD_INDEX);
    }

    /**
     * Returns the completed task from the task list.
     *
     * @param input supplied by the user.
     * @return the task description of the completed task.
     */
    public static Task getFinishedTask(String input) {
        int taskNumber = Integer.parseInt(input.substring(COMPLETED_TASK_INDEX));
        return TaskList.updatedTaskList.get(taskNumber - 1);
    }

    /**
     * Returns the task number to be deleted upon filtering the input.
     *
     * @param input supplied by the user.
     * @return task number to be deleted.
     */
    public static int getTaskNumberToBeDeleted(String input) {
        return Integer.parseInt(input.substring(DELETE_TASK_INDEX));
    }

    /**
     * Returns the task to be deleted from the task list.
     *
     * @param taskNumber of the task (in the task list) to be deleted.
     * @return the task to be deleted.
     */
    public static Task getTaskToBeDeleted(int taskNumber) {
        return TaskList.updatedTaskList.get(taskNumber - 1);
    }

    /**
     * Returns the todo task description upon filtering the input.
     *
     * @param input supplied by the user.
     * @return todo task description.
     */
    public static String getTodoTask(String input) {
        return input.substring(TODO_TASK_INDEX);
    }

    /**
     * Returns the event task description upon filtering the input.
     *
     * @param input supplied by the user.
     * @return event task description.
     */
    public static String getEventTask(String input) {
        return input.substring(EVENT_TASK_INDEX).split("/")[0];
    }

    /**
     * Returns the event date upon filtering the input.
     *
     * @param input supplied by the user.
     * @return event date.
     */
    public static String getEventDate(String input) {
        return input.substring(EVENT_TASK_INDEX).split("/")[1].substring(EVENT_DATE_INDEX);
    }

    /**
     * Returns the deadline task description upon filtering the input.
     *
     * @param input supplied by the user.
     * @return deadline task description.
     */
    public static String getDeadlineTask(String input) {
        return input.substring(DEADLINE_TASK_INDEX).split("/", 2)[0];
    }

    /**
     * Returns the deadline date upon filtering the input.
     *
     * @param input supplied by the user.
     * @return deadline date.
     */
    public static LocalDate getDeadlineDate(String input) {
        return LocalDate.parse(input.substring(DEADLINE_TASK_INDEX).split("/", 2)[1]
                .substring(DEADLINE_DATE_INDEX).replaceAll("/", "-"));
    }
}
