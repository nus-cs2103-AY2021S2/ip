package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String divider = "____________________________________________________________\n";
    public static final String filePath = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch(DukeException error) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                Command command = parser.parseCommand(input);
                switch(command) {
                    case BYE:
                        ui.showGoodBye();
                        storage.save(tasks);
                        break;
                    case LIST:
                        ui.showTasks(tasks);
                        break;
                    case DONE:
                        try {
                            int index = parser.parseDoneCommand(input);
                            ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.markTaskAsDone(index));
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("Please select a valid item to mark as done.");
                        } catch (IndexOutOfBoundsException error) {
                            ui.showErrorMessage("Selected item does not exist.");
                        }
                        break;
                    case DELETE:
                        try {
                            int index = parser.parseDeleteCommand(input);
                            ui.showMessage("Noted. I've removed this task:\n  " + tasks.deleteTask(index) + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("Please select an item to delete.");
                        } catch (IndexOutOfBoundsException error) {
                            ui.showErrorMessage("Selected item does not exist.");
                        }
                        break;
                    case TODO:
                        try {
                            Todo curr = parser.parseTodoCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of a todo cannot be empty.");
                        }
                        break;
                    case DEADLINE:
                        try {
                            Deadline curr = parser.parseDeadlineCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of a deadline cannot be empty");
                        } catch(DateTimeParseException error) {
                            ui.showErrorMessage("The date provided is invalid");
                        }
                        break;
                    case EVENT:
                        try {
                            Event curr = parser.parseEventCommand(input);
                            tasks.addTask(curr);
                            ui.showMessage("Got it. I've added this task:\n  " + curr + "\nNow you have " + tasks.getSize() + " tasks in the list.");
                            storage.save(tasks);
                        } catch (DukeException error) {
                            ui.showErrorMessage("The description of an event cannot be empty");
                        }
                        break;
                    case HELP:
                        ui.showHelpMessage();
                        break;
                }
            } catch(DukeException error) {
                ui.showInputError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
