import java.util.ArrayList;

/**
 * When the user inputs a Note task, the NoteCommand is returned.
 */
public class NoteCommand extends Command {
    private String command;

    public NoteCommand(String command){
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        if (input.equalsIgnoreCase("note")) {
            throw new DukeException("Could you please specify your note? :)");
        }
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];
        String cmdTask = strArray[1];

        Note tempTask = new Note(cmdTask);
        taskList.add(tempTask);
        return ui.showTaskAdded(tempTask);
    }

    public boolean isRunning() {
        return true;
    }
}
