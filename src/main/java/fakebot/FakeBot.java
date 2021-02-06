package fakebot;

import java.time.LocalDate;
import java.time.LocalTime;

import fakebot.command.Command;
import fakebot.command.CommandException;
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
     * Saves history to storage.
     */
    public void saveHistory() {
        storage.writeTasksToFile(taskList);
    }

    /**
     * Returns hello message used at the start of the project.
     *
     * @return hello message.
     */
    public String getHelloMessage() {
        return Parser.getBotMPrintMessage("Hello from\n" + LOGO + "What can I do for you?");
    }

    /**
     * Returns message to show that the task is done.
     *
     * @param task to print.
     * @return a string containing resulted from done.
     */
    public String getDoneMessage(Task task) {
        return Parser.getBotMPrintMessage("Nice! I've marked this task as done:\n " + task.toString());
    }


    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task deleted Task.
     * @return a string containing resulted from deleting.
     */
    public String getDeleteMessage(Task task) {
        return Parser.getBotMPrintMessage("Noted. I've removed this task:\n " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Returns message to show that the task is deleted and print the remaining number of task left.
     *
     * @param task added Task.
     * @return a string containing resulted from adding.
     */
    public String getAddedTaskMessage(Task task) {
        return Parser.getBotMPrintMessage("Got it. I've added this task: \n  " + task.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Process command given by user.
     * Returns process command result message
     *
     * @param command to be processed.
     * @return a string containing resulted from the process.
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
        case HELP:
            return command.getDescription();
        default:
            assert false : "All command type should be handle in switch";
            break;
        }

        return "";
    }

    /**
     * Process done command.
     *
     * @param command to be process.
     * @return a string containing resulted from the process.
     */
    private String processDoneCommand(Command command) throws CommandException {
        int doneIndex = Integer.parseInt(command.getDescription()) - 1;
        boolean isIndexOutOfRange = doneIndex >= taskList.getSize() || doneIndex < 0;
        if (isIndexOutOfRange) {
            throw new CommandException("OOPS!!! Task number out of range.");
        }
        taskList.getTask(doneIndex).markComplete();
        saveHistory();
        return getDoneMessage(taskList.getTask(doneIndex));
    }

    /**
     * Process todo command.
     *
     * @param command to be process.
     * @return a string containing resulted from the process.
     */
    private String processTodoCommand(Command command) {
        Todo todoTask = new Todo(command.getDescription());
        taskList.addTask(todoTask);
        saveHistory();
        return getAddedTaskMessage(todoTask);
    }

    /**
     * Process deadline command.
     *
     * @param command to be process.
     * @return a string containing resulted from the process.
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
     * Process event command.
     *
     * @param command to be process.
     * @return a string containing resulted from the process.
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
     * Process delete command.
     *
     * @param command to be process.
     * @return a string containing resulted from the process.
     */
    private String processDeleteCommand(Command command) throws CommandException {
        int deleteIndex = Integer.parseInt(command.getDescription()) - 1;
        boolean isIndexOutOfRange = deleteIndex >= taskList.getSize() || deleteIndex < 0;
        if (isIndexOutOfRange) {
            throw new CommandException("OOPS!!! Task number out of range.");
        }
        Task deletedTask = taskList.getTask(deleteIndex);
        taskList.removeTask(deleteIndex);
        saveHistory();
        return getDeleteMessage(deletedTask);
    }
}
