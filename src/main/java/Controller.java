import exceptions.DukeNoDescriptionException;
import exceptions.DukeUnknownArgumentsException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Controller {
    private final static String INDENT = "\t";
    private final static String NEWLINE = System.lineSeparator();
    private final static String LINE = INDENT + "__________________________________________________"
            + "______________" + NEWLINE;
    private final static String GREETING = INDENT + " Hello! I'm Duke" + NEWLINE + INDENT + "What" +
            " can I do for you?" + NEWLINE;
    private final static String BYE_MSG = INDENT + " Bye. Hope to see you again soon!" + NEWLINE;
    private final static String END_COMMAND = "bye";
    private final static String DATA_DIR = "./data";
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
        CommandType command = Parser.parseCommand(input);
        executeCommand(input, command);
    }

    private void executeCommand(String input, CommandType command) {
        try {
            switch (command) {
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
            update();
        } catch (DukeUnknownArgumentsException e) {
            String errorMsg = String.format(INDENT + " %s", e);
            System.out.println(errorMsg);
        }
    }

    private void doneTask(String input) {
        int index = Parser.stringToIndex(input, 5);
        Task task = list.get(index);
        task.done();
        String output = String.format(INDENT + " Nice! I've marked this task as done:" + NEWLINE
                + INDENT + INDENT + " %s", task);
        System.out.println(output);
    }

    private void addTask(String input) throws DukeUnknownArgumentsException {
        try {
            Task t;
            AddCommandType command = Parser.inputToAddCommand(input);
            switch (command) {
            case TODO:
                t = createTodo(input);
                break;
            case DEADLINE:
                t = createDeadline(input);
                break;
            case EVENT:
                t = createEvent(input);
                break;
            default:
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

    private Todo createTodo(String input) throws DukeNoDescriptionException {
        input = Parser.parseTodoInput(input);
        return new Todo(input);
    }


    private Deadline createDeadline(String input) throws DukeNoDescriptionException,
            DateTimeParseException {
        String description = Parser.obtainDescription(input, AddCommandType.DEADLINE);
        LocalDate deadline = Parser.obtainDate(input, AddCommandType.DEADLINE);
        return new Deadline(description, deadline);
    }

    private Event createEvent(String input) throws DukeNoDescriptionException {
        String description = Parser.obtainDescription(input, AddCommandType.EVENT);
        LocalDate eventTime = Parser.obtainDate(input, AddCommandType.EVENT);
        return new Event(description, eventTime);
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
        int index = Parser.stringToIndex(input, 7);
        Task t = list.get(index);
        list.remove(index);
        String output =
                String.format(INDENT + " Noted. I've removed this task:" + NEWLINE + INDENT + INDENT
                        + t + NEWLINE + INDENT + " Now you have %d tasks in the list.",
                        list.size());
        System.out.println(output);
    }

    private void update() {
        try {
            Path dataPath = Paths.get(DATA_DIR);
            Files.createDirectories(dataPath);
            File saveFile = new File("./data/save.txt");
            FileWriter fw = new FileWriter(saveFile);
            fw.write(tasks());
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to create data directory" + e.getMessage());
        }
    }

    private String tasks() {
        String res = "";
        for (Task task : list) {
            res = res.concat(task.data() + NEWLINE);
        }
        return res;
    }
}
