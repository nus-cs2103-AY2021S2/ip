import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    public TaskList tasks;
    public Storage storage;
    public Ui ui;
    public Parser parser;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage("C:/Users/Jeremias/Documents/GitHub/ip/data/", "data.txt");
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.printGreeting();
        storage.loadTaskList(tasks);
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                ui.printBye();
            } else {
                executeCommand(input);
                save();
            }
        }
    }

    public void executeCommand(String input) {
        String command = parser.parseCommand(input);
        try {
            if (command.equals("todo")) {
                if (parser.isCorrectTodo(input)) {
                    String description = parser.parseTodoDescripton(input);
                    Task task = new Todo(description);
                    tasks.addTask(task);
                    ui.printAddTask(task, tasks.size());
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("deadline")) {
                if (parser.isCorrectDeadline(input)) {
                    try {
                        LocalDateTime dateTime = parser.parseDeadlineDate(input);
                        String description = parser.parseDeadlineDescription(input);
                        Deadline task = new Deadline(dateTime, description);
                        tasks.addTask(task);
                        ui.printAddTask(task, tasks.size());
                    } catch (DateTimeParseException e) {
                        ui.printError(new WrongFormatDukeException(command));
                    }
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("event")) {
                if (parser.isCorrectEvent(input)) {
                    try {
                        LocalDateTime dateTime = parser.parseEventDate(input);
                        String description = parser.parseEventDescription(input);
                        Event task = new Event(dateTime, description);
                        tasks.addTask(task);
                        ui.printAddTask(task, tasks.size());
                    } catch (DateTimeParseException e) {
                        ui.printError(new WrongFormatDukeException(command));
                    }
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("list")) {
                if (parser.isCorrectList(input)) {
                    ui.printTaskList(tasks);
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("done")) {
                if (parser.isCorrectIndexCommand(input, tasks.size())) {
                    int index = parser.parseIndex(input);
                    tasks.doneTask(index);
                    ui.printDoneTask(tasks.getTask(index));
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("delete")) {
                if (parser.isCorrectIndexCommand(input, tasks.size())) {
                    int index = parser.parseIndex(input);
                    Task deleted = tasks.deleteTask(index);
                    ui.printDeleteTask(deleted);
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else if (command.equals("help")) {
                if (parser.isCorrectHelp(input)) {
                    ui.printHelp();
                } else {
                    throw new WrongFormatDukeException(command);
                }
            } else {
                throw new WrongCommandDukeException();
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
    }

    public void save() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.getTask(i).formatToSave() + "\n";
        }
        storage.saveTaskList(str);
    }
}