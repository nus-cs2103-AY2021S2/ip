package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * main class containing the Duke Chatbot main logic.
 */
public class Duke {
    private static Storage storage;
    private Ui ui;
    TaskList tasks;

    Duke() {
        this.storage = initializeStorage();
        this.ui = new Ui();
        try {
            tasks = new TaskList(storage.loadStorage());
        } catch ( DukeException err) {
            System.out.println(err.getMessage());
            tasks = new TaskList();
        }
    }

    private Storage initializeStorage() {
        File directory = new File("data"); // Check if directory exists.
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Storage("data/duke.txt");
    }


    /**
     * adds a Task to the list, and prints a message on to the console
     * @param incomingTask
     */

    public void addTask(Task incomingTask) throws IOException {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + incomingTask);
        tasks.add(incomingTask);
        storage.saveTasks(tasks);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     *
     * parse a string input MARK_DONE command to obtain the index of the task to mark as done.
     * @param input string.
     * @return  the index of the task to mark done.
     * @throws DukeException for the case when done is empty or when the integer cannot be parsed.
     */

    private static int parseMarkDone(String input) throws DukeException {
        //for the case when "done" in the input string is followed by variable number of space.
        if (input.toLowerCase().matches("^done\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        String regex = "^done\\s+([0-9]+)$"; // "done" followed by at least one space and at least one number.
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            // Unable to parse the string following "done "
            throw new DukeException("The input for done must be integer.");
        }
        int indexToMarkDone = Integer.parseInt(m.group(1));
        return indexToMarkDone;
    }

    /**
     * Marks a task at a specified index in the list as done. Prints out a message to tell the user that the
     * task is marked done.
     * @param indexToMarkDone the index of the task to mark as done.
     */

    public void markTaskDone(int indexToMarkDone) {
        Task task = tasks.markTaskDone(indexToMarkDone);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task);
    }

    /**
     * Reads in a REMOVE_TASK command. Parses the string to obtain the index of the task to be deleted.
     * Prints out a message to tell the user the task is deleted
     * @param input string
     * @return index of the task to be deleted.
     * @throws DukeException when the delete is of the incorrect format or is empty.
     */

    private static int parseDelete(String input) throws DukeException{
        //for the case when "delete" is followed by variable number of space.
        if (input.toLowerCase().matches("^delete\\s*$")) {
            throw new DukeException("The input cannot be empty.");
        }
        String regex = "^delete\\s+([0-9]+)$"; //delete followed by at least one space and one number.
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            throw new DukeException("The input for delete must be integer.");
        }
        int indexToDelete = Integer.parseInt(m.group(1));
        return indexToDelete;
    }

    /**
     * deletes a task in the list at a certain index.
     * @param indexToDelete index of the task to be deleted.
     */

    public void deleteTask(int indexToDelete) throws IOException {
        Task task = tasks.delete(indexToDelete);
        storage.saveTasks(tasks);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * parses a string input of the ADD_DEADLINE command and returns the corresponidng deadline class.
     * @param input string
     * @return Deadline to be added.
     * @throws DukeException when the deadline is empty.
     */

    private static Task parseAddDeadline(String input) throws DukeException {
        if (input.toLowerCase().matches("^deadline\\s*$")) {
            // when the string is just "deadline" followed by variable number of space.
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String regex = "^deadline\\s+(.+)\\s+/by\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            // case when it is of the wrong format. (e.g no \by in input string.)
            throw new DukeException("The deadline is of incorrect format.");
        }
        String description = m.group(1);
        String by = m.group(2);
        return new Deadline(description,by);
    }

    /**
     * parses an input of the type ADD_EVENT, returns the correspoinding event class.
     * @param input string
     * @return Event Task
     * @throws DukeException when the event command is empty or of incorrect format.
     */

    private static Task parseAddEvent(String input) throws DukeException{
        if (input.toLowerCase().matches("^event\\s*$")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String regex = "^event\\s+(.+)\\s+/at\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()) {
            throw new DukeException("The event is of incorrect format.");
        }
        String description = m.group(1);
        String at = m.group(2);
        return new Event(description,at);
    }

    /**
     * parses an input of the Command type ADD_TODO and returns the corresponding ToDo Task.
     * @param input string
     * @return ToDo Task.
     * @throws DukeException command Todo is empty or of the incorrect format.
     */

    private static Task parseToDo (String input) throws DukeException{
        if (input.toLowerCase().matches("^todo\\s*$")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String regex = "^todo\\s+(.+)$";
        Pattern patternToMatch = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = patternToMatch.matcher(input);
        if (!m.matches()){
            throw new DukeException("The todo is of incorrect format.");
        }
        String description = m.group(1);
        return new ToDo(description);
    }

    public void run(){
        ui.displayWelcomeMessage();

        String input;

        while (ui.hasUserInput()) {
            input = ui.getUserCommand();
            try {
                if (input.equals("bye")) break;
                if (input.equals("list")) {
                    ui.showUserAllTasks(tasks);
                } else if (CommandType.MARK_AS_DONE.isCommandTypeFor(input)) {
                    int indexToMarkDone = parseMarkDone(input);
                    markTaskDone(indexToMarkDone);
                } else if (CommandType.ADD_DEADLINE.isCommandTypeFor(input)) {
                    Task incomingTask = parseAddDeadline(input);
                    addTask(incomingTask);
                } else if (CommandType.ADD_EVENT.isCommandTypeFor(input)) {
                    Task incomingTask = parseAddEvent(input);
                    addTask(incomingTask);
                } else if (CommandType.ADD_TODO.isCommandTypeFor(input)) {
                    Task incomingTask = parseToDo(input);
                    addTask(incomingTask);
                } else if (CommandType.REMOVE_TASK.isCommandTypeFor(input)) {
                    int indexToDelete = parseDelete(input);
                    deleteTask(indexToDelete);
                } else{
                    // do not understand the command..
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getCause());
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        Duke chatbot  = new Duke();
        chatbot.run();
    }
}
