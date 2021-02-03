import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskList {

    /**
     * Marks the task at a particular index as done.
     *
     * @param index Index of the task to be removed.
     * @param tasks List of all tasks.
     */
    public static String markDone(int index, List<Task> tasks) {
        String reply = "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        tasks.get(index).markAsDone();
        reply += "\nGood job, I have marked this task as done!";
        reply += "\n" + tasks.get(index).toString();
        reply += "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            reply += "\nSorry, unable to save data to the hard disk.";
        }
        return reply;
    }

    /**
     * Adds a todo task to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task.
     * @param tasks List of all tasks.
     * @throws InvalidTodoException If Todo does not
     *                              have a description.
     */
    public static String addTodo(String command, List<Task> tasks) throws InvalidTodoException {
        String reply = "";
        if (command.length() < 6) {
            throw new InvalidTodoException();
        }
        tasks.add(new Todo(command.substring(5)));
        int count = tasks.size();
        reply += "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\nGot it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            reply += "\n" + "Sorry, unable to save data to the hard disk.";
        }
        return reply;
    }

    /**
     * Adds a deadline to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task and the deadline
     *                to submit it by.
     * @param tasks List of all tasks.
     * @throws InvalidDateTimeFormatException If the format of the date and time entered is incorrect.
     */

    public static String addDeadline(String command, List<Task> tasks) throws InvalidDateTimeFormatException {
        String reply = "";
        try {
            tasks.add(new Deadline(command.substring(9).split("/")[0],
                    LocalDateTime.parse(command.substring(9)
                            .split("/")[1].substring(3), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        int count = tasks.size();
        reply += "\n" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\n" + "Got it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            reply += "\n" + "Sorry, unable to save data to the hard disk.";
        }
        return reply;
    }

    /**
     * Adds a deadline to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the event and the date and time.
     * @param tasks List of all tasks.
     */

    public static String addEvent(String command, List<Task> tasks) throws InvalidDateTimeFormatException {
        String reply = "";
        try {
            tasks.add(new Event(command.substring(6).split("/")[0],
                    LocalDateTime.parse(command.substring(6).split("/")[1].substring(3),
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        int count = tasks.size();
        reply += "\n" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\n" + "Got it. I've added this task: ";
        reply += "\n" + tasks.get(count - 1).toString();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            System.out.println("Sorry, unable to save data to the hard disk.");
        }
        return reply;
    }

    /**
     * Deletes the specified task.
     *
     * @param command Command input by the user
     *                which contains the index of
     *                the task to be deleted.
     * @param tasks List of all tasks.
     */
    public static String deleteTask(String command, List<Task> tasks) {
        String reply = "";
        int deleteIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        reply += "\n" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\n" + "I've removed the task:";
        reply += "\n" + tasks.get(deleteIndex);
        tasks.remove(deleteIndex);
        int count = tasks.size();
        reply += "\n" + "Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.";
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            Ui.printUnableToSaveDataMessage();
        }
        return reply;
    }

    /**
     * Lists all the tasks that have been input by the user.
     *
     * @param tasks List of all tasks.
     */
    public static String printList(List<Task> tasks) {
        String reply = "";
        int listCount = 1;
        reply += "\n" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\n" + "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + "  " + listCount + "." + task.toString();
            listCount++;
        }
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        System.out.println(reply);
        return reply;
    }

    /**
     * Searches for task specified by user.
     *
     * @param command Command input by user.
     * @param tasks List of tasks.
     */
    public static String findTask(String command, List<Task> tasks) {
        String reply = "";
        int listCount = 1;
        reply += "\n" + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        reply += "\n" + "Here are the matching tasks in your list:";
        for (Task task : tasks) {
            if (task.toString().contains(command.split(" ")[1])) {
                reply += "\n" + "  " + listCount + "." + task.toString();
                listCount++;
            }
        }
        reply += "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        return reply;
    }

}
