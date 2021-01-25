import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException, FileNotFoundException {
        ui.showGreetings();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            String commandType = Parser.parseCommandType(command);
            try {
                if (commandType.equals("list")) {
                    // SHOW LIST
                    ui.showList(tasks.list);
                } else if (commandType.equals("done")) {
                    // MARK TASK AS COMPLETE
                    int taskIndex = Parser.parseTaskIndex(command) - 1;
                    Task completedTask = tasks.list.get(taskIndex);
                    completedTask.markAsDone();
                    ui.showDone(completedTask);
                } else if (commandType.equals("todo")) {
                    // ADD TODO TASK
                    try {
                        String description = Parser.parseTodoDescription(command);
                        Task task = new Todo(description);
                        tasks.addTask(task);
                        ui.showAddTask(tasks.list);
                    } catch (StringIndexOutOfBoundsException e){
                        ui.showInvalidTodo();
                    }
                } else if (commandType.equals("deadline")) {
                    // ADD DEADLINE TASK
                    String deadlineDescription = Parser.parseDeadlineDescription(command);
                    LocalDate deadlineDate = Parser.parseDeadlineDate(command); // INPUT DATE IS YYYY-MM-DD
                    Task task = new Deadline(deadlineDescription, deadlineDate);
                    tasks.addTask(task);
                    ui.showAddTask(tasks.list);
                } else if (commandType.equals("event")) {
                    // ADD EVENT TASK
                    String eventDescription = Parser.parseEventDescription(command);
                    String eventDate = Parser.parseEventDate(command);
                    Task task = new Event(eventDescription, eventDate);
                    tasks.addTask(task);
                    ui.showAddTask(tasks.list);
                } else if (commandType.equals("delete")) {
                    int taskIndex = Parser.parseTaskIndex(command) - 1;
                    Task deletedTask = tasks.list.get(taskIndex);
                    tasks.removeTask(taskIndex);
                    ui.showDeleteTask(tasks.list, deletedTask);
                } else {
                    throw new DukeException(ui.showInvalidCommand());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            command = scanner.nextLine();

        }

        if (command.equals("bye")) {
            ui.showExit();
            storage.save(tasks.list);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, DukeException {
        new Duke("duke.txt").run();
    }
}
