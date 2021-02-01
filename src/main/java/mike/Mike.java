package mike;

import mike.task.Task;
import mike.ui.TextUi;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Mike {

    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    /**
     * Create a Mike Object with a taskList from an existing file
     * Otherwise creates a Mike Object with an empty taskList
     *
     * @param filePath the path of the *.txt file holding information of existing tasks
     */
    public Mike(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new TextUi();
        try {
            taskList = new TaskList(storage.load());
        } catch (ParseException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        } catch (IOException e) {
            ui.showIOError(e);
        }
    }

    public String getResponse(String input) {
        try {
            int endIndOfCommand = input.indexOf(' ');
            String descriptionInput = input.substring(endIndOfCommand);
        } catch (StringIndexOutOfBoundsException e) {
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                case BYE:
                    return ui.exit();
                case LIST:
                    try {
                        return ui.showList(taskList);
                    } catch (NullPointerException e2) {
                        return ui.showListError();
                    }
                default:
                    return ui.showGeneralError();
                }
            } catch (IllegalArgumentException e2) {
                return ui.showGeneralError();
            }
        }

        try {
            int endIndOfCommand = input.indexOf(' ');
            String commandInput = input.substring(0, endIndOfCommand);
            String descriptionInput = input.substring(endIndOfCommand + 1);
            System.out.println(descriptionInput);
            Command command = Parser.parseCommand(commandInput);
            switch (command) {
            case DONE:
                try {
                    int i = Integer.parseInt(descriptionInput);
                    return ui.showMarkSuccess(taskList.mark(i));
                } catch (IndexOutOfBoundsException e) {
                    return ui.showIndexOutOfBoundsError(taskList);
                }
            case DELETE:
                try {
                    int k = Integer.parseInt(descriptionInput);
                    Task deletedTask = taskList.delete(k);
                    return ui.showDeleteSuccess(taskList, deletedTask);
                } catch (IndexOutOfBoundsException e) {
                    return ui.showIndexOutOfBoundsError(taskList);
                }
            case FIND:
                return ui.showMatchingResults(taskList.find(descriptionInput));
            default:
                try {
                    Task addedTask = Parser.parseDescription(command, descriptionInput);
                    taskList.add(addedTask);
                    String response = ui.showAddSuccess(taskList, addedTask);
                    storage.save(taskList);
                    return response;
                } catch (ParseException e) {
                    return ui.showError(e);
                } catch (IOException e) {
                    return ui.showIOError(e);
                } catch (DateTimeParseException e) {
                    return ui.showDateTimeParseError(e);
                }
            }
        } catch (IllegalArgumentException e) {
            return ui.showGeneralError();
        }
    }

    public static void main(String[] args) {
        Mike mike = new Mike("data/tasks.txt");
    }

}