import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String EMPTY_TASKLIST_MESSAGE = "You do not have anything to do at the moment!";
    private static final String SHOWING_TASKLIST_MESSAGE = "Here are the tasks in your list:";
    private static final String INDEXED_TASK_ITEM_FORMAT = "%d.%s";
    private static final String TASKLIST_SIZE_MESSAGE_FORMAT = "Now you have %d tasks in your list.";
    
    private final Scanner in;
    private final PrintStream out;
    
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }
    
    public String getUserCommand() {
        return in.next();
    }
    
    public String getUserCommandArguments() {
        return in.nextLine();
    }
    
    public void printDivider() {
        show(DIVIDER);
    }

    public void printGreeting() {
        String welcomeMsg = String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO);
        show(DIVIDER, welcomeMsg);
    }

    public void printExitMessage() {
        show(EXIT_MESSAGE, DIVIDER);
    }

    public void printAllTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            show(EMPTY_TASKLIST_MESSAGE);
        } else {
            show(SHOWING_TASKLIST_MESSAGE, convertListToString(taskList.getAsImmutableList()));
        }
        
    }
    
    private String convertListToString(List<Task> taskList) {
        StringBuilder tasksListAsString = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            tasksListAsString.append(String.format(INDEXED_TASK_ITEM_FORMAT, i, taskList.get(i - 1)));
            if (i == taskList.size()) {
                break;
            }
            tasksListAsString.append("\n");
        }
        return tasksListAsString.toString();
    }

    public void printMarkedAsDoneMessage(Task task) {
        show("Nice! I've marked this task as done:", 
                task.toString());
    }

    public void printAddedMessage(Task task) {
        show("Got it. I've added this task:", 
                "  " + task.toString());
    }
    
    public void printTaskListSize(TaskList taskList) {
        show(String.format(TASKLIST_SIZE_MESSAGE_FORMAT, taskList.size()));
    }

    public void printDeletedMessage(Task task) {
        show("Noted. I've removed this task:", 
                "  " + task.toString());
    }

    public void printHelp() {
        show("Here are the list of available commands:", 
                "BYE:\nExit the program\nUsage: bye\n", 
                "LIST:\nPrint the list of current tasks\nUsage: list\n", 
                "DONE:\nMark a task as completed\nUsage: done <task_number>\n", 
                "DELETE:\nDelete a task\nUsage: delete <task_number>\n", 
                "TODO:\nAdd a todo task\nUsage: todo <task_description>\n", 
                "DEADLINE:\nAdd a deadline task\nUsage: deadline <task_description> /by dd/mm/yyyy HHHH\n", 
                "EVENT:\nAdd an event task\nUsage: event <task_description> /at <event_time>\n", 
                "HELP:\nPrint available commands\nUsage: help");
    }
    
    private void show(String... messages) {
        for (String msg : messages) {
            out.println(msg);
        }
    }
}
