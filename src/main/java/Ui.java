import java.io.IOException;
import java.time.LocalDate;


/**
 * Bot that handles user inputs, identifies specific commands and respond accordingly
 * Available commands: list, bye, done, delete, delete, event, deadline, todo
 */
public class Ui {
    private boolean isExit;
    private String commandOutput;

    /**
     * Constructor for DukeBot
     * Sets up duke bot to welcome user
     */
    public Ui() {
        isExit = false;
        welcomeProcess();
    }

    public void handleCommand(String text) throws DukeException, IOException {
        commandOutput = "";
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
            } else if (Integer.parseInt(description) > taskList.size() || Integer.parseInt(description) <= 0) {
                // Selection out of taskList range
                throw new DukeException(command, DukeExceptionType.SELECTION_EXCEED_RANGE);
            }
        }

        // Sets up process to be done for specific commands
        switch (command) {
        case "list":
            listProcess();
            break;
        case "bye":
            byeProcess();
            break;
        case "done":
            doneProcess(description);
            //saveData();
            break;
        case "delete":
            deleteProcess(description);
            //saveData();
            break;
        case "event":
            eventProcess(description, Parser.date(text));
            //saveData();
            break;
        case "deadline":
            deadlineProcess(description, Parser.date(text));
            //saveData();
            break;
        case "todo":
            todoProcess(description);
            //saveData();
            break;
        default:
            throw new DukeException(command, DukeExceptionType.UNKNOWN_INPUT);
        }

        respondToCommand(commandOutput);
    }

    private void welcomeProcess() {
        commandOutput = "Hello! I'm Duke\n"
                + "\tWhat can I do for you?";
        respondToCommand(commandOutput);
    }

    /**
     * Sets up program to list out tasks
     */
    private void listProcess() {
        commandOutput = getTaskListContents();
    }

    /**
     * Sets up program for System exit
     */
    private void byeProcess() {
        isExit = true;
        commandOutput = "Bye. Hope to see you again soon!";
    }

    private void doneProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        task.markAsDone();
        commandOutput = "Nice! I've marked this task as done:\n\t  "
                + task.toString();
    }

    private void deleteProcess(String selection) {
        int taskNum = Integer.parseInt(selection);
        Task task = taskList.get(taskNum);
        taskList.remove(task);
        commandOutput = "Noted. I've removed this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void eventProcess(String description, String date) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(description, LocalDate.parse(date));
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private void deadlineProcess(String description, String date) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(description, LocalDate.parse(date));
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }
    
    private void todoProcess(String taskName) {
        Task task = new ToDo(taskName);
        taskList.add(task);
        commandOutput = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks();
    }

    private String getTaskListContents() {
        String contents = "Here are the tasks in your list:";

        for (int i = 1; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            contents += String.format("\n\t%d.%s", i, task.toString());
        }

        return contents;
    }

    private String getRemainingTasks() {
        return "\n\tNow you have " + (taskList.size() - 1) + " tasks in the list.";
    }

    public void respondToCommand(String selectedOutput) {
        String responseMsg = "\t____________________________________________________________\n"
                + "\t" + selectedOutput + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(responseMsg);

        if (isExit) {
            System.exit(0);
        }
    }

}
