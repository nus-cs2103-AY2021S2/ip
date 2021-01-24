package Duke;

import Duke.Exception.*;
import Duke.Command.*;
import Duke.Constant.*;
import Duke.Helper.*;
import Duke.Task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final Scanner sc;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke(String path){
        this.sc = new Scanner(System.in);
        this.storage = new Storage(path);
        this.taskList = new TaskList(storage.readDataFromFile());
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(Constants.FILE_PATH);
        duke.run();
    }

    public void run() {
        System.out.println(Constants.START);
        ui.printResponse(Constants.GREETING);
        while (sc.hasNextLine()){
            String command = sc.nextLine().trim();
            if (command.equalsIgnoreCase(Command.BYE.getAction())){
                ui.printResponse(Constants.BYE);
                break;
            } else if (command.equalsIgnoreCase(Command.LIST.getAction())){
                ui.printAllTask(taskList.getList());
            } else if (command.equalsIgnoreCase(Command.DONE.getAction()) ||
                    command.equalsIgnoreCase(Command.DELETE.getAction())){
                try {
                    throw new InvalidIndex(command, taskList.getList().size());
                } catch (InvalidIndex e){
                    ui.printResponse(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DONE.getAction())){
                try{
                    int doneIndex = Integer.parseInt(command.substring(5));
                    String result = taskList.finishTask(doneIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e){
                    System.out.println(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DELETE.getAction())){
                try{
                    int deleteIndex = Integer.parseInt(command.substring(7));
                    String result = taskList.deleteTask(deleteIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e){
                    ui.printResponse(e.getMessage());
                }
            } else if (command.equalsIgnoreCase(Command.FIND.getAction())){
                try {
                    throw new EmptyFindContent();
                } catch (EmptyFindContent e) {
                    ui.printResponse(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.FIND.getAction())){
                String keyword = command.substring(5);
                ArrayList<Task> filter = taskList.findTask(keyword);
                ui.printMatchedTask(filter);
            }
            else {
                try {
                    String status = taskList.addTask(command);
                    ui.printResponse(status);
                } catch (NoSuchCommandException | EmptyTaskException | InvalidTask e){
                    ui.printResponse(e.getMessage());
                }
            }
            //update the file
            storage.writeDataToFile(taskList.getList());
        }
    }
}
