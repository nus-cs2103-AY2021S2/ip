package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.data.DataStorage;

import java.io.IOException;

/**
 * Create list command class
 */
public class ListCommand extends Command {

    /**
     * Constructor to create delete command object
     */

    public ListCommand(String input) {
        super(input);
    }

    /** Display all the task found in list
     * @return
     */
    @Override
    public String execute() throws DukeException, IOException {
        tasklist.setTaskArraylist(storage.loadFile());
        String allTaskMsg =  tasklist.showAllTask("list");

        if(allTaskMsg.isEmpty()){
            return ui.printNoTaskMessage();
        }else{
            return allTaskMsg;
        }
    }

}
