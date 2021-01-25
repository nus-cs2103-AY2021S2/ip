package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx) {
        super(false);
        this.idx = idx - 1;
    }

  
    /** 
     * Marks task at index idx of taskList as done
     * 
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markDone(idx);
    }
}