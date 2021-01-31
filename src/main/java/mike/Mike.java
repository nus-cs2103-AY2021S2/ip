package mike;

import mike.task.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Mike {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Create a Mike Object with a taskList from an existing file
     * Otherwise creates a Mike Object with an empty taskList
     *
     * @param filePath the path of the *.txt file holding information of existing tasks
     */
    public Mike(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
        } catch (ParseException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        } catch (IOException e) {
            ui.showIOError(e);
        }
    }

    public void run() {
        ui.init();
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.next();
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                case BYE:
                    ui.exit();
                    return;
                case LIST:
                    try {
                        ui.showList(taskList);
                    } catch (NullPointerException e) {
                        ui.showListError();
                    }
                    break;
                case DONE:
                    int i = sc.nextInt();
                    try {
                        ui.showMarkSuccess(taskList.mark(i));
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIndexOutOfBoundsError(taskList);
                    }
                    break;
                case DELETE:
                    int k = sc.nextInt();
                    try {
                        Task deletedTask = taskList.delete(k);
                        ui.showDeleteSuccess(taskList, deletedTask);
                    } catch (IndexOutOfBoundsException e) {
                        ui.showIndexOutOfBoundsError(taskList);
                    }
                    break;
                case FIND:
                    String keyword = sc.next();
                    ui.showMatchingResults(taskList.find(keyword));
                    break;
                default:
                    String description = sc.nextLine();
                    try {
                        Task addedTask = Parser.parseDescription(command, description);
                        taskList.add(addedTask);
                        ui.showAddSuccess(taskList, addedTask);
                        storage.save(taskList);
                    } catch (ParseException e) {
                        ui.showError(e);
                    } catch (IOException e) {
                        ui.showIOError(e);
                    } catch (DateTimeParseException e) {
                        ui.showDateTimeParseError(e);
                    }
                }
            } catch (IllegalArgumentException e) {
                ui.showGeneralError();
            }
        } while (true);
    }

    public static void main(String[] args) {
        new Mike("data/tasks.txt").run();
    }

}