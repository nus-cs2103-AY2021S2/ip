import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Mister Duke is an interactive chat bot that
 * keep track of your tasks, deadlines and events
 * in a list.
 * Some things Mister Duke is good at:
 * - listing the tasks on your list
 * - marking specified tasks as done
 * - removing specified tasks from the list
 * - find matching tasks given a search word/phrase
 *
 * @author Shaelyn
 * @version CS2103T 20/21 Semester 2, Individual Project
 */

public class MisterDuke {

    private final Ui ui;
    private final Storage storage;
    private ArrayList<Task> tasks;

    public MisterDuke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new ArrayList<>(storage.load());
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Runs Mister Duke
     * @throws IOException when the input is wrong/incomplete
     */
    public void run() throws IOException {
        storage.load();
        ui.printLine();
        ui.showWelcome();
        ui.printLine();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String command = ui.readCommand();
                ui.printLine();
                Command cmd = Parser.parse(command);
                cmd.executeCommand(ui, storage, tasks);
                isRunning = cmd.isRunning();
            } catch (DukeException e) {
                ui.showDefaultError(e);
            } finally {
                ui.printLine();
            }
        }
        storage.save(tasks);
    }

    /**
     * Main driver function
     * @param args command line args
     * @throws IOException when the input is wrong/incomplete
     */
    public static void main(String[] args) throws IOException {
        new MisterDuke("src\\main\\data\\duke.txt").run();
    }
}

/** The user interface class is for printing Mister Duke's
 * responses onto the terminal.
 *
 */
class Ui {

    /**
     * Prints horizontal line to partition Mister Duke's messages
     */
    public void printLine() {
        System.out.println("    _________________________________________________");
    }

    /**
     * Reads user's input
     * @return user's input as String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    /**
     * Displays Mister Duke's welcome message
     */
    public void showWelcome() {
        System.out.println("     Hello! Nice to meet you, I'm Mister Duke :)");
        System.out.println("     What can I do for you today?");
    }

    /**
     * Displays Mister Duke's goodbye message
     */
    public void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon! :)");
    }

    /**
     * Displays number of tasks in the list
     * @param numOfTasks in the list
     */
    public void showTaskList(int numOfTasks) {
        if (numOfTasks == 1) {
            System.out.println("     Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    /**
     * Informs user that the task input has been added to the list
     * @param task specified by user that needs to be added to the list
     */
    public void showTaskAdded(Task task) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task.toString());
    }

    /**
     * Indicates that the specified task has been completed
     * @param task specified by user that has been completed
     */
    public void showTaskDone(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
    }

    /**
     * Lists the tasks
     * @param tasksArray array list of tasks
     */
    public void showList(ArrayList<Task> tasksArray) {
        if (tasksArray.isEmpty()) {
            System.out.println("     Your list is empty, there is nothing to do. Yay!");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasksArray.size(); i++) {
                System.out.println("       " + (i + 1) + ". " + tasksArray.get(i).toString());
            }
        }
    }

    /**
     * Informs user that the specified task has been removed from the list
     * @param tasksArray array list of tasks
     * @param commandNumber the task in the list that will be removed
     */
    public void showTaskDelete(ArrayList<Task> tasksArray, String commandNumber) {
        if (tasksArray.isEmpty()) {
            System.out.println("     Oops! You have no tasks to delete.");
        } else {
            int cmdNum = Integer.parseInt(commandNumber); //strArray[1]
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + tasksArray.get(cmdNum - 1).toString());
            tasksArray.remove(cmdNum - 1);
        }
    }

    /**
     * Informs the user of wrong/incomplete input
     * @param e error message that specifies wrong/incomplete input
     */
    public void showDefaultError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showOutOfBounds() {
        System.out.println("     Oops! You don't have that many tasks");
    }

    public void showMatchingItems(ArrayList<Task> tasksArray) {
        if (tasksArray.isEmpty()) {
            System.out.println("     Oh no! There are no matching tasks :(");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < tasksArray.size(); i++) {
                System.out.println("       " + (i + 1) + ". " + tasksArray.get(i).toString());
            }
        }
    }
}

/**
 * The Storage class is for loading and saving the list of tasks
 * to the local hard disk.
 */
class Storage {
    private final File txtFile;

    public Storage (String filePath) throws IOException {
        this.txtFile = new File(filePath);
        if (txtFile.createNewFile()) {
            System.out.println("File created!");
        } else {
            System.out.println("File loaded!");
        }
    }

    /**
     * Loads list of tasks from the hard disk into the list of tasks
     * @return array list of tasks
     * @throws FileNotFoundException when the file cannot be found on the hard disk
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.txtFile);
        ArrayList<Task> tasksArrayList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String tempStr = sc.nextLine();
            String checkForTick = tempStr.substring(0, 5);
            String cmd = tempStr.substring(7);
            if (tempStr.contains("[T]")) {
                ToDo toDoTask = new ToDo(cmd);
                if (!checkForTick.contains(" ")) {
                    toDoTask.markAsDone();
                }
                tasksArrayList.add(toDoTask);
            } else if (tempStr.contains("[D]")) {
                String[] strArray = cmd.split("by:", 2);
                String inst = strArray[0].substring(0, strArray[0].length() - 2);
                String date = strArray[1].substring(0, strArray[1].length() - 1).trim();
                try {
                    Date deadlineDate = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(date);
                    Deadline deadlineTask = new Deadline(inst + " ", deadlineDate);
                    if (!checkForTick.contains(" ")) {
                        deadlineTask.markAsDone();
                    }
                    tasksArrayList.add(deadlineTask);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else if (tempStr.contains("[E]")) {
                String[] strArray = cmd.split("at:", 2);
                String inst = strArray[0].substring(0, strArray[0].length() - 2);
                String date = strArray[1].substring(0, strArray[1].length() - 1);
                Event eventTask = new Event(inst + " ", date);
                if (!checkForTick.contains(" ")) {
                    eventTask.markAsDone();
                }
                tasksArrayList.add(eventTask);
            }
        }
        sc.close();
        return tasksArrayList;
    }

    /**
     * Saves the list of tasks on to the hard disk after Mister Duke
     * has been terminated
     * @param taskArrayList list of tasks
     * @throws IOException when there is a wrong/incomplete user input
     */
    public void save(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fwriter = new FileWriter(this.txtFile);
        for (Task task : taskArrayList) {
            if (task instanceof ToDo) {
                fwriter.write(task.toString() + "\n");
            } else if (task instanceof Deadline) {
                fwriter.write(task.toString() + "\n");
            } else {
                fwriter.write(task.toString() + "\n");
            }
        }
        fwriter.close();
    }
}

/**
 * The Parser class takes in the user input, parses it,
 * then display the corresponding message from Mister Duke
 */
class Parser {
    private final Ui ui;
    private Storage storage;
    private ArrayList<Task> taskList;

    public Parser(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * The parse function takes in the user's input (command) and
     * returns a class that corresponds to the command
     * @param command user input
     * @return class that corresponds to the command
     * @throws DukeException when the user input is wrong/incomplete
     */
    public static Command parse(String command) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0].trim();

        if (cmd.equalsIgnoreCase("todo")) {
            return new ToDoCommand(command);
        } else if (cmd.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(command);
        } else if (cmd.equalsIgnoreCase("event")) {
            return new EventCommand(command);
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand(command);
        } else if (cmd.equalsIgnoreCase("done")) {
            return new DoneCommand(command);
        } else if (cmd.equalsIgnoreCase("delete")) {
            return new DeleteCommand(command);
        } else if (cmd.equalsIgnoreCase("bye")) {
            return new ExitCommand(command);
        } else if (cmd.equalsIgnoreCase("find")) {
            return new FindCommand(command);
        } else {
            throw new DukeException("     Oops! I'm sorry but I don't know what you mean by that :(");
        }
    }
}

abstract class Command {
    public abstract void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException;

    public abstract boolean isRunning();
}

/**
 * When the user inputs a ToDo task, the ToDoCommand is returned
 */
class ToDoCommand extends Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];

        if (input.equalsIgnoreCase("todo")) {
            throw new DukeException("     Could you please specify your task? :)");
        }
        String cmdTask = strArray[1];
        ToDo tempTask = new ToDo(cmdTask);
        taskList.add(tempTask);
        ui.showTaskAdded(tempTask);
        ui.showTaskList(taskList.size());
    }

    public boolean isRunning() {
        return true;
    }
}

/**
 * When the user inputs a Deadline task, the DeadlineCommand is returned
 */
class DeadlineCommand extends Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("deadline")) {
            throw new DukeException("     Could you please specify your task? :)");
        }
        
        String[] strArray = input.split(" ", 2);
        if (!strArray[1].contains("/by")) {
            throw new DukeException("     Uh oh! Please specify a timing using /by.");
        }
        
        String cmd = strArray[0];
        String cmdTask = strArray[1];
        String[] tempStrArray = cmdTask.split("/by", 2);
        String inputDate = tempStrArray[1].trim();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(inputDate);
        } catch (Exception e) {
            throw new DukeException("     Uh oh! Please enter a timing in the format dd-mm-yyyy HHmm");
        }
        Deadline tempTask = new Deadline(tempStrArray[0], date);
        taskList.add(tempTask);
        ui.showTaskAdded(tempTask);
        ui.showTaskList(taskList.size());
    }

    public boolean isRunning() {
        return true;
    }
}

/**
 * When the user inputs an Event task, the EventCommand is returned
 */
class EventCommand extends Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("event")) {
            throw new DukeException("     Could you please specify your task? :)");
        }
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String cmdTask = strArray[1];

        if (!strArray[1].contains("/at")) {
            throw new DukeException("     Uh oh! Please specify a timing using /at.");
        }
        String[] tempStrArray = cmdTask.split("/at", 2);
        Event tempTask = new Event(tempStrArray[0], tempStrArray[1]);
        taskList.add(tempTask);
        ui.showTaskAdded(tempTask);
        ui.showTaskList(taskList.size());
    }

    public boolean isRunning() {
        return true;
    }
}

/**
 * When the user requests for the current list of tasks,
 * the ListCommand is called
 */
class ListCommand extends Command {
    private String command;

    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        ui.showList(taskList);
    }

    public boolean isRunning() {
        return true;
    }
}

/**
 * When the user marks a specified task as done,
 * the DoneCommand is called
 */
class DoneCommand extends Command {
    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        if (Integer.parseInt(commandArray[1]) > taskList.size()) {
            ui.showOutOfBounds();
        } else {
            Task completedTask = taskList.get(Integer.parseInt(commandArray[1]) - 1);
            completedTask.markAsDone();
            ui.showTaskDone(completedTask);
        }
    }

    public boolean isRunning() {
        return true;
    }

}

/**
 * When the user deletes a specified task from the task list,
 * the DeleteCommand is called
 */
class DeleteCommand extends Command {
    private String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        if (Integer.parseInt(commandArray[1]) > taskList.size()) {
            ui.showOutOfBounds();
        } else {
            ui.showTaskDelete(taskList, commandArray[1]);
        }
    }

    public boolean isRunning() {
        return true;
    }

}

/**
 * When the user bids goodbye, the Exit Command is called
 */
class ExitCommand extends Command {
    private String command;

    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        ui.showGoodbye();
    }

    public boolean isRunning() {
        return false;
    }
}

class FindCommand extends Command {
    private String command;

    public FindCommand(String command){
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String keyword = strArray[1];
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tempTaskList.add(task);
            }
        }
        ui.showMatchingItems(tempTaskList);
    }

    public boolean isRunning() {
        return true;
    }
}

/**
 * The Task class is a parent class for ToDo, Deadline and Event
 * whereby each Task has a boolean to indicate whether it has been done
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The Task applies to inputs with sufficient information to classify as a todo, deadline
     * or event task.
     * @param description describes the details of a task supplied to the chat bot
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * For a given task, indicate whether it has been done using a space or a tick
     * @return if the task has been done, return a tick, else return a space
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick symbol
    }

    /**
     * For a given task, if it is done, mark it as done by changing the boolean isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

/**
 * The ToDo class is a child class of the Task Class,
 * it specifies the task as a ToDo using [T]
 */
class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * The Deadline class is a child class of the Task Class,
 * it specifies the task as a Deadline using [D]
 */
class Deadline extends Task {
    protected Date deadlineBy;

    public Deadline(String description, Date deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineBy + ")";
    }
}

/**
 * The Event class is a child class of the Task Class,
 * it specifies the task as an Event using [E]
 */
class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}

/**
 * The DukeException is a child class of the Java Exception class
 * which throws an exception message when an invalid input is given to the chat bot
 * either a wrong message (unidentified input) or an unclear message (not specific enough)
 */
class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}