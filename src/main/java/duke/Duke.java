package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static final String filePath = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath, ui);
        try {
            tasks = new TaskList(storage.load());
        } catch(DukeException error) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = parser.parseCommand(input);
                switch(command) {
                    case BYE:
                        ui.showGoodbye();
                        storage.save(tasks);
                        break;
                    case LIST:
                        ui.showTasks(tasks);
                        break;
                    case DONE:
                        int taskToMarkAsDone = parser.parseDoneCommand(input);
                        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.markTaskAsDone(taskToMarkAsDone));
                        storage.save(tasks);
                        break;
                    case DELETE:
                        int taskToDelete = parser.parseDeleteCommand(input);
                        ui.showMessage("Noted. I've removed this task:\n  " + tasks.deleteTask(taskToDelete) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case TODO:
                        Todo newTodo = parser.parseTodoCommand(input);
                        tasks.addTask(newTodo);
                        ui.showMessage("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case DEADLINE:
                        Deadline newDeadline = parser.parseDeadlineCommand(input);
                        tasks.addTask(newDeadline);
                        ui.showMessage("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case EVENT:
                        Event newEvent = parser.parseEventCommand(input);
                        tasks.addTask(newEvent);
                        ui.showMessage("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                        storage.save(tasks);
                        break;
                    case HELP:
                        ui.showHelpMessage();
                        break;
                }
            } catch(DukeException error) {
                ui.showInputError();
            } catch(DateTimeParseException error) {
                ui.showErrorMessage("The date provided is invalid");
            } catch (IndexOutOfBoundsException error) {
                ui.showErrorMessage("Selected item does not exist.");
            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
