package duke.command;
import java.util.List;
import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Task;
import duke.tasktype.Deadline;
public class CDeadline implements ICommand {
    private String input;
    public CDeadline(String command){
        this.input = command;
    }
    @Override
    public boolean isBye() {
        return false;
    }
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