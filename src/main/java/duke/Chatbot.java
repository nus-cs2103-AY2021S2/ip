package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructs the chat bot.
     */
    public Chatbot() {
        storage = new Storage("./data.txt");
        this.taskList = storage.readFile();
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
                return taskList.printTaskList();
            case DONE:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of done cannot be empty.");
                }
                tempOrder = Integer.parseInt(taskTypeSplit[1]);
                storage.updateFile(taskList);
                return taskList.markAsDone(tempOrder - 1);
            case DELETE:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of delete cannot be empty.");
                }
                tempOrder = Integer.parseInt(taskTypeSplit[1]);
                storage.updateFile(taskList);
                return taskList.deleteTask(tempOrder - 1);
            case FIND:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of find cannot be empty.");
                }
                storage.updateFile(taskList);
                return taskList.findTask(taskTypeSplit[1]);
            case TODO:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                newTask = new ToDo(input.substring(5), TaskType.TODO);
                storage.updateFile(taskList);
                return taskList.addTask(newTask);
            case DEADLINE:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                taskTimeSplit = input.split(" /by ");
                if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
                    throw new DukeException("The time of a deadline cannot be empty.");
                }
                newTask = new Deadline(taskTimeSplit[0].substring(9),
                        TaskType.DEADLINE, LocalDate.parse(taskTimeSplit[1]));
                storage.updateFile(taskList);
                return taskList.addTask(newTask);
            case EVENT:
                if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                taskTimeSplit = input.split(" /at ");
                if (taskTimeSplit.length <= 1 || taskTimeSplit[1].isBlank()) {
                    throw new DukeException("The time of an event cannot be empty.");
                }
                newTask = new Event(taskTimeSplit[0].substring(6),
                        TaskType.EVENT, LocalDate.parse(taskTimeSplit[1]));
                storage.updateFile(taskList);
                return taskList.addTask(newTask);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            return Ui.printFormatMessage(ex.toString());
        } catch (DateTimeParseException ex) {
            return Ui.printFormatMessage("OOPS!!! " + "The format of date is wrong! (yyyy-mm-dd)");
        }

    }

}
