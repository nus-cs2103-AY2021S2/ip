package helper.command;

import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;

/**
 * Commands for adds (todo..., deadline..., event...)
 */
public class AddCommand extends Command {

    private final String whichAdd;
    private final String addString;

    public AddCommand(String s1, String s2) {
        whichAdd = s1;
        addString = s2;
    }

    /**
     * Executes the parsed command
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (whichAdd) {
            case "todo":
                Todo t = new Todo(addString);
                tasks.add(t);
                ui.dukePrint("added new todo: " + t);
            break;
            case "event":
                String[] eventInfo = addString.split(" /at ");
                try {
                    LocalDate d1 = LocalDate.parse(eventInfo[1]);
                    Event e = new Event(eventInfo[0],d1);
                    tasks.add(e);
                    ui.dukePrint("added new event: " + e);
                }
                catch (Exception e) {
                    throw new DukeException("Invalid event");
                }
            break;
            case "deadline":
                String[] deadlineInfo = addString.split(" /by ");
                try {
                    LocalDate d1 = LocalDate.parse(deadlineInfo[1]);
                    Deadline d = new Deadline(deadlineInfo[0],d1);
                    tasks.add(d);
                    ui.dukePrint("added new deadline: " + d);
                }
                catch (Exception e) {
                    throw new DukeException("Invalid deadline");
                }
            break;
        }
        storage.saveFile(tasks);
    }

}