import java.util.Scanner;

/**
 * An app that interacts with the users and help them list down tasks
 * they need to do.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Main function of the class.
     */
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

    /**
     * Constructor of the Duke app
     * @param filepath A string showing the path of the txt file
     * @throws DukeExceptionFolder The 'data' folder doesn't exist
     * @throws DukeExceptionCorruptedData The txt file format is corrupted
     */
    public Duke(String filepath) throws DukeExceptionFolder, DukeExceptionCorruptedData {
        this.storage = new Storage(filepath);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * After loading the data from the storage, run Duke application.
     */
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

    /**
     * A function that process the command from the users
     * @param command The type of task given
     * @param description The description of the task need to be done
     * @param deadline The deadline of the task (if any)
     * @return Task object based on the command
     * @throws DukeException Command is not valid
     * @throws DukeExceptionDeadline Deadline given is not in "yyyy-MM-dd"
     */

    public static Task processTask(String command, String description, String deadline)
            throws DukeException, DukeExceptionDeadline {
        if (command.equals("todo")) {
            return new Todo(description);
        } else if (command.equals("deadline")) {
            return new Deadline(description, deadline);
        } else if (command.equals("event")) {
            return new Event(description, deadline);
        }
        throw new DukeException("Invalid Command");
    }
}
