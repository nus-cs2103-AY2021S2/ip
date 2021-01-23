import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final static String INDENT = "\t";
    private final static String NEWLINE = System.lineSeparator();
    private final static String LINE = INDENT + "__________________________________________________"
            + "______________" + NEWLINE;
    private final static String GREETING = INDENT + " Hello! I'm Duke" + NEWLINE + INDENT + "What" +
            " can I do for you?" + NEWLINE;
    private final static String BYE_MSG = INDENT + " Bye. Hope to see you again soon!" + NEWLINE;
    private final static String END_COMMAND = "bye";
    private final List<Task> list;

    public Controller() {
        list = new ArrayList<>();
    }

    public void run() {
        String startMsg = LINE + GREETING + LINE;
        System.out.println(startMsg);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals(END_COMMAND)) {
            System.out.print(LINE);
            handleInput(input);
            System.out.print(LINE);
            input = sc.nextLine();
        }

        System.out.println(LINE + BYE_MSG + LINE);
    }

    private void handleInput(String input) {
        try {
            CommandType type = parseCommand(input);
            switch (type) {
            case DONE:
                doneTask(input);
                break;
            case LIST:
                printList();
                break;
            case DELETE:
                deleteTask(input);
                break;
            case ADD:
                addTask(input);
                break;
            }
        } catch (DukeUnknownArgumentsException e) {
            String errorMsg = String.format(INDENT + " %s", e);
            System.out.println(errorMsg);
        }
    }

    private CommandType parseCommand(String input) {
        if (input.startsWith("done")) {
            return CommandType.DONE;
        } else if (input.startsWith("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else {
            return CommandType.ADD;
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
                            + INDENT + INDENT + " %s" + NEWLINE + INDENT + " Now you have %d tasks "
                            + "in the list."
                    , t, list.size());
            System.out.println(output);
        } catch (DukeNoDescriptionException e) {
            String output = String.format(INDENT + " %s", e);
            System.out.println(output);
        } catch (DateTimeParseException e) {
            System.out.println(INDENT + "Date is not input correctly.");
        }
    }

    private Todo createTodo(String task) throws DukeNoDescriptionException {
        if (task.length() < 6 || task.substring(5).isBlank()) {
            throw new DukeNoDescriptionException("todo");
        } else {
            return new Todo(task.substring(5));
        }
    }

    private Deadline createDeadline(String task) throws DukeNoDescriptionException,
            DateTimeParseException {
        if (task.length() < 10 || task.substring(9).isBlank()) {
            throw new DukeNoDescriptionException("deadline");
        } else {
            task = task.substring(9);
            String[] inputs = task.split("/");
            String description = inputs[0];
            String deadline = inputs[1].substring(3);
            LocalDate deadlineDate = LocalDate.parse(deadline);
            return new Deadline(description, deadlineDate);
        }
    }

    private Event createEvent(String task) throws DukeNoDescriptionException {
        if (task.length() < 7 || task.substring(6).isBlank()) {
            throw new DukeNoDescriptionException("event");
        } else {
            task = task.substring(6);
            String[] inputs = task.split("/");
            String description = inputs[0];
            String eventTime = inputs[1].substring(3);
            LocalDate eventTimeDate = LocalDate.parse(eventTime);
            return new Event(description, eventTimeDate);
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

    private void deleteTask(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task t = list.get(index);
        list.remove(index);
        String output =
                String.format(INDENT + " Noted. I've removed this task:" + NEWLINE + INDENT + INDENT
                        + t + NEWLINE + INDENT + " Now you have %d tasks in the list.",
                        list.size());
        System.out.println(output);
    }
}
