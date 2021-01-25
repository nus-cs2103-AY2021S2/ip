import java.time.LocalDate;


/**
 * Bot that handles user inputs, identifies specific commands and respond accordingly
 * Available commands: list, bye, done, delete, delete, event, deadline, todo
 */
public class Ui {
    private boolean isExit;
    private String output;

    public Ui() {
        isExit = false;
        welcomeProcess();
    }

    public void handleCommand(String text, TaskList tasks, Storage storage) throws DukeException {
        output = "";
        String command = Parser.command(text);
        String description = Parser.description(text);

        // Throws exception for ToDo, Event and Deadline tasks
        if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
            if (description.equals(command)) {
                //Empty description
                throw new DukeException(command, DukeExceptionType.EMPTY_DESCRIPTION);
            }
        }

        // Throws exception for done and delete commands
        if (command.equals("done") || command.equals("delete")) {
            if (description.equals(command)) {
                throw new DukeException(command, DukeExceptionType.EMPTY_SELECTION);
            } else if (!Utility.isNumeric(description)) {
                // Selection not numeric
                throw new DukeException(command, DukeExceptionType.INVALID_INTEGER);
            } else if (Integer.parseInt(description) > tasks.size() || Integer.parseInt(description) <= 0) {
                // Selection out of taskList range
                throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
            }
        }

        // Sets up process to be done for specific commands
        switch (command) {
        case "list":
            listProcess(tasks);
            break;
        case "bye":
            byeProcess();
            break;
        case "done":
            doneProcess(description, tasks);
            storage.save(tasks);
            break;
        case "delete":
            deleteProcess(description, tasks);
            storage.save(tasks);
            break;
        case "event":
            eventProcess(description, Parser.date(text), tasks);
            storage.save(tasks);
            break;
        case "deadline":
            deadlineProcess(description, Parser.date(text), tasks);
            storage.save(tasks);
            break;
        case "todo":
            todoProcess(description, tasks);
            storage.save(tasks);
            break;
        default:
            throw new DukeException(command, DukeExceptionType.UNKNOWN_INPUT);
        }
        response(output);
    }

    private void welcomeProcess() {
        output = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        response(output);
    }

    /**
     * Sets up program to list out tasks
     */
    private void listProcess(TaskList tasks) {
        output = getTaskListContents(tasks);
    }

    /**
     * Sets up program for System exit
     */
    private void byeProcess() {
        isExit = true;
        output = "Bye. Hope to see you again soon!";
    }

    private void doneProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        task.markAsDone();
        output = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    private void deleteProcess(String selection, TaskList tasks) {
        int taskNum = Integer.parseInt(selection);
        Task task = tasks.get(taskNum);
        tasks.remove(task);
        output = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void eventProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void deadlineProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }
    
    private void todoProcess(String taskName, TaskList tasks) {
        Task task = new ToDo(taskName);
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getTaskListContents(TaskList tasks) {
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    public void response(String selectedOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + selectedOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
