/**
 * An app that interacts with the users and help them list down tasks
 * they need to do.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /*
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
    */

    public String getResponse(String command) {
        String output = "";
        if (command.equals("bye")) {
            return Ui.doBye();
        }
        try {
            this.parser = new Parser(command);
            String parsedCommand = this.parser.getCommand();
            String description = this.parser.getDescription();
            String deadline = this.parser.getDeadLine();
            if (parsedCommand.equals("list")) {
                output = taskList.iterateList();
            } else if (parsedCommand.equals("done")) {
                output = taskList.finishATask(description);
            } else if (parsedCommand.equals("delete")) {
                output = taskList.deleteATask(description);
            } else if (parsedCommand.equals("find")) {
                output = taskList.findTasks(description);
            } else {
                Task task = processTask(parsedCommand, description, deadline);
                output = taskList.addTask(task);
            }
        } catch (DukeException e) {
            output = Ui.showMessage(e.getMessage());
        } catch (DukeExceptionDeadline e) {
            output = Ui.showMessage(e.getMessage());
        }
        try {
            storage.writeFile(taskList);
        } catch (DukeException e) {
            output = Ui.showMessage(e.getMessage());
        }
        return output;
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
