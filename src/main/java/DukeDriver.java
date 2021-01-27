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
        System.out.println(Ui.LOGO);
        System.out.println(Ui.GREETING);

        System.out.println(Ui.LOADFILE);

        Ui.SLEEP();

        TaskStorage.loadFiles();

        while(sc.hasNext()) {
            String message = sc.nextLine();
            boolean bye = inputHandler(message);
            if (bye) {
                break;
            }
        }
        System.out.println(Ui.FAREWELL);
    }

    /**
     * handle done command by marking the task as done.
     *
     * @param task name of the user task.
     */
    private static final void handleDone(String task) {
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
     * @param task name of the user task.
     */
    private static final void handleToDo(String task) {
        if (!task.equals("")) {
            Todo todo = new Todo(task);
            System.out.println(Ui.biggerBox(todo));
        } else {
            DukeException.emptyTaskException();
        }

    }

    /**
     * handle deadline command by creating deadline task if task and date are not empty.
     *
     * @param task name of the user task.
     * @param date date of the task to be done.
     */
    private static final void handleDeadline(String task, String date) {
        if (task.equals("")) {
            DukeException.emptyTaskException();
        } else {
            if (date.equals("")) {
                DukeException.missingDateErrorException();
            } else {
                Deadlines deadlines = new Deadlines(task, date);
                System.out.println(Ui.biggerBox(deadlines));
            }
        }
    }


    /**
     * handle event command by creating event if task and date are not empty.
     *
     * @param task name of the user task.
     * @param date date of the task to be done.
     */
    private static final void handleEvent(String task, String date) {
        if (task.equals("")) {
            DukeException.emptyTaskException();
        } else {
            if (date.equals("")) {
                DukeException.missingDateErrorException();
            } else {
                Event event = new Event(task, date);
                System.out.println(Ui.biggerBox(event));
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

    /**
     * handle delete command key in by user by removing the task from the list if there is any.
     *
     * @param task name of the user task.
     */
    private static final void handleDelete(String task) {
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
        InputProcessor ip = new InputProcessor(message);
        String command = ip.getCommand();
        String task = ip.getTaskName();
        String date = ip.getDate();
        boolean end = false;
        switch (command) {
        case "bye":
            end = handleBye(message);
            TaskStorage.writeToFiles(Task.getTaskList());
            break;
        case "list":
            Ui.LISTING();
            break;
        case "done":
            handleDone(task);
            break;
        case "todo":
            handleToDo(task);
            break;
        case "deadline":
            handleDeadline(task, date);
            break;
        case "event":
            handleEvent(task, date);
	        break;
        case "delete":
            handleDelete(task);
            break;
        default:
            DukeException.commandErrorException();
            break;
        }
        return end;
    }




}
