import java.io.IOException;
import java.util.ArrayList;

/**
 * When the user inputs a ToDo task, the ToDoCommand is returned
 */
public class ToDoCommand extends Command {
    private String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public String executeCommand(Ui ui, Storage storage, ArrayList<Task> taskList) throws DukeException {
        String input = command.trim();
        String[] strArray = input.split(" ", 2);
        String cmd = strArray[0];

        if (input.equalsIgnoreCase("todo")) {
            throw new DukeException("Could you please specify your task? :)");
        }
        String cmdTask = strArray[1];
        ToDo tempTask = new ToDo(cmdTask);
        taskList.add(tempTask);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ui.showTaskAdded(tempTask);
    }

    public boolean isRunning() {
        return true;
    }
}