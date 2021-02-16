package duke.command;

import duke.exception.DukeException;

import java.io.IOException;

/**
 * Command class to list task in the task list
 */
public class ListCommand extends Command {

    /**
     * Create a list command
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Display all the tasks found in the task list
     * @return String message upon successful execution of the command
     */
    @Override
    public String execute() throws DukeException, IOException {
        tasklist.setTaskArraylist(storage.loadFile());
        String allTaskMsg =  tasklist.showAllTask("list");
        System.out.println("all list size is: " + tasklist.getTaskListArray().size());

        if(allTaskMsg.isEmpty()){
            return ui.DisplayNoTaskMessage();
        }else{
            return allTaskMsg;
        }
    }

}
