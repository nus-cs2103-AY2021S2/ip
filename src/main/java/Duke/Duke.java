package Duke;

import java.util.ArrayList;
import java.util.Scanner;

import Duke.Command.Command;
import Duke.Constant.Constants;
import Duke.Exception.EmptyFindContent;
import Duke.Exception.EmptyTaskException;
import Duke.Exception.InvalidIndex;
import Duke.Exception.InvalidTask;
import Duke.Exception.NoSuchCommandException;
import Duke.Helper.Storage;
import Duke.Helper.TaskList;
import Duke.Helper.Ui;
import Duke.Task.Task;

/**
 * The main driver class for this application
 */
public class Duke {
    private final Scanner sc;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Empty constructor for Duke class
     */
    public Duke() {
        this.sc = new Scanner(System.in);
        this.storage = new Storage(Constants.FILE_PATH);
        this.taskList = new TaskList(storage.readDataFromFile());
        this.ui = new Ui();
    }

    /**
     * The Duke class constructor has 1 parameter: a path to the destination for reading and writing data.
     * @param path the destination for reading and writing data.
     */
    public Duke(String path) {
        this.sc = new Scanner(System.in);
        this.storage = new Storage(path);
        this.taskList = new TaskList(storage.readDataFromFile());
        this.ui = new Ui();
    }

    /**
     * Main method for running this application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(Constants.FILE_PATH);
        duke.run();
    }

    public String getResponse(String command) {
        if (command.equalsIgnoreCase(Command.LIST.getAction())) {
            ArrayList<Task> list = taskList.getList();
            if (list.isEmpty()) {
                return Constants.EMPTY_TASK_LIST;
            }
            StringBuilder output = new StringBuilder();
            output.append(Constants.START_LISTING).append('\n');
            for (int i = 0; i < list.size(); i++) {
                output.append(i + 1).append(". ").append(list.get(i)).append('\n');
            }
            return output.toString();
        } else if (command.equalsIgnoreCase(Command.DONE.getAction())
                || command.equalsIgnoreCase(Command.DELETE.getAction())) {
            try {
                throw new InvalidIndex(command, taskList.getList().size());
            } catch (InvalidIndex e) {
                return e.getMessage();
            }
        } else if (command.toLowerCase().startsWith(Command.DONE.getAction())) {
            try {
                int doneIndex = Integer.parseInt(command.substring(5));
                return taskList.finishTask(doneIndex);
            } catch (NumberFormatException | InvalidIndex e) {
                return e.getMessage();
            }
        } else if (command.toLowerCase().startsWith(Command.DELETE.getAction())) {
            try {
                int deleteIndex = Integer.parseInt(command.substring(7));
                return taskList.deleteTask(deleteIndex);
            } catch (NumberFormatException | InvalidIndex e) {
                return e.getMessage();
            }
        } else if (command.equalsIgnoreCase(Command.FIND.getAction())) {
            try {
                throw new EmptyFindContent();
            } catch (EmptyFindContent e) {
                return e.getMessage();
            }
        } else if (command.toLowerCase().startsWith(Command.FIND.getAction())) {
            String keyword = command.substring(5);
            ArrayList<Task> filter = taskList.findTask(keyword);
            if (filter.isEmpty()) {
                return Constants.FIND_FAIL;
            } else {
                StringBuilder output = new StringBuilder();
                output.append(Constants.FIND_SUCCESS).append('\n');
                for (int i = 0; i < filter.size(); i++) {
                    output.append(i + 1).append(". ").append(filter.get(i)).append('\n');
                }
                return output.toString();
            }
        } else {
            try {
                String status = taskList.addTask(command);
                storage.writeDataToFile(taskList.getList());
                return status;
            } catch (NoSuchCommandException | EmptyTaskException | InvalidTask e) {
                return e.getMessage();
            }
        }
    }

    private void run() {
        System.out.println(Constants.START);
        ui.printResponse(Constants.GREETING);
        while (sc.hasNextLine()) {
            String command = sc.nextLine().trim();
            if (command.equalsIgnoreCase(Command.BYE.getAction())) {
                ui.printResponse(Constants.BYE);
                break;
            } else if (command.equalsIgnoreCase(Command.LIST.getAction())) {
                ui.printAllTask(taskList.getList());
            } else if (command.equalsIgnoreCase(Command.DONE.getAction())
                    || command.equalsIgnoreCase(Command.DELETE.getAction())) {
                try {
                    throw new InvalidIndex(command, taskList.getList().size());
                } catch (InvalidIndex e) {
                    ui.printResponse(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DONE.getAction())) {
                try {
                    int doneIndex = Integer.parseInt(command.substring(5));
                    String result = taskList.finishTask(doneIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DELETE.getAction())) {
                try {
                    int deleteIndex = Integer.parseInt(command.substring(7));
                    String result = taskList.deleteTask(deleteIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e) {
                    ui.printResponse(e.getMessage());
                }
            } else if (command.equalsIgnoreCase(Command.FIND.getAction())) {
                try {
                    throw new EmptyFindContent();
                } catch (EmptyFindContent e) {
                    ui.printResponse(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.FIND.getAction())) {
                String keyword = command.substring(5);
                ArrayList<Task> filter = taskList.findTask(keyword);
                ui.printMatchedTask(filter);
            } else {
                try {
                    String status = taskList.addTask(command);
                    ui.printResponse(status);
                } catch (NoSuchCommandException | EmptyTaskException | InvalidTask e) {
                    ui.printResponse(e.getMessage());
                }
            }
            //update the file
            storage.writeDataToFile(taskList.getList());
        }
    }
}
