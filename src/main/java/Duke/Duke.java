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

    /**
     * Gets the response, use for Duke GUI.
     * @param command The type of command.
     * @return The output shows to the user screen.
     */
    public String getResponse(String command) {
        command = command.trim();
        if (command.equalsIgnoreCase(Command.BYE.getAction())) {
            return Constants.BYE;
        } else if (command.equalsIgnoreCase(Command.LIST.getAction())) {
            return handleListCommand();
        } else if (command.equalsIgnoreCase(Command.STATISTICS.getAction())) {
            return handleStatCommand();
        } else if (command.toLowerCase().startsWith(Command.DONE.getAction())) {
            return handleDoneCommand(command);
        } else if (command.toLowerCase().startsWith(Command.DELETE.getAction())) {
            return handleDeleteCommand(command);
        } else if (command.toLowerCase().startsWith(Command.FIND.getAction())) {
            return handleFindCommand(command);
        } else {
            return handleAddCommand(command);
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
                ui.printResponse(handleListCommand());
            } else if (command.equalsIgnoreCase(Command.STATISTICS.getAction())) {
                ui.printResponse(handleStatCommand());
            } else if (command.toLowerCase().startsWith(Command.DONE.getAction())) {
                ui.printResponse(handleDoneCommand(command));
            } else if (command.toLowerCase().startsWith(Command.DELETE.getAction())) {
                ui.printResponse(handleDeleteCommand(command));
            } else if (command.toLowerCase().startsWith(Command.FIND.getAction())) {
                ui.printResponse(handleFindCommand(command));
            } else {
                ui.printResponse(handleAddCommand(command));
            }
        }
    }
    private String handleFindCommand(String command) {
        if (command.equalsIgnoreCase(Command.FIND.getAction())) {
            try {
                throw new EmptyFindContent();
            } catch (EmptyFindContent e) {
                return e.getMessage();
            }
        }
        String[] keyword = command.substring(5).split(" ");
        ArrayList<Task> filter = taskList.findTask(keyword);
        if (filter.isEmpty()) {
            return Constants.FIND_FAIL;
        } else {
            StringBuilder output = new StringBuilder();
            output.append(Constants.FIND_SUCCESS).append('\n');
            for (int i = 0; i < filter.size(); i++) {
                output.append(i + 1).append(". ").append(filter.get(i));
                if (i != filter.size() - 1) {
                    output.append('\n');
                }
            }
            return output.toString();
        }
    }
    private String handleListCommand() {
        ArrayList<Task> list = taskList.getList();
        if (list.isEmpty()) {
            return Constants.EMPTY_TASK_LIST;
        }
        StringBuilder output = new StringBuilder();
        output.append(Constants.START_LISTING).append('\n');
        for (int i = 0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i));
            if (i != list.size() - 1) {
                output.append('\n');
            }
        }
        return output.toString();
    }
    private String handleStatCommand() {
        ArrayList<Task> list = taskList.getList();
        if (list.isEmpty()) {
            return Constants.EMPTY_TASK_LIST;
        } else {
            int[][] stats = taskList.statistics();
            return ui.stats(stats);
        }
    }
    private String handleDoneCommand(String command) {
        if (command.equalsIgnoreCase(Command.DONE.getAction())) {
            try {
                throw new InvalidIndex(command, taskList.getList().size());
            } catch (InvalidIndex e) {
                return e.getMessage();
            }
        } else {
            try {
                int doneIndex = Integer.parseInt(command.substring(5));
                String output = taskList.finishTask(doneIndex);
                storage.writeDataToFile(taskList.getList());
                return output;
            } catch (NumberFormatException | InvalidIndex e) {
                return e.getMessage();
            }
        }
    }
    private String handleDeleteCommand(String command) {
        if (command.equalsIgnoreCase(Command.DELETE.getAction())) {
            try {
                throw new InvalidIndex(command, taskList.getList().size());
            } catch (InvalidIndex e) {
                return e.getMessage();
            }
        } else {
            try {
                int deleteIndex = Integer.parseInt(command.substring(7));
                String output = taskList.deleteTask(deleteIndex);
                storage.writeDataToFile(taskList.getList());
                return output;
            } catch (NumberFormatException | InvalidIndex e) {
                return e.getMessage();
            }
        }
    }
    private String handleAddCommand(String command) {
        try {
            String status = taskList.addTask(command);
            storage.writeDataToFile(taskList.getList());
            return status;
        } catch (NoSuchCommandException | EmptyTaskException | InvalidTask e) {
            return e.getMessage();
        }
    }
}
