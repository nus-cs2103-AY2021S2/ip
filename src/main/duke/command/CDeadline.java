package main.duke.command;
import java.util.List;
import main.duke.DukeException;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;
import main.duke.tasktype.Task;
import main.duke.tasktype.Deadline;
public class CDeadline implements ICommand {
    private String input;
    public CDeadline(String command){
        this.input = command;
    }

    /**
     * Check if it is the bye command
     * @return false as it is not the bye command
     */
    @Override
    public boolean isBye() {
        return false;
    }
    /**
     *
     * @param ui UI object that deal with the program output
     * @param dukeList Collection of tasks in list form
     * @param storage Storage object that deal with the file system
     */
    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException{
        List<Task> taskList = dukeList.getTaskList();
        int endPoint = input.indexOf("/") - 1;
        String text = input.substring("deadline".length() + 1, endPoint);
        String dateTime = input.substring(endPoint + 5);
        Task deadline = new Deadline(text, dateTime);
        taskList.add(deadline);

        storage.addText(deadline.convertToFile());
        return ui.showTaskAdded(deadline.getStatus(), taskList);

    }


}