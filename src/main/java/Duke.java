//import java.lang.reflect.Array;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    public Duke(String filePath) throws DukeException {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] launchArgs) throws Exception {
        new Duke("../duke/data/tasks.txt").run();
    }

    private void run() throws DukeException {
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void runCommandLoopUntilExitCommand() {
        String commandText = "";
        while (!commandText.equals("bye")){
            commandText = ui.getUserCommand();
            try {
                Parser commandParser = new Parser();
                String operator = commandParser.parseOperator(commandText);
                executeCommand(operator, commandText);
            } catch (DukeException e){
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    private void executeCommand(String operator, String commandText) throws DukeException {
        Parser commandParser = new Parser();
        try {
            switch (operator) {
                case "done":
                    int taskNumberToComplete = commandParser.parseDone(commandText);
                    completeTask(taskNumberToComplete);
                    break;
                case "delete":
                    int taskNumberToDelete = commandParser.parseDelete(commandText);
                    deleteTask(taskNumberToDelete);
                    break;
                case "todo":
                    String description = commandParser.parseAddToDo(commandText);
                    addToDo(description);
                    break;
                case "deadline":
                    String[] detailsDeadline = commandParser.parseAddDeadline(commandText);
                    addDeadline(detailsDeadline);
                    break;
                case "event":
                    String[] detailsEvent = commandParser.parseAddEvent(commandText);
                    addEvent(detailsEvent);
                    break;
                case "list":
                    displayList();
                    break;
                case "find":
                    String keyWord = commandParser.parseFindTask(commandText);
                    findTask();
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
        storage.updateTaskList(tasks);
    }

    private void addEvent(String[] details) {
        String description = details[0];
        String time = details[1];
        Event newTask = new Event(description, time);
        tasks.addTask(newTask);
        ui.showAddMessage(newTask, tasks.getSize());
    }

    private void addDeadline(String[] details) {
        String description = details[0];
        String time = details[1];
        Deadline newTask = new Deadline(description, time);
        tasks.addTask(newTask);
        ui.showAddMessage(newTask, tasks.getSize());
    }

    private void addToDo(String description) {
        ToDo newTask = new ToDo(description);
        tasks.addTask(newTask);
        ui.showAddMessage(newTask, tasks.getSize());
    }

    private void completeTask(int taskNumber) {
        Task task = tasks.markTaskAsDone(taskNumber);
        ui.showCompleteMessage(task);
    }

    private void deleteTask(int taskNumber) {
        Task task = tasks.deleteTask(taskNumber);
        ui.showDeleteMessage(task, tasks.getSize());
    }

    private void displayList(){
        ui.printMyTask(tasks);
    }

    private void findTask() {

    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}