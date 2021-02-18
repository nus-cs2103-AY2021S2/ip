package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.ParseException;
import duke.expenses.Expense;
import duke.tasks.Task;

public class GuiUi {

    private static final StringBuffer boxBound = new StringBuffer();

    /**
     * Takes in a string and convert it into a chat box text.
     *
     * @param content content to put into chat box
     */
    public static String insertMsgIntoChatBox(String content) {
        return GuiUi.boxBound + "\n" + content + "\n" + GuiUi.boxBound;
    }

    /**
     * Informs the user there is error when parsing the date of tasks.
     *
     * @return error message
     */
    public static String displayParseError() {
        return insertMsgIntoChatBox("Input not accepted. "
                + "Hint: Use '/by OR /at YYYY-MM-DD' after description"
                + " and '/time HH:mm:ss' after date is specified.\n");
    }

    /**
     * Informs the user when there is an error for parsing the input to create an Expense object.
     *
     * @return error message
     */
    public static String displaySpendParseError() {
        return insertMsgIntoChatBox("Input not accepted. "
                + "Hint: Use '/amt [amount spent]' after description"
                + " and '/date YYYY-MM-DD' after amount spent is specified.\n");
    }

    /**
     * Returns a String indicating a generic error is encountered.
     *
     * @return String of error message
     */
    public static String displayGenericError() {
        try {
            throw new ParseException("Tasker does not understand what you did there!\n");
        } catch (ParseException e) {
            return insertMsgIntoChatBox(e.getErrMsg());
        }
    }

    /**
     * Returns a String indicating that Tasker is unable to save file.
     *
     * @return String of error message
     */
    public static String displayFailToSaveError() {
        try {
            throw new DukeException("Your save file or path is corrupted!\n");
        } catch (DukeException e) {
            return insertMsgIntoChatBox(e.getErrMsg());
        }
    }

    /**
     *  Returns a String to prompt the user to use a valid number to delete/mark task
     *  and shows the number of Tasks in the taskList.
     *
     * @param taskList the list of Tasks.
     * @return String of error message
     */
    public static String displayIndexOutOfBoundsError(TaskList taskList) {
        return insertMsgIntoChatBox("Dude. You have only "
                + taskList.getTasks().size()
                + " tasks, please delete/mark the added tasks.");
    }

    /**
     * Shows a list of all the Tasks in the given TaskList on receive of "list" command.
     *
     * @param taskList list of Tasks
     * @return formatted String
     */
    public static String displayListOfTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int n = tasks.size();
        if (n == 0) {
            return insertMsgIntoChatBox("There is no task.\n");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append(i + 1).append(") ").append(tasks.get(i)).append("\n");
        }
        String output = new String(buffer);
        return insertMsgIntoChatBox("Here are your tasks:\n" + output);
    }

    /**
     * Shows a list of all the Expenses in the given TaskList on receive of "list_e" command.
     *
     * @param expensesList list of Expenses
     * @return formatted String
     */
    public static String displayListOfExpenses(ExpensesList expensesList) {
        ArrayList<Expense> expenses = expensesList.getExpenses();
        int n = expenses.size();
        if (n == 0) {
            return insertMsgIntoChatBox("There is no expense yet.\n");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++) {
            buffer.append(i + 1).append(") ").append(expenses.get(i)).append("\n");
        }
        String output = new String(buffer);
        return insertMsgIntoChatBox("Here are your expenses:\n" + output);
    }


    /**
     * Displays when Tasker receives "bye" command and exits.
     */
    public static String displayExitMsg() {
        String goodbye = "Bye. Remember to do your tasks!\n"
                + "See you next time on Tasker!\n";
        return insertMsgIntoChatBox(goodbye);
    }

    /**
     * Returns a String which contains the matching results.
     *
     * @param taskList list of tasks
     */
    public static String showMatchingResult(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int numOfTasks = tasks.size();
        if (numOfTasks == 0) {
            return insertMsgIntoChatBox("There is no matching task :(\n");
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < numOfTasks; i++) {
            buffer.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        String output = new String(buffer);
        return insertMsgIntoChatBox("Here are the matching tasks in your list:\n" + output);
    }

    /**
     * Informs the user that a task is added successfully.
     *
     * @param taskList TaskList
     * @param task task to be added
     * @return String of formatted message
     */
    public static String displayAddSuccess(TaskList taskList, Task task) {
        return insertMsgIntoChatBox("Got it. I've added this task:" + '\n'
                + task + "\n"
                + "Now you have " + taskList.getTasks().size() + " tasks in the list.\n");
    }

    /**
     * Informs the user that an expense is added successfully.
     *
     * @param expensesList ExpensesList
     * @param expense expense to be added
     * @return String of formatted message
     */
    public static String displayAddExpenseSuccess(ExpensesList expensesList, Expense expense) {
        return insertMsgIntoChatBox("Got it. I've added this expense:" + '\n'
                + expense + "\n"
                + "Now you have spent a total of $" + expensesList.sumUp() + ".\n");
    }

    /**
     * Returns a 'successfully marked' message.
     *
     * @param task task done
     * @return String of formatted message
     */
    public static String displayMarkDoneSuccess(Task task) {
        return insertMsgIntoChatBox("Nice! I've marked this Task as done:\n" + task + "\n");
    }

    /**
     * Returns a 'successfully deleted' message.
     *
     * @param taskList TaskList
     * @param task task deleted
     * @return String of formatted message
     */
    public static String displayDeleteSuccess(TaskList taskList, Task task) {
        return insertMsgIntoChatBox("Got it. I've removed this task:" + '\n'
                + task + "\n" + "Now you have "
                + taskList.getTasks().size() + " tasks in the list.\n");
    }
}
