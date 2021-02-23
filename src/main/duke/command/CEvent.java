package main.duke.command;

import main.duke.DukeException;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;
import main.duke.tasktype.Event;
import main.duke.tasktype.Task;
import java.util.List;

public class CEvent implements ICommand {
    private String input;
    public CEvent(String command){
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
