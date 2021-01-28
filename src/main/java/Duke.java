import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
//    private static ArrayList<Task> userList = new ArrayList<>();
    //---------------------------------------------------------------------------------------------------------
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.getTaskList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        final String exitCommand = "bye";
        final String listCommand = "list";
        final String doneCommand = "done";
        final String deleteCommand = "delete";
        final String addTodoCommand = "todo";
        final String addDeadlineCommand = "deadline";
        final String addEventCommand = "event";
        ui.printWelcomeGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            //task taken in
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");
            Task newTask;
            ui.printHorizontalRule();
            try {
                switch (commandArr[0]) {
                case exitCommand:
                    ui.printExitMessage();
                    sc.close();
                    //add data back into the file
                    storage.writeData(tasks.getTaskList());
                    isBye = true;
                    break;
                case listCommand:
                    ui.printTasks(tasks);
                    ui.printHorizontalRule();
                    break;
                case doneCommand:
                    int taskNumber = Integer.parseInt(commandArr[1]);
                    ui.printDoneTask(tasks, taskNumber);
                    break;
                case addTodoCommand:
                    if(commandArr.length == 1){
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    else {
                        newTask = Parser.parseAddTodo(command);
                        ui.printAddedTask(tasks, newTask);
                    }
                    break;
                case addDeadlineCommand:
                    newTask = Parser.parseAddDeadline(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case addEventCommand:
                    newTask = Parser.parseAddEvent(command);
                    ui.printAddedTask(tasks, newTask);
                    break;
                case deleteCommand:
                    int taskNumToBeDeleted = Parser.parseDeleteCommand(command);
                    ui.printDeletedTask(tasks, taskNumToBeDeleted);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch(DukeException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}
