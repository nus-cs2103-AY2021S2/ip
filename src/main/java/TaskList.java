import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles all operations related to tasks such as adding, deleting and searching.
 */
public class TaskList {

    private static String updateSavedList(List<Task> tasks) {
        String reply = "";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            reply += "\nSorry, unable to save data to the hard disk.";
        }
        return reply;
    }

    private static String getMarkedAsDoneReply(int index, List<Task> tasks) {
        String reply = "";
        reply += "\nGood job, I have marked this task as done!";
        reply += "\n" + tasks.get(index).toString();
        return reply;
    }

    /**
     * Marks the task at a particular index as done.
     *
     * @param command Command input by the user which contains the
     *                index of the task to be marked as complete.
     * @param tasks   List of all tasks.
     * @return Acknowledgment that task has been marked as done.
     */
    public static String markDone(String command, List<Task> tasks) {
        int index = Parser.parseDoneIndex(command);
        tasks.get(index).markAsDone();
        String reply = getMarkedAsDoneReply(index, tasks);
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static String getAddTodoReply(List<Task> tasks) {
        String reply = "";
        int count = tasks.size();
        reply += "\nGot it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        return reply;
    }

    /**
     * Adds a todo task to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task.
     * @param tasks   List of all tasks.
     * @return Acknowledgment that todo has been added to the list.
     * @throws InvalidTodoException If Todo does not
     *                              have a description.
     */
    public static String addTodo(String command, List<Task> tasks)
            throws InvalidTodoException {
        String reply = "";
        if (command.length() < 6) {
            throw new InvalidTodoException();
        }
        tasks.add(new Todo(command.substring(5)));
        reply += getAddTodoReply(tasks);
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static String getAddDeadlineReply(List<Task> tasks) {
        String reply = "";
        int count = tasks.size();
        reply += "\n" + "Got it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        return reply;
    }

    private static void addDeadlineToList(String command, List<Task> tasks) {
        tasks.add(new Deadline(command.substring(9).split("/")[0],
                LocalDateTime.parse(command.substring(9)
                                .split("/")[1].substring(3),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
    }

    /**
     * Adds a deadline to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task and the deadline
     *                to submit it by.
     * @param tasks   List of all tasks.
     * @return Acknowledgment that deadline has been added to the list.
     * @throws InvalidDateTimeFormatException If the format of the date and time
     *                                        entered is incorrect.
     */
    public static String addDeadline(String command, List<Task> tasks) throws
            InvalidDateTimeFormatException, InvalidDeadlineException {
        if (command.length() < 14) {
            throw new InvalidDeadlineException();
        }
        String reply = "";
        try {
            addDeadlineToList(command, tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        reply += getAddDeadlineReply(tasks);
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static String getAddEventReply(List<Task> tasks) {
        String reply = "";
        int count = tasks.size();
        reply += "\n" + "Got it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        return reply;
    }

    private static void addEventToList(String command, List<Task> tasks) {
        tasks.add(new Event(command.substring(6).split("/")[0],
                LocalDateTime.parse(command.substring(6).split("/")[1].substring(3),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
    }

    /**
     * Adds an event to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the event and the date and time.
     * @param tasks   List of all tasks.
     * @return Acknowledgment that event has been added to the list.
     * @throws InvalidEventException          If the format of the command input is invalid.
     * @throws InvalidDateTimeFormatException If the format of the date and time
     *                                        input by the user is incorrect.
     */
    public static String addEvent(String command, List<Task> tasks)
            throws InvalidDateTimeFormatException, InvalidEventException {
        if (command.length() < 13) {
            throw new InvalidEventException();
        }
        String reply = "";
        try {
            addEventToList(command, tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        reply += getAddEventReply(tasks);
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static String getDeleteTaskReply(String command, List<Task> tasks) {
        String reply = "";
        int deleteIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        reply += "\n" + "I've removed the task:";
        reply += "\n" + tasks.get(deleteIndex);
        tasks.remove(deleteIndex);
        int count = tasks.size();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        return reply;
    }

    /**
     * Deletes the specified task.
     *
     * @param command Command input by the user
     *                which contains the index of
     *                the task to be deleted.
     * @param tasks   List of all tasks.
     * @return Acknowledgment that task has been deleted.
     */
    public static String deleteTask(String command, List<Task> tasks) {
        String reply = "";
        reply = getDeleteTaskReply(command, tasks);
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    /**
     * Lists all the tasks that have been input by the user.
     *
     * @param tasks List of all tasks.
     * @return List of tasks converted to print format.
     */
    public static String printList(List<Task> tasks) {
        String reply = "";
        int listCount = 1;
        reply += "\n" + "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + "  " + listCount + "." + task.toString();
            listCount++;
        }
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static String getFindTaskReply(String command, List<Task> tasks) {
        int listCount = 1;
        String reply = "";
        reply += "\n" + "Here are the matching tasks in your list:";
        for (Task task : tasks) {
            if (task.toString().contains(command.split(" ")[1])) {
                reply += "\n" + "  " + listCount + "." + task.toString();
                listCount++;
            }
        }
        return reply;
    }

    /**
     * Searches for task specified by user.
     *
     * @param command Command input by user.
     * @param tasks   List of tasks.
     * @return List of tasks which contain the keyword required.
     */
    public static String findTask(String command, List<Task> tasks) {
        String reply = "";
        reply += getFindTaskReply(command, tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

    private static void performUndoOperation(String parsedCommand, String lastCommand,
                                             List<Task> tasks, Task deletedTask) {
        if (parsedCommand.equals("todo") || parsedCommand.equals("deadline")
                || parsedCommand.equals("event")) {
            tasks.remove(tasks.size() - 1);
        } else if (parsedCommand.equals("done")) {
            int index = Parser.parseDoneIndex(lastCommand);
            tasks.get(index).markAsIncomplete();
        } else if (parsedCommand.equals("delete")) {
            tasks.add(deletedTask);
        }
    }

    /**
     * Performs undo operation.
     *
     * @param tasks       List of tasks.
     * @param lastCommand The most recent command executed
     *                    by the user before undo.
     * @param deletedTask The most recent task which was
     *                    deleted.
     * @return Acknowledgement message that the undo
     * operation has been performed.
     */
    public static String undoCommand(List<Task> tasks, String lastCommand,
                                     Task deletedTask) {
        String reply = "";
        String parsedCommand = Parser.parseCommand(lastCommand);
        performUndoOperation(parsedCommand, lastCommand, tasks, deletedTask);
        reply += "Undo operation successful";
        reply += updateSavedList(tasks);
        assert !reply.isEmpty() : "reply cannot be an empty string";
        return reply;
    }

}
