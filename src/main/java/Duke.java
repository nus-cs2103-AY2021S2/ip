/**
 * An app that interacts with the users and help them list down tasks
 * they need to do.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

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
            output = processCommand(parsedCommand, description);
            if (output.equals("")){
                Task task = processTask(parsedCommand, description, deadline);
                output = taskList.addTask(task);
            }
            return output;
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
        assert(!output.equals(""));
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
        this.taskList = this.storage.load();
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
        switch (command){
            case "todo":
                return new Todo(description);
            case "deadline":
                return new Deadline(description, deadline);
            case "event":
                return new Event(description, deadline);
        }
        throw new DukeException("Invalid Command");
    }

    public String processCommand(String command, String description) throws DukeException {
        switch (command){
            case "list":
                assert (description.equals(""));
                return taskList.iterateList();
            case "done":
                return taskList.finishATask(description);
            case "delete":
                return taskList.deleteATask(description);
            case "find":
                return taskList.findTasks(description);
        }
        return "";
    }
}
