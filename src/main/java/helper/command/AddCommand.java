package helper.command;

import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Deadline;
import task.Event;
import task.Todo;

import java.time.LocalDate;

/**
 * Commands for adds (todo..., deadline..., event...)
 */
public class AddCommand extends Command {

    private final String whichAdd;
    private final String addString;

    public AddCommand(String whichAdd, String description) {
        this.whichAdd = whichAdd;
        this.addString = description;
    }

    /**
     * Executes the parsed command
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String stringToReturn = "";
        switch (whichAdd) {
        case "todo":
            Todo todo = new Todo(addString);
            tasks.add(todo);
            stringToReturn = ("added new todo: " + todo);
            break;
        case "event":
            String[] eventInfo = addString.split(" /at ");
            try {
                LocalDate date = LocalDate.parse(eventInfo[1]);
                Event event = new Event(eventInfo[0], date);
                tasks.add(event);
                stringToReturn = ("added new event: " + event);
            } catch (Exception e) {
                throw new DukeException("Invalid event");
            }
            break;
        case "deadline":
            String[] deadlineInfo = addString.split(" /by ");
            try {
                LocalDate date = LocalDate.parse(deadlineInfo[1]);
                Deadline deadline = new Deadline(deadlineInfo[0], date);
                tasks.add(deadline);
                stringToReturn = ("added new deadline: " + deadline);
            } catch (Exception e) {
                throw new DukeException("Invalid deadline");
            }
            break;
        }
        storage.saveFile(tasks);
        return stringToReturn;
    }

}