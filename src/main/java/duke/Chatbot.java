package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;


/**
 * A duke.Duke chatbot that provides todo-list function for users.
 */
public class Chatbot {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructs the chat bot.
     */
    public Chatbot() {
        storage = new Storage("./data.txt");
        this.tasks = storage.readFile();
        parser = new Parser();
    }


    /**
     * Executes the chat bot. Keep looping and asking for user input.
     * The function will be terminated once hit "bye".
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            System.out.println(getDukeReply(input));
            input = sc.nextLine();
        }


    }

    public String getDukeReply(String input) {
        String[] taskTimeSplit;
        Task newTask;
        try {
            String[] taskTypeSplit = input.split(" ");
            Command command = parser.parseStringToCommand(taskTypeSplit);
            int tempOrder;
            switch (command) {
            case LIST:
                return Printer.printTaskList(tasks.getTaskList());
            case DONE:
                return doneCommand(taskTypeSplit);
            case DELETE:
                return deleteCommand(taskTypeSplit);
            case FIND:
                return findCommand(taskTypeSplit);
            case TODO:
                return todoCommand(input, taskTypeSplit);
            case DEADLINE:
                return deadlineCommand(input, taskTypeSplit);
            case EVENT:
                return eventCommand(input, taskTypeSplit);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        } catch (DateTimeParseException ex) {
            return Ui.printFormatMessage("OOPS!!! " + "The format of date is wrong! (yyyy-mm-dd)");
        }

    }

    public String doneCommand(String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of done cannot be empty.");
        }
        int doneOrder = Integer.parseInt(taskTypeSplit[1]) - 1;
        tasks.markAsDone(doneOrder);
        storage.updateFile(tasks);
        return Printer.printDoneReply(tasks.getTask(doneOrder));
    }

    public String deleteCommand(String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of delete cannot be empty.");
        }
        int deleteOrder = Integer.parseInt(taskTypeSplit[1]) - 1;
        Task removedTask = tasks.deleteTask(deleteOrder);
        storage.updateFile(tasks);
        return Printer.printDeleteReply(removedTask);
    }

    public String findCommand(String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of find cannot be empty.");
        }
        List<Task> targetTasks = tasks.findTask(taskTypeSplit[1]);
        storage.updateFile(tasks);
        return Printer.printFindReply(targetTasks);
    }

    public String todoCommand(String input, String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(input.substring(5), TaskType.TODO);
        tasks.addTask(newTask);
        storage.updateFile(tasks);
        return Printer.printAddReply(newTask, tasks.size());
    }

    public String deadlineCommand(String input, String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] taskTimeSplit = input.split(" /by ");
        if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
            throw new DukeException("The time of a deadline cannot be empty.");
        }
        Task newTask = new Deadline(taskTimeSplit[0].substring(9),
                TaskType.DEADLINE, LocalDate.parse(taskTimeSplit[1]));
        tasks.addTask(newTask);
        storage.updateFile(tasks);
        return Printer.printAddReply(newTask, tasks.size());
    }

    public String eventCommand(String input, String... taskTypeSplit) throws DukeException {
        if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] taskTimeSplit = input.split(" /at ");
        if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
            throw new DukeException("The time of an event cannot be empty.");
        }
        Task newTask = new Event(taskTimeSplit[0].substring(6),
                TaskType.EVENT, LocalDate.parse(taskTimeSplit[1]));
        tasks.addTask(newTask);
        storage.updateFile(tasks);
        return Printer.printAddReply(newTask, tasks.size());
    }

}
