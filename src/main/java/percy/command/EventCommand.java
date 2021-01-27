package percy.command;

import percy.task.Task;
import percy.task.TaskList;

import percy.task.Event;
import percy.ui.UserInterface;
import percy.storage.Storage;

import java.io.IOException;

public class EventCommand extends Command {
    private String eventDescription;
    private String date;

    public EventCommand(String eventDescription, String date) {
        super(false);
        this.eventDescription = eventDescription;
        this.date = date;
    }


    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) throws IOException { // Is task list Immutable?
        Task event = new Event(eventDescription, date);

        taskList.addTaskToList(event);
        storage.save(taskList);
        return UserInterface.makeAddMsg(event, taskList);
    }
}
