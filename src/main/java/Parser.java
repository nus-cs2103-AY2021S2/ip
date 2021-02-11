import java.time.format.DateTimeParseException;
import java.util.ArrayDeque;

import javafx.scene.control.Label;

/**
 * Parser class for CS2103T iP. Handles the logic of the program.
 */
public class Parser {
    private final TaskList list;
    private final Ui ui;

    /**
     * Creates a Parser object.
     * @param list TaskList object for data manipulation.
     * @param ui Ui object for messages.
     */
    public Parser(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    /**
     * Main logic of the program.
     * @return Label with response from program.
     */
    public Label parse(String input) {
        if (input.equals("list")) {
            return list.listAll();
        } else if (input.contains("done")) {
            return markDone(input);
        } else if (input.contains("delete")) {
            return delete(input);
        } else if (input.contains("todo")) {
            return createTodo(input);
        } else if (input.contains("deadline")) {
            return createDeadline(input);
        } else if (input.contains("event")) {
            return createEvent(input);
        } else if (input.contains("find")) {
            return find(input);
        } else if (input.contains("snooze")){
            return snooze(input);
        } else {
            return ui.print("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Label markDone(String input) {
        int number;
        try {
            number = (Integer.parseInt(input.substring(5))) - 1; // Index of input number starts at 5.
            return list.markDone(number);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printErr("No task was input to be marked as done.");
        } catch (IndexOutOfBoundsException e) {
            return ui.printErr("No task has been stored at that index yet.");
        } catch (NumberFormatException e) {
            return ui.printErr("Input index was not recognised.");
        }
    }

    private Label delete(String input) {
        int number;
        try {
            number = (Integer.parseInt(input.substring(7))) - 1;
            return list.delete(number);
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printErr("No task was input to be deleted.");
        } catch (IndexOutOfBoundsException e) {
            return ui.printErr("No task has been stored at that index yet.");
        } catch (NumberFormatException e) {
            return ui.printErr("Input index was not recognised.");
        }
    }

    private Label createTodo(String input) {
        String task = input.substring(4); // "todo" is 4 letters long.
        if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
            return ui.printErr("The description of a todo cannot be empty.");
        } else {
            return list.add(new Task(task, 0));
        }
    }

    private Label createDeadline(String input) {
        String task = input.substring(8); // "deadline" is 8 letters long.
        if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
            return ui.printErr("The description of a deadline cannot be empty.");
        } else {
            try {
                return list.add(new Task(task, 1));
            } catch (IllegalArgumentException e) {
                return ui.printErr("The description or date of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                return ui.printErr("The input date was not recognised.");
            }
        }
    }

    private Label createEvent(String input) {
        String task = input.substring(5); // "event" is 5 letters long.
        if (task.length() == 0 || task.equals(" ") || task.contains("  ")) {
            return ui.printErr("The description of an event cannot be empty.");
        } else {
            try {
                return list.add(new Task(task, 2));
            } catch (IllegalArgumentException e) {
                return ui.printErr("The description or date of an event cannot be empty.");
            } catch (DateTimeParseException e) {
                return ui.printErr("The input time was not recognised.");
            }
        }
    }

    private Label find(String input) {
        String search;
        try {
            search = input.substring(5); // Beginning of search query is index 5.
        } catch (StringIndexOutOfBoundsException e) {
            return ui.printErr("The search query cannot be empty.");
        }
        if (search.length() == 0) {
            return ui.printErr("The search query cannot be empty.");
        } else {
            int noOfHits = 0;
            ArrayDeque<Task> found = new ArrayDeque<>();
            for (int i = 0; i < list.size(); i++) {
                Task currTask = list.get(i);
                if (currTask.toString().contains(search)) {
                    found.add(currTask);
                    noOfHits++;
                }
            }
            if (noOfHits == 0) {
                return ui.print("Looks like no tasks were found :-(");
            } else {
                String[] output = new String[noOfHits + 1];
                output[0] = "Here are the matching tasks in your list:";
                for (int i = 1; i <= noOfHits; i++) {
                    output[i] = i + "." + found.poll().toString();
                }
                return ui.print(output);
            }
        }
    }

    private Label snooze(String input) {
        String[] terms = input.split(" ");
        if (terms.length == 3) {
            int index = Integer.parseInt(terms[1]) - 1;
            long duration = Long.parseLong(terms[2]);
            try {
                return list.snooze(index, duration);
            } catch (IndexOutOfBoundsException e) {
                return ui.printErr("No task has been stored at that index yet.");
            } catch (NumberFormatException e) {
                return ui.printErr("Input index was not recognised.");
            }
        } else {
            String[] output = new String[2];
            output[0] = "Please input as snooze [index] [time to snooze].";
            output[1] = "For deadlines, time to snooze is in days while for events it is in minutes.";
            return ui.print(output);
        }
    }
}
