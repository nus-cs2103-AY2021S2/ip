import exceptions.DukeEmptyListException;
import exceptions.DukeUnknownArgumentsException;

import java.util.Scanner;

public class Controller {
    private final static String INDENT = "\t";
    private final static String NEWLINE = System.lineSeparator();
    private final static String LINE = INDENT + "__________________________________________________"
            + "______________" + NEWLINE;
    private final static String GREETING = INDENT + "Hello! I'm Duke" + NEWLINE + INDENT + "What" +
            " can I do for you?" + NEWLINE;
    private final static String BYE_MSG = INDENT + " Bye. Hope to see you again soon!" + NEWLINE;
    private final static String END_COMMAND = "bye";
    private final TaskList taskList;
    private final Storage storage;

    public Controller() {
        storage = Storage.getInstance();
        taskList = new TaskList(storage);
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
                taskList.deleteTask(input);
                break;
            case ADD:
                addTask(input);
                break;
            }
           taskList.updateSave(storage);
        } catch (DukeUnknownArgumentsException e) {
            String errorMsg = String.format(INDENT + " %s", e);
            System.out.println(errorMsg);
        } catch (NumberFormatException e) {
            System.out.println(INDENT + "Please enter an integer as argument. " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.printf(INDENT + "Please enter an integer within your tasks " +
                    "size: %d.%n", taskList.size());
        } catch (DukeEmptyListException e) {
            System.out.println(INDENT + e);
        }
    }

    private void doneTask(String input) {
        taskList.done(input);
    }

    private void addTask(String input) throws DukeUnknownArgumentsException {
        taskList.add(input);
    }


    private void printList() {
        taskList.print();
    }

}
