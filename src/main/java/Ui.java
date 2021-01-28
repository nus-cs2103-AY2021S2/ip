import java.util.Scanner;
import java.util.List;

/**
 * Ui interacts with users to receive input and display output.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"   
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BORDER_LINE = "\t____________________________________________________________";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads input from user.
     * @return String input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    private void echo(List<String> msgs) {
        for (String s : msgs) {
            System.out.println("\t  " + s);
        }
    }

    private void echoSingle(String msg) {
        System.out.println("\t  " + msg);
    }

    private void echoBorder(List<String> msgs) {
        System.out.println(BORDER_LINE);
        echo(msgs);
        System.out.println(BORDER_LINE);
        System.out.println();
    }

    private void echoSingleBorder(String msg) {
        System.out.println(BORDER_LINE);
        echoSingle(msg);
        System.out.println(BORDER_LINE);
        System.out.println();
    }

    /**
     * Displays greeting messages when Duke starts.
     */
    public void greetings() {
        System.out.println(LOGO);
        echoBorder(List.of("Hello! I'm Duke",
                "What can I do for you?",
                "Enter \"help\" to see list of commands."));
    }

    /**
     * Lists out commands and their functions.
     */
    public void help() {
        echoBorder(List.of("List of commands:",
                "bye",
                "- Close Duke",
                "list",
                "  - List out all task",
                "done [number]",
                "  - Mark selected task as done",
                "todo [description]",
                "  - Add a todo task",
                "deadline [description] /by [due date]",
                "  - Add a deadline task with a due date (YYYY-MM-DD)",
                "event [description] /at [date]",
                "  - Add a event task with a date (YYYY-MM-DD)",
                "delete [number]",
                "  - Delete a task",
                "save",
                "  - save checklist to \"data/dukeData.txt\"",
                "load",
                "  - Load previously saved checklist",
                "help",
                "  - Display list of commands",
                "search [keyword]",
                "  - Display all task containing the following keyword.",
                "  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date."));
    }

    /**
     * Displays error messages.
     * @param e Error to be displayed.
     */
    public void displayError(DukeException e) {
        echoSingleBorder(e.getMessage());
    }

    /**
     * Displays goodbye message.
     */
    public void exit() {
        echoSingleBorder("Bye. Hope to see you again soon!");
    }

    /**
     * Displays list contents.
     * @param lst List to be displayed.
     */
    public void displayList(List<String> lst) {
        if (lst.size() == 0) {
            echoSingleBorder("No task found!");
        } else {
            System.out.println(BORDER_LINE);
            echoSingle("Here are the tasks in your list:");
            echo(lst);
            System.out.println(BORDER_LINE);
            System.out.println();
        }
    }

    /**
     * Displays message after task is marked as done.
     * @param task Task  marked as done.
     */
    public void completeTask(String task) {
        echoBorder(List.of("Nice! I've marked this task as done:", task));
    }

    /**
     * Displays added task and the current number of task in the TaskList.
     * @param task Task added to TaskList.
     * @param size Current size of TaskList.
     */
    public void addTask(String task, int size) {
        echoBorder(List.of("Got it. I've added this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    /**
     * Displays deleted task and the current number of task in the TaskList.
     * @param task Task deleted from TaskList.
     * @param size Current size of TaskList.
     */
    public void deleteTask(String task, int size) {
        echoBorder(List.of("Noted. I've removed this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    /**
     * Displays messages after TaskList has been saved.
     */
    public void saved() {
        echoSingleBorder("TaskList have been saved!");
    }

    /**
     * Displays messages after TaskList has been loaded.
     */
    public void loaded() {
        echoSingleBorder("TaskList loaded successfully!");
    }

    /**
     * Prompts user to save file.
     * @return Response from user.
     */
    public String saveFilePrompt() {
        echoSingleBorder("Do you want to save the current tasklist? y/n");
        return readInput();
    }

    /**
     * Confirms if user want to delete this task.
     * @return Response from user.
     */
    public String deleteTaskPrompt() {
        echoSingleBorder("Are you sure you want to delete this task? y/n");
        return readInput();
    }

    /**
     * Displays delete aborted message.
     */
    public void abortDelete() {
        echoSingleBorder("Deletion cancelled.");
    }
}
