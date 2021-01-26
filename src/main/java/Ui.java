import java.util.Scanner;
import java.util.List;

/**
 * List of all responses
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

    public void greetings() {
        System.out.println(LOGO);
        echoBorder(List.of("Hello! I'm Duke",
                "What can I do for you?",
                "Enter \"load\" to restore previously saved checklist.",
                "Enter \"help\" to see list of commands."));
    }

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

    public void displayError(DukeException e) {
        echoSingleBorder(e.getMessage());
    }

    public void exit() {
        echoSingleBorder("Bye. Hope to see you again soon!");
    }

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

    public void completeTask(String task) {
        echoBorder(List.of("Nice! I've marked this task as done:", task));
    }

    public void addTask(String task, int size) {
        echoBorder(List.of("Got it. I've added this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    public void deleteTask(String task, int size) {
        echoBorder(List.of("Noted. I've removed this task:",
                task,
                "Now you have " + size + " tasks in the list."));
    }

    public void saved() {
        echoSingleBorder("TaskList have been saved!");
    }

    public void loaded() {
        echoSingleBorder("TaskList loaded successfully!");
    }
}
