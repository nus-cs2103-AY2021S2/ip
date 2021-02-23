package duke.command;

import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Event;
import duke.tasktype.Task;
import java.util.List;

public class CEvent implements ICommand {
    private String input;
    public CEvent(String command){
        this.input = command;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        int endPoint = input.indexOf("/") - 1;
        String text = input.substring("event".length() + 1, endPoint);
        String dateTime = input.substring(endPoint + 5);
        Task event = new Event(text, dateTime);
        taskList.add(event);

        storage.addText(event.convertToFile());
        return ui.showTaskAdded(event.getStatus(), taskList);
    }
}
