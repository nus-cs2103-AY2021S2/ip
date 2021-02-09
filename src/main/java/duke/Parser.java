package duke;

/**
 * Handles individual inputs and interprets the commands from the user.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Only constructor for parser. Gets all the parameters from dukeCatches cases where the user inputs an invalid command.
     *
     * @param duke the current duke instance
     * @param taskList manager of the list of tasks
     * @param ui user interface
     * @param storage storage manager of the duke. Persists even when duke instance is closed
     */
    protected String activate(String input, Duke duke, TaskList taskList, Ui ui, Storage storage) {
        try {
            return handleCommand(duke, input, taskList, ui, storage);
        } catch (Exception e) {
            return String.format("%s\n☹ %s\n%s", Duke.line, e.getMessage(), Duke.line);
        }
    }

    /**
     * Listens to input. Parses the input into the command, body and date/time.
     * Activates taskList to manage the task.
     * Activates UI to respond to the user.
     * Hanldes commands:
     * "list", "save", "bye", "delete", "done", "todo" , "deadline", "event".
     *
     * @param duke the current duke instance
     * @param currLine the current input from the user
     * @param taskList manager of the list of tasks
     * @param ui user interface
     * @param storage storage manager of the duke. Persists even when duke instance is closed
     * @throws Exception when an invalid command is made. i.e. the first word in the input is
     *                   invalid
     * @return
     */
    private String handleCommand(Duke duke, String currLine, TaskList taskList, Ui ui, Storage storage) throws Exception {

        currLine = currLine.toLowerCase();
        assert !currLine.equals("");
        String[] parsedLine = currLine.split(" ");
        if (currLine.startsWith("list")) {
            return ui.printListTasks(taskList);
        } else if (currLine.startsWith("find")) {
            return ui.find(taskList, currLine);
        } else if (currLine.startsWith("save")) {
            storage.save(ui, taskList);
            return "Your information has been saved!";
        } else if (currLine.startsWith("bye")) {
            taskList.bye(duke);
            return ui.bye();
        } else if (currLine.startsWith("delete")) {
            Task task = taskList.delete(Integer.parseInt(parsedLine[1]));
            return ui.delete(task, taskList.list.size());
        } else if (currLine.startsWith("done")) {
            Task task = taskList.doTask(Integer.parseInt(parsedLine[1]));
            return ui.doTask(task);
        } else if (currLine.startsWith("todo")) {
            Task task = taskList.addTask(new Todo(currLine));
            return ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("deadline")) {
            Task task = taskList.addTask(new Deadline(currLine));
            return ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("event")) {
            Task task = taskList.addTask(new Event(currLine));
            return ui.addTask(task, taskList.list.size());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
