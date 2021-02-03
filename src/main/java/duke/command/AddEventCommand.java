package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Event;
import duke.exception.NoMeaningException;

public class AddEventCommand extends Command {
    public AddEventCommand() {}

    /**
     * Process command event given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        try {
            String commandContent = command.substring(6);
            String[] eventWords = commandContent.split("/at");
            String eventWord = eventWords[0];
            String eventTime = eventWords[1];

            String[] eventDateHours = eventTime.split(" ");
            LocalDate eventDate = LocalDate.parse(eventDateHours[1]);
            LocalTime eventHour = LocalTime.parse(eventDateHours[2]);
            Event event = new Event(eventWord, eventDate, eventHour);
            taskList.add(event);
            storage.saveTasks(taskList);
            return ui.getTaskFinally(event, taskList.size());

        }  catch (StringIndexOutOfBoundsException e) {
            return ui.getTaskFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a event cannot be empty.")
            );
        }
    }
}
