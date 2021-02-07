package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.Locale;

public class FindCommand extends Command {
    private static TaskList tempList;

    public FindCommand(String description) {
        super(description);
    }

    /**
     * Finds tasks in the taskList based on keyword given
     *
     * @param taskList, the list of tasks
     * @param ui, the UI object
     * @param storage, the storage object
     * @return String which are a list of tasks that are found with the keyword
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputList = description.trim().split(" ", 2);
        String toFind = inputList[1].trim().toLowerCase();
        tempList = new TaskList();

        for (int i = 0; i< taskList.getSize(); i++) {
            Task toAdd = taskList.getTask(i);

            if(toAdd.getInfo().contains(toFind)) {
                tempList.addTask(toAdd);
            }
        }

        if (tempList.getSize() == 0) {
            String message = "Master, I'm unable to find such a task."
            ui.formatAndPrintType1(message);
            return message;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Master, I have found "
                      + tempList.getSize()
                      + " such tasks:\n");

            for (int j = 0; j < tempList.getSize(); j++) {
                Task currentTask = tempList.getTask(j);
                sb.append((j + 1) + "." + currentTask.toString() + "\n");
            }
            ui.formatAndPrintType1(sb.toString());
            return sb.toString();
        }
    }

    @Override
    public boolean isEndOfProgram() {
        return false;
    }
}
