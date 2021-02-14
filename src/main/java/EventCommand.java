import java.util.ArrayList;

/**
 * When the user inputs an Event task, the EventCommand is returned
 */
public class EventCommand extends Command {
    private String command;

    public EventCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("event")) {
            throw new DukeException("Could you please specify your task? :)");
        }
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String cmdTask = strArray[1];

        if (!strArray[1].contains("/at")) {
            throw new DukeException("Uh oh! Please specify a timing using /at.");
        }
        String[] tempStrArray = cmdTask.split("/at", 2);
        Event tempTask = new Event(tempStrArray[0], tempStrArray[1]);
        taskList.add(tempTask);
        return ui.showTaskAdded(tempTask);
    }

    public boolean isRunning() {
        return true;
    }
}
