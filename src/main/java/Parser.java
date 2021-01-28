import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private Scanner sc;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.sc = new Scanner(System.in);
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    public void open() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                identifyInput(input);
            } catch (DukeGeneralException e) {
                ui.showDukeGeneralError();
            } catch (DukeTaskException e1) {
                ui.showDukeTaskError();
            }
        }
    }

    public void close() {
        sc.close();
    }

    private void identifyInput(String input) throws DukeGeneralException, DukeTaskException {
        Task temp;
        switch (getCommand(input)) {
            case "bye":
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                ui.showBye();
                break;

            case "list":
                try {
                    ui.showList(tasks);
                } catch (Exception e) {
                    ui.showDukeEmptyListError();
                }
                break;

            case "done":
                tasks.setDone(ui.getIndex(input));
                ui.showDone(input, tasks);
                break;

            case "todo":
                temp = new ToDo(getMessage(input));
                ui.showTaskAdded(tasks, temp);
                tasks.add(temp);
                break;

            case "deadline":
                temp = new Deadline(getMessage(input));
                ui.showTaskAdded(tasks, temp);
                tasks.add(temp);
                break;

            case "event":
                temp = new Event(getMessage(input));
                ui.showTaskAdded(tasks, temp);
                tasks.add(temp);
                break;

            case "delete":
                ui.showDeleteTask(input, tasks);
                tasks = tasks.remove(ui.getIndex(input));
                break;

            case "empty":
                throw new DukeTaskException("☹ OOPS!!! The description of a todo cannot be empty.");

            default:
                throw new DukeGeneralException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private String getCommand(String input) {
        try {
            String[] arr = input.split(" ", 2);
            return arr[0];
        } catch (Exception e) {
            return "empty";
        }
    }

    private String getMessage(String input) throws DukeTaskException {
        try {
            String[] arr = input.split(" ", 2);
            return arr[1];
        } catch (Exception e) {
            return "empty";
        }

    }

}
