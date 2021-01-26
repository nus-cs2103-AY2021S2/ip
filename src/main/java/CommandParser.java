/**
 * Parses user commands and triggers corresponding effects.
 */
public class CommandParser {
    private TaskList tasks;
    private Ui ui;

    public CommandParser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses a user-input command and triggers the relevant effects.
     * @param userInput String representation of the command to be parsed.
     * @return Boolean flag used to indicate whether the program should terminate or not.
     * @throws DukeException If an Exception occurs due to a malformed command.
     */
    public boolean parseCommand(String userInput) throws DukeException {
        if (userInput.toLowerCase().equals("list")) {
            // display list
            ui.borderPrint(tasks.display());
        } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
            // finish a task
            String[] bits = userInput.split(" ");
            int idx = Integer.parseInt(bits[1]);
            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Oops! That doesn't appear to be a valid task number.");
            } else {
                Task finishedTask = tasks.get(idx);
                if (finishedTask.isDone()) {
                    throw new DukeException("That task's already done!");
                } else {
                    finishedTask.markAsDone();
                    ui.showDoneTask(finishedTask);
                }
            }
        } else if (userInput.toLowerCase().matches("^(delete|remove) \\d+$")) {
            // manually remove task
            String[] bits = userInput.split(" ");
            int idx = Integer.parseInt(bits[1]);
            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Oops! That doesn't appear to be a valid task number.");
            } else {
                Task removedTask = tasks.remove(idx);
                ui.showRemovedTask(removedTask, tasks.size());
            }
        } else if (userInput.toLowerCase().matches("^(todo|deadline|event)( .+)?$")) {
            // add task to list
            Task newTask = TaskParser.parseTask(userInput);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.size());
        } else if (userInput.toLowerCase().equals("bye")) {
            // end session
            return false;
        } else {
            throw new DukeException("I don't understand that command!");
        }
        return true;
    }
}