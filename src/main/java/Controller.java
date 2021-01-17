import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final static String INDENT = "\t";
    private final static String NEWLINE = System.lineSeparator();
    private final static String LINE = INDENT + "__________________________________________________"
            + "______________" + NEWLINE;
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
        try {
            if (input.startsWith("done")) {
                doneTask(input);
            } else if (input.equals("list")) {
                printList();
            } else {
                addTask(input);
            }
        } catch (DukeException e) {
            String output = String.format(INDENT + " %s", e);
            System.out.println(output);
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

    private void addTask(String task) throws DukeUnknownArgumentsException {
        try {
            Task t;
            if (task.startsWith("todo")) {
                t = createTodo(task);
            } else if (task.startsWith("deadline")) {
                t = createDeadline(task);
            } else if (task.startsWith("event")) {
                t = createEvent(task);
            } else {
                throw new DukeUnknownArgumentsException();
            }
            list.add(t);
            String output = String.format(INDENT + " Got it. I've added this task:" + NEWLINE
                            + INDENT + INDENT + " %s" + NEWLINE + INDENT + "Now you have %d tasks "
                            + "in the list."
                    , t, list.size());
            System.out.println(output);
        } catch (DukeNoDescriptionException e) {
            String output = String.format(INDENT + " %s", e);
            System.out.println(output);
        }
    }

    private Todo createTodo(String task) throws DukeNoDescriptionException {
        if (task.length() < 6) {
            throw new DukeNoDescriptionException("todo");
        } else {
            return new Todo(task.substring(5));
        }
    }

    private Deadline createDeadline(String task) throws DukeNoDescriptionException {
        if (task.length() < 10) {
            throw new DukeNoDescriptionException("deadline");
        } else {
            task = task.substring(9);
            return new Deadline(task.split("/"));
        }
    }

    private Event createEvent(String task) throws DukeNoDescriptionException {
        if (task.length() < 7) {
            throw new DukeNoDescriptionException("event");
        } else {
            task = task.substring(6);
            return new Event(task.split("/"));
        }
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
