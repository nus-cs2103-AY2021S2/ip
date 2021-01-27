package duke.command;

import duke.task.Event;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class AddEventCommand extends Command{

    public AddEventCommand(String userMessage){
        super(userMessage);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        int spaceIndex = userMessage.indexOf(" ");
        int dateIndex = userMessage.indexOf('/');
        if (dateIndex == -1) {
            throw new DukeException("OOPS!!! I can't find your event time.");
        }
        if (spaceIndex == -1 || dateIndex - spaceIndex == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String eventName = userMessage.substring(spaceIndex + 1,dateIndex - 1);
        String at = userMessage.substring(dateIndex + 4);
        Event event;

        try{
            event = new Event(eventName,at);
        } catch (Exception e){
            throw new DukeException("OOPS! The input format is wrong! Should be YYYY-MM-DD HH:MM");
        }

        taskList.addTasks(event);

        builder.append("[" + event.getStatusIcon() + "] " + event.toString());
        builder.append("\nNow you have " + Integer.toString(taskList.getNumOfTasks()) + " tasks in the list.");
        String botMessage = builder.toString();
        ui.display(botMessage);
    }
}
