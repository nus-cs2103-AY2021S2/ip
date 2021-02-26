package commands;

import common.Message;
import data.IDuke;
import data.exception.DukeIllegalArgumentException;
import data.task.Event;
import data.task.ITask;

public class EventCommand extends StoreCommand {

    private final String description;
    private final String time;

    private EventCommand(String description, String time, IDuke duke) {
        super(-1, duke);
        this.description = description;
        this.time = time;
    }

    public static EventCommand getEventCommand(String description, String time) {
        return new EventCommand(description, time, null);
    }

    private String handleEvent(String description, String time)
            throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of event cannot be empty!");
        }
        if (time.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of event cannot be empty!");
        }

        ITask task = Event.getEvent(description, time);
        storeTask(task);
        String output = "Got it. I've added this task:\n\t" + task.toString()
                + "\nNow you have " + duke.numOfTasks() + " task(s) in the list.";
        System.out.print(output);
        return output;
    }


    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleEvent(description, time);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new EventCommand(description, time, duke);
    }

}
