package duke;

import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Ui class is a class that handles all IO aspects in Duke.
 */
public class Ui {

    /**
     * The messages that the chat bot will send out.
     */
    static final String BOT_LOGO = "     _       _        \n"
        + "    | |_   _| | _____ \n"
        + " _  | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    static final String WELCOME_MESSAGE = "Hello! I am Juke, your personal assistant, how can I help you today?";
    static final String HELP_PROMPT = "[Type 'help' to display instructions]";
    static final String HELP_INTRO = "Here are the list of commands for Juke: \n";
    static final String[] COMMANDS = {"list", "bye", "todo", "deadline", "event", "done", "find", "delete", "help"};
    static final String LIST_HELP = "1. list - lists out all existing tasks that have been stored in Juke";
    static final String BYE_HELP = "2. bye - to exit Juke";
    static final String TODO_HELP = "3. todo <description> - adds a Todo task with the specified <description>";
    static final String DEADLINE_HELP = "4.  deadline <description> /by <yyyy-MM-dd HH:mm> - adds a Deadline task with"
        + " the specified <description> by <yyyy-MM-dd HH:mm>";
    static final String EVENT_HELP = "event <description> /at <yyyy-MM-dd HH:mm> - adds an Event task with"
        + " the specified <description>, date and time";
    static final String DONE_HELP = "done <taskId>' - marks a specific task as done";
    static final String FIND_HELP = "find <regex> - to find all items containing the specified "
        + "regex in its description";
    static final String DELETE_HELP = "delete <taskId>' - deletes a specific task";
    private Scanner sc;

    /**
     * Constructor method for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);

    }

    /**
     * Shows Duke's welcome message.
     */
    public String showWelcome() {
        /*String s = "     _       _        \n"
            + "    | |_   _| | _____ \n"
            + " _  | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";*/
        StringBuilder sb = new StringBuilder(Ui.WELCOME_MESSAGE + "\n" +"\n" + Ui.HELP_PROMPT);
        return sb.toString();
    }


    public String readCommand() {
        return sc.nextLine();
    }

    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    /**
     * Shows the error message from exception.
     * @param error
     */
    public String showError(String error) {
        return error;
    }

    /**
     *
     * @param message
     */
    public String print(String message) {
        return message;
    }

    /**
     * Prints the task added
     * @param task Task to be added.
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task: \n" + task.toString();
    }

    /**
     * Prints the tasks in the list.
     * @param taskList The list of tasks.
     */
    public String showNoOfItems(TaskList taskList) {
        int num = taskList.getSize();
        if (num == 1) {
            return "Now you have " + num + " task in the list.\n";
        } else {
            return "Now you have " + num + " tasks in the list.\n";
        }
    }

    /**
     * Prints the task marked as done.
     * @param task The task to be marked as done.
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Prints the task to be removed.
     * @param task The task to be removed.
     */
    public String showTaskRemoved(Task task) {
        return "Noted. I've removed this task: \n" + task.toString();

    }

    public String showBye() {
        return "    Bye. Hope to see you again soon!";
    }

    public String showFoundTasks() {
        return "    Here are the matching tasks in your list:";
    }

    public String showHelpReply() {
        StringBuilder sb = new StringBuilder("[");
        for (String command : Ui.COMMANDS) {
            sb.append(String.format("%s ", command));
        }
        // Deletes whitespace in the last character and adds ']' to StringBuilder
        sb.setLength(sb.length() - 1);
        sb.append("]");

        StringBuilder reply = new StringBuilder();
        String[] commandsToAdd = {Ui.HELP_INTRO, sb.toString().trim() , Ui.LIST_HELP, Ui.BYE_HELP, Ui.TODO_HELP, Ui.DEADLINE_HELP,
            Ui.EVENT_HELP, Ui.DONE_HELP, Ui.FIND_HELP, Ui.DELETE_HELP};
        for(String message : commandsToAdd) {
            reply.append(message).append("\n");
        }
        return reply.toString().trim();
    }

    public Scanner getSc() {
        return this.sc;
    }

}
