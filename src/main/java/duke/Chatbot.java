package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A duke.Duke chatbot that provides todo-list function for users.
 */
public class Chatbot {
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs the chat bot.
     *
     * @param taskList the task list given to chat bot
     */
    public Chatbot(TaskList taskList) {
        this.taskList = taskList;
        ui = new Ui();
        parser = new Parser();
    }


    /**
     * Executes the chat bot. Keep looping and asking for user input.
     * The function will be terminated once hit "bye".
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] taskTimeSplit;
        Task newTask;

        while (!input.toLowerCase().equals("bye")) {
            try {
                String[] taskTypeSplit = input.split(" ");
                Command command = parser.parse(taskTypeSplit);
                int tempOrder;
                switch (command) {
                case LIST:
                    taskList.printTaskList();
                    break;
                case DONE:
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of done cannot be empty.");
                    }
                    tempOrder = Integer.parseInt(taskTypeSplit[1]);
                    taskList.markDone(tempOrder - 1);
                    break;
                case DELETE:
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of delete cannot be empty.");
                    }
                    tempOrder = Integer.parseInt(taskTypeSplit[1]);
                    taskList.delete(tempOrder - 1);
                    break;
                case FIND:
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of find cannot be empty.");
                    }
                    taskList.findTask(taskTypeSplit[1]);
                    break;
                case TODO:
                    if (taskTypeSplit.length <= 1 || taskTypeSplit[1].isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    newTask = new ToDo(input.substring(5), TaskType.TODO);
                    taskList.addTask(newTask);
                    break;
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
                    taskList.addTask(newTask);
                    break;
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
                    taskList.addTask(newTask);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                ui.printFormatMessage(ex.toString());
            } catch (DateTimeParseException ex) {
                ui.printFormatMessage("\t  OOPS!!! " + "The format of date is wrong! (yyyy-mm-dd)");
            }
            input = sc.nextLine();
        }


    }

    /**
     * Returns the task list in the chat bot.
     *
     * @return the task list to be returned
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
