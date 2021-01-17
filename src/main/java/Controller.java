import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final static String INDENT = "\t";
    private final static String NEWLINE = System.lineSeparator();
    private final static String LINE = INDENT + "__________________________________" + NEWLINE;
    private final static String GREETING = INDENT + " Hello! I'm Duke\n\t What can I do for you?"
            + NEWLINE;
    private final static String ENDDUKE = INDENT + " Bye. Hope to see you again soon!" + NEWLINE;
    private final static String ENDCOMMAND = "bye";
    private final List<Task> list;

    public Controller() {
        list = new ArrayList<>();
    }

    public void run() {
        String startMsg = LINE + GREETING + LINE;
        System.out.println(startMsg);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals(ENDCOMMAND)) {
            System.out.print(LINE);
            handleInput(input);
            System.out.print(LINE);
            input = sc.nextLine();
        }

        System.out.println(LINE + ENDDUKE + LINE);
    }

    private void handleInput(String input) {
        if (input.startsWith("done")) {
            doneTask(input);
        } else if(input.equals("list")) {
            printList();
        } else {
            addTask(input);
        }
    }

    private void doneTask(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        Task task = list.get(index);
        task.done();
        String output = String.format(INDENT + " Nice! I've marked this task as done:" + NEWLINE
                + INDENT + INDENT + " %s", task);
        System.out.println(output);
    }

    private void addTask(String task) {
        Task t;
        if (task.startsWith("todo")) {
            t = new Todo(task.substring(5));
        } else if (task.startsWith("deadline")) {
            task = task.substring(9);
            t = new Deadline(task.split("/"));
        } else if (task.startsWith("event")) {
            task = task.substring(6);
            t = new Event(task.split("/"));
        } else {
            t = new Todo("");
        }
        list.add(t);
        String output = String.format(INDENT + " Got it. I've added this task:" + NEWLINE + INDENT
                + INDENT + " %s" + NEWLINE + INDENT + "Now you have %d tasks in the list."
                , t, list.size());
        System.out.println(output);
    }

    private void printList() {
        System.out.println(INDENT + " Here are the tasks in your list:");
        int num = 1;
        for (Task task : list) {
            String output = String.format(INDENT + " %d.%s", num, task);
            System.out.println(output);
            num++;
        }
    }
}
