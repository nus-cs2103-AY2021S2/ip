import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DukeLevel class is an interactive chat bot that can:
 * - add items to a list
 * - display a list of items which were previously added
 * - mark items in the list as done
 * - remove items from the list
 * - throw exceptions if the input is incorrect
 *
 * @author Shaelyn
 * @version CS2103T 20/21 Semester 2, Individual Project
 */

public class MisterDuke {

    private Ui ui;
    private Storage storage;
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

    public static void main(String[] args) throws IOException {
        new MisterDuke("src\\main\\data\\duke.txt").run();
    }
}

class Ui {

    public void printLine() {
        System.out.println("    _________________________________________________");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    public void showWelcome() {
        System.out.println("     Hello! Nice to meet you, I'm Mister Duke :)");
        System.out.println("     What can I do for you today?");
    }

    public void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon! :)");
    }

    public void showTaskList(int numOfTasks) {
        if (numOfTasks == 1) {
            System.out.println("     Now you have " + numOfTasks + " task in the list.");
        } else {
            System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        }
    }

    public void showTaskAdded(Task task) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task.toString());
    }

    public void showTaskDone(Task task) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
    }

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

    public void showDefaultError(Exception e) {
        System.out.println(e.getMessage());
    }
}

class Storage {
    private File txtFile;

    public Storage (String filePath) throws IOException {
        this.txtFile = new File(filePath);
        if (txtFile.createNewFile()) {
            System.out.println("File created!");
        } else {
            System.out.println("File loaded!");
        }
    }

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
                String date = strArray[1].substring(0, strArray[1].length() - 1);
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Deadline deadlineTask = new Deadline(inst + " ", localDate);
                if (!checkForTick.contains(" ")) {
                    deadlineTask.markAsDone();
                }
                tasksArrayList.add(deadlineTask);
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

class Parser {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> taskList;

    public Parser(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

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
        } else {
            throw new DukeException("     Oops! I'm sorry but I don't know what you mean by that :(");
        }
    }
}

abstract class Command {
    public abstract void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException;

    public abstract boolean isRunning();
}

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

class DeadlineCommand extends Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    public boolean isDate(String str) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("DD-MM-yyyy");
        try {
            LocalDate.parse(str, dateTimeFormatter);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        return true;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("deadline")) {
            throw new DukeException("     Could you please specify your task? :)");
        }
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String cmdTask = strArray[1];

        if (!strArray[1].contains("/by")) {
            throw new DukeException("     Uh oh! Please specify a timing using /by.");
        }
        String[] tempStrArray = cmdTask.split("/by", 2);
        if (isDate(tempStrArray[1])) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(tempStrArray[1], dateTimeFormatter);
            Deadline tempTask = new Deadline(tempStrArray[0], localDate);
            taskList.add(tempTask);
            ui.showTaskAdded(tempTask);
            ui.showTaskList(taskList.size());
        } else {
            throw new DukeException("     Uh oh! Please enter a timing in the format dd-mm-yyyy");
        }
    }

    public boolean isRunning() {
        return true;
    }
}

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

class DoneCommand extends Command {
    private String command;

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        Task completedTask = taskList.get(Integer.parseInt(commandArray[1]) - 1);
        completedTask.markAsDone();
        ui.showTaskDone(completedTask);
    }

    public boolean isRunning() {
        return true;
    }

}

class DeleteCommand extends Command {
    private String command;

    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String[] commandArray = command.trim().split(" ");
        ui.showTaskDelete(taskList, commandArray[1]);
        ui.showTaskList(taskList.size());
    }

    public boolean isRunning() {
        return true;
    }

}

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
    public String toString() { return "[" + getStatusIcon() + "] " + description; }
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
    protected LocalDate deadlineBy;

    public Deadline(String description, LocalDate deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadlineBy.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
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