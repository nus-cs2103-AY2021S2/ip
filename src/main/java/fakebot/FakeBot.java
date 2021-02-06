package fakebot;

import java.time.LocalDate;
import java.time.LocalTime;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.command.CommandType;
import fakebot.task.Deadline;
import fakebot.task.Event;
import fakebot.task.Task;
import fakebot.task.TaskList;
import fakebot.task.Todo;


/**
 * Fakebot class, main class for Fakebot.
 */
public class FakeBot {

    private static final String LOGO = " ______      _  ________   ____   ____ _______ \n"
            + "|  ____/ \\  | |/ /  ____| |  _ \\ / __ \\__   __|\n"
            + "| |__ /  \\  | ' /| |__    | |_) | |  | | | |   \n"
            + "|  __/ /\\ \\ |  < |  __|   |  _ <| |  | | | |\n"
            + "| | / ____ \\| . \\| |____  | |_) | |__| | | |\n"
            + "|_|/_/    \\_\\_|\\_\\______| |____/ \\____/  |_|\n";

    private static final String SAVE_FILE_PATH = "/data/";
    private static final String SAVE_FILE_NAME = "savedHistory.txt";

    private TaskList taskList;
    private Storage storage;

    /**
     * Class constructor specifying save file name and save file path.
     */
    public FakeBot() {
        this(SAVE_FILE_PATH, SAVE_FILE_NAME);
    }

    /**
     * Class constructor specifying save file name and save file path.
     */
    public FakeBot(String saveFileName, String saveFilePath) {
        storage = new Storage(SAVE_FILE_NAME, SAVE_FILE_PATH);
        taskList = new TaskList(storage.tryReadTaskFile());
    }

    /**
     * Saves History to Storage.
     */
    public void saveHistory() {
        storage.writeTasksToFile(taskList);
    }

    /**
     * Returns Hello Message used at the start of the project.
     *
     * @return Returns Hello Message
     */
    public String getHelloMessage() {
        return Parser.getBotMPrintMessage("Hello from\n" + LOGO + "What can I do for you?");
    }

    /**
     * Returns message to show that the task is done.
     *
     * @param task Task to print.
     * @return Returns Done Message
     */
    public String getDoneMessage(Task task) {
        return Parser.getBotMPrintMessage("Nice! I've marked this task as done:\n " + task.toString());
    }


    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task Deleted Task.
     * @return Returns Delete Message
     */
    public String getDeleteMessage(Task task) {
        return Parser.getBotMPrintMessage("Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task Added Task.
     * @return Returns Added Task Message
     */
    public String getAddedTaskMessage(Task task) {
        return Parser.getBotMPrintMessage("Got it. I've added this task: \n  " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Process Command given by user.
     * Returns process command result message
     *
     * @param command Total number of Task Left.
     * @return Returns process command result message
     */
    public String processCommand(Command command) throws CommandException {
        switch (command.getCommand()) {
        case BYE:
            return "";
        case LIST:
            return Parser.getTaskListPrintMessage(taskList);
        case DONE:
            return processDoneCommand(command);
        case TODO:
            return processTodoCommand(command);
        case DEADLINE:
            return processDeadlineCommand(command);
        case EVENT:
            return processEventCommand(command);
        case DELETE:
            return processDeleteCommand(command);
        case FIND:
            return Parser.getTaskListPrintMessage(new TaskList(taskList.findTasks(command.getDescription())));
        default: break;
        }

        return "";
    }

    /**
     * Process Done Command.
     *
     * @param command Command to Process.
     * @return Returns process Done command result message
     */
    private String processDoneCommand(Command command) throws CommandException {
        int doneIndex = Integer.parseInt(command.getDescription()) - 1;
        boolean isIndexOutOfRange = doneIndex >= taskList.getSize() || doneIndex < 0;
        if (isIndexOutOfRange) {
            throw new CommandException("☹ OOPS!!! Task number out of range.");
        }
        taskList.getTask(doneIndex).markComplete();
        saveHistory();
        return getDoneMessage(taskList.getTask(doneIndex));
    }

    /**
     * Process Todos Command.
     *
     * @param command Command to Process.
     * @return Returns process ToDos command result message
     */
    private String processTodoCommand(Command command) {
        Todo todoTask = new Todo(command.getDescription());
        taskList.addTask(todoTask);
        saveHistory();
        return getAddedTaskMessage(todoTask);
    }

    /**
     * Process Deadline Command.
     *
     * @param command Command to Process.
     * @return Returns process Deadline command result message
     */
    private String processDeadlineCommand(Command command) {
        String[] deadlineDetails = command.getDescriptions();
        String[] dates = deadlineDetails[1].split(" ");
        LocalDate date = LocalDate.parse(dates[0]);
        LocalTime time = LocalTime.parse(dates[1]);
        Deadline deadlineTask = new Deadline(deadlineDetails[0], date, time);
        taskList.addTask(deadlineTask);
        saveHistory();
        return getAddedTaskMessage(deadlineTask);
    }

    /**
     * Process Event Command.
     *
     * @param command Command to Process.
     * @return Returns process Event command result message
     */
    private String processEventCommand(Command command) {
        String[] eventDetails = command.getDescriptions();
        String[] eventDates = eventDetails[1].split(" ");
        LocalDate startDate = LocalDate.parse(eventDates[0]);
        LocalTime startTime = LocalTime.parse(eventDates[1]);
        LocalDate endDate = LocalDate.parse(eventDates[2]);
        LocalTime endTime = LocalTime.parse(eventDates[3]);
        Event eventTask = new Event(eventDetails[0], startDate, startTime, endDate, endTime);
        taskList.addTask(eventTask);
        saveHistory();
        return getAddedTaskMessage(eventTask);
    }

    /**
     * Process Delete Command.
     *
     * @param command Command to Process.
     */
    private String processDeleteCommand(Command command) throws CommandException{
        int deleteIndex = Integer.parseInt(command.getDescription()) - 1;
        boolean isIndexOutOfRange = deleteIndex >= taskList.getSize() || deleteIndex < 0;
        if (isIndexOutOfRange) {
            throw new CommandException("☹ OOPS!!! Task number out of range.");
        }
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        saveHistory();
        return getDeleteMessage(deletedTask);
    }

}
