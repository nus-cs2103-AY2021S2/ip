import java.util.Scanner;

/**
 * Driver class of Duke that handles the command key in by user and respond.
 */
public class DukeDriver {

    /**
     * Take in user input and execute the Duke program.
     *
     */
    public static void executeDuke() {
        Scanner sc = new Scanner(System.in);
        System.out.println(Format.LOGO);
        System.out.println(Format.GREETING);

        while(sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Format.FAREWELL);
    }

    /**
     * handle done command by marking the task as done.
     *
     * @param input user input.
     * @param command command given by user.
     */
    private static final void handleDone(String input, String command) {
        String task = extractTask(input, command);
        if (task.length() > 0) {
            try {
                int num = Integer.parseInt(task);
                Task.done(num);
            } catch (NumberFormatException e) {
                DukeException.NumberFormatException();
            }
        } else {
            DukeException.argumentErrorException();
        }
    }

    /**
     * handle todo command and create a todo task if task is not empty.
     *
     * @param input user input.
     * @param command user command.
     */
    private static final void handleToDo(String input, String command) {
        String task = extractTask(input, command);
        if (!task.equals("")) {
            Todo todo = new Todo(task);
            System.out.println(Format.biggerBox(todo));
        } else {
            DukeException.emptyTaskException();
        }

    }

    /**
     * handle deadline command by creating deadline task if task and date are not empty.
     *
     * @param input user input.
     * @param command user command.
     */
    private static final void handleDeadline(String input, String command) {
        String task = extractTask(input, command);
        if (task.equals("")) {
            DukeException.emptyTaskException();
        } else {
            String date = extractDate(input, command);
            if (date.equals("")) {
                DukeException.missingDateErrorException();
            } else {
                Deadlines deadlines = new Deadlines(task, date);
                System.out.println(Format.biggerBox(deadlines));
            }
        }
    }


    /**
     * handle event command by creating event if task and date are not empty.
     *
     * @param input user input.
     * @param command user command.
     */
    private static final void handleEvent(String input, String command) {
        String task = extractTask(input, command);
        if (task.equals("")) {
            DukeException.emptyTaskException();
        } else {
            String date = extractDate(input, command);
            if (date.equals("")) {
                DukeException.missingDateErrorException();
            } else {
                Event event = new Event(task, date);
                System.out.println(Format.biggerBox(event));
            }
        }
    }

    /**
     * handle bye command by returning true or false.
     *
     * @param input user iuput.
     * @return a boolean of whether to exit the program.
     */
    private static final boolean handleBye(String input) {
        if (input.trim().length() > 3) {
            DukeException.commandErrorException();
            return false;
        } else {
            return true;
        }
    }

    private static final void handleDelete(String input, String command) {
        String task = extractTask(input, command);
        if (task.length() > 0) {
            try {
                int num = Integer.parseInt(task);
                Task.delete(num);
            } catch (NumberFormatException e) {
                DukeException.NumberFormatException();
            }
        } else {
            DukeException.argumentErrorException();
        }
    }

    /**
     * The main handler of all the command.
     *
     * @param message String representation of the message key in by user.
     * @return a boolean to whether to exit the program.
     */
    private static final boolean inputHandler(String message) {
        String command = extractCommand(message);
        boolean end = false;
        switch (command) {
        case "bye":
            end = handleBye(message);
            break;
        case "list":
            Format.LISTING();
            break;
        case "done":
            handleDone(message, command);
            break;
        case "todo":
            handleToDo(message, command);
            break;
        case "deadline":
            handleDeadline(message, command);
            break;
        case "event":
            handleEvent(message, command);
	        break;
        case "delete":
            handleDelete(message, command);
            break;
        default:
            DukeException.commandErrorException();
            break;
        }
        return end;
    }


    /**
     * extract the command key in by user.
     *
     * @param input the input key in by user.
     * @return String representation of the command word of the user input.
     */
    private static final String extractCommand(String input) {
        return input.trim().toLowerCase().split(" ")[0];
    }

    /**
     * extracting task name from user input.
     * @param input user input.
     * @param command user command.
     * @return the task name if there is one and return empty string if task name empty.
     */
    private static final String extractTask(String input, String command) {
        String body = input.replaceAll(command, "").trim();
        if (command.equals("todo") || command.equals("delete")) {
            return body;
        } else if (command.equals("done")) {
            return body.replaceAll("[^0-9]", "");
        } else {
            try {
                return body.split("/")[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                return "";
            }
        }
    }

    /**
     * extract the date of the task to be done.
     * @param input user input.
     * @param command user command.
     * @return the task date in String and return empty if there is no date.
     */
    private static final String extractDate(String input, String command) {
        String body = input.replaceAll(command, "").trim();
        String[] parts = body.split("/", 2);
        if (parts.length == 2) {
            return parts[1];
        } else {
            return "";
        }
    }

}
