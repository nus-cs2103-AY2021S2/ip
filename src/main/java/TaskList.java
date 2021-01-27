import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TaskList {

    /**
     * Marks the task at a particular index as done.
     *
     * @param index Index of the task to be removed.
     */
    public static void markDone(int index, List<Task> tasks) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        tasks.get(index).markAsDone();
        System.out.println("Good job, I have marked this task as done!");
        System.out.println(tasks.get(index).toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            System.out.println("Sorry, unable to save data to the hard disk.");
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task.
     * @throws InvalidTodoException If Todo does not
     *                              have a description.
     */
    public static void addTodo(String command, List<Task> tasks) throws InvalidTodoException {
        if (command.length() < 6) {
            throw new InvalidTodoException();
        }
        tasks.add(new Todo(command.substring(5)));
        int count = tasks.size();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            System.out.println("Sorry, unable to save data to the hard disk.");
        }
    }

    /**
     * Adds a deadline to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the task and the deadline
     *                to submit it by.
     */

    public static void addDeadline(String command, List<Task> tasks) throws InvalidDateTimeFormatException {
        try {
            tasks.add(new Deadline(command.substring(9).split("/")[0],
                    LocalDateTime.parse(command.substring(9)
                            .split("/")[1].substring(3), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        int count = tasks.size();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            System.out.println("Sorry, unable to save data to the hard disk.");
        }
    }

    /**
     * Adds a deadline to the list.
     *
     * @param command Command input by the user
     *                which contains the name of
     *                the event and the date and time.
     */

    public static void addEvent(String command, List<Task> tasks) throws InvalidDateTimeFormatException {
        try {
            tasks.add(new Event(command.substring(6).split("/")[0],
                    LocalDateTime.parse(command.substring(6).split("/")[1].substring(3),
                            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        int count = tasks.size();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(count - 1).toString());
        System.out.println("Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            System.out.println("Sorry, unable to save data to the hard disk.");
        }
    }

    /**
     * Deletes the specified task.
     *
     * @param command Command input by the user
     *                which contains the index of
     *                the task to be deleted.
     */
    public static void deleteTask(String command, List<Task> tasks) {
        int deleteIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("I've removed the task:");
        System.out.println(tasks.get(deleteIndex));
        tasks.remove(deleteIndex);
        int count = tasks.size();
        System.out.println("Now you have " + count
                + (count == 1 ? " task" : " tasks")
                + " in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        try {
            Storage.saveDataToFile(tasks);
        } catch (UnableToSaveDataException e) {
            Ui.printUnableToSaveDataMessage();
        }
    }

    /**
     * Lists all the tasks that have been input by the user.
     */
    public static void printList(List<Task> tasks) {
        int listCount = 1;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("  " + listCount + "." + task.toString());
            listCount++;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

}
