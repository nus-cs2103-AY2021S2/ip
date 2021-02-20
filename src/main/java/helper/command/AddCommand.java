package helper.command;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.ocpsoft.prettytime.nlp.PrettyTimeParser;

import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Commands for adds (todo..., deadline..., event...)
 */
public class AddCommand extends Command {

    private final String whichAdd;
    private final String addString;

    /**
     * Initialize the command
     * @param whichAdd
     * @param description
     */
    public AddCommand(String whichAdd, String description) {
        this.whichAdd = whichAdd;
        this.addString = description;
    }

    /**
     * Executes the parsed command
     * @param tasks
     * @param ui
     * @param storage
     * @return string to print
     * @throws DukeException
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
                LocalDate date = parseTime(eventInfo[1]);
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
                LocalDate date = parseTime(deadlineInfo[1]);
                Deadline deadline = new Deadline(deadlineInfo[0], date);
                tasks.add(deadline);
                stringToReturn = ("added new deadline: " + deadline);
            } catch (Exception e) {
                throw new DukeException("Invalid deadline");
            }
            break;
        default:
            throw new DukeException("Invalid add");
        }
        storage.saveFile(tasks);
        return stringToReturn;
    }


    /**
     * Helps parse the time
     * @param s with time info
     * @return date
     */
    private LocalDate parseTime(String s) {
        List<Date> dates = new PrettyTimeParser().parse(s);
        Date date = dates.get(0);
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

}
