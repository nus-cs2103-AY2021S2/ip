import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public static void main(String[] args) {
        try {
            new Duke("./data/duke.txt").run();
        } catch (DukeExceptionFolder e) {
            Ui.showMessage(e.getMessage());
            return;
        } catch (DukeExceptionCorruptedData e) {
            Ui.showMessage(e.getMessage());
            return;
        }
    }

    public Duke(String filepath) throws DukeExceptionFolder, DukeExceptionCorruptedData {
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage.load());
    }

    public void run() {
        Ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                Ui.doBye();
                break;
            }
            Ui.printLine();
            String[] inputs = command.split(" ");
            try {
                this.parser = new Parser(command);
                command = this.parser.getCommand();
                String description = this.parser.getDescription();
                String deadline = this.parser.getDeadLine();
                if (command.equals("list")) {
                    taskList.iterateList();
                    continue;
                } else if (command.equals("done")) {
                    taskList.finishATask(description);
                } else if (command.equals("delete")) {
                    taskList.deleteATask(description);
                } else if (command.equals("find")) {
                    taskList.findTasks(description);
                } else {
                    Task task = processTask(command, description, deadline);
                    taskList.addTask(task);
                }
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
                continue;
            } catch (DukeExceptionDeadline e) {
                Ui.showMessage(e.getMessage());
                continue;
            }
            try {
                storage.writeFile(taskList);
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            }
        }
    }

    public static Task processTask(String command, String description, String deadline) throws DukeException, DukeExceptionDeadline{
        if (command.equals("todo")) {
            return new Todo(description);
        }
        else if (command.equals("deadline")) {
            return new Deadline(description, deadline);
        }
        else if (command.equals("event")) {
            return new Event(description, deadline);
        }
        throw new DukeException("Invalid Command");
    }
}
