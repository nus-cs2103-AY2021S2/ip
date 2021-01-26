import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandParser {
    private static final int SPLIT_LIMIT = 2;
    private TaskList tasks;
    private Ui ui;

    public CommandParser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public boolean parseCommand(String userInput) throws DukeException {
        // display list
        if (userInput.toLowerCase().equals("list")) {
            ui.displayList(tasks);
        // finish a task
        } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
            String[] bits = userInput.split(" ");
            int idx = Integer.parseInt(bits[1]);
            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Oops! That doesn't appear to be a valid task number.");
            } else {
                Task finishedTask = tasks.get(idx);
                if (finishedTask.isDone()) {
                    throw new DukeException("That task's already done!");
                } else {
                    finishedTask.finish();
                    ui.showDoneTask(finishedTask);
                }
            }
        // manually remove task
        } else if (userInput.toLowerCase().matches("^(delete|remove) \\d+$")) {
            String[] bits = userInput.split(" ");
            int idx = Integer.parseInt(bits[1]);
            if (idx < 1 || idx > tasks.size()) {
                throw new DukeException("Oops! That doesn't appear to be a valid task number.");
            } else {
                Task removedTask = tasks.remove(idx);
                ui.showRemovedTask(removedTask, tasks.size());
            }
        // add task to list
        } else if (userInput.toLowerCase().matches("^(todo|deadline|event)( .+)?$")) {
            Task newTask = TaskParser.parseTask(userInput);
            tasks.add(newTask);
            ui.showAddedTask(newTask, tasks.size());
        // find task in list
        } else if (userInput.toLowerCase().startsWith("find")) {
            String[] bits = userInput.split(" ", SPLIT_LIMIT);
            if (bits.length == 1) {
                throw new DukeException("Oops! Usage: find [search pattern]");
            } else {
                TaskList matchingTasks = tasks.find(bits[1]);
                ui.displayList(matchingTasks);
            }
        // end session
        } else if (userInput.toLowerCase().equals("bye")) {
            return false;
        } else {
            throw new DukeException("I don't understand that command!");
        }
        return true;
    }
}