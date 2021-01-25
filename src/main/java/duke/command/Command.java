package duke.command;

import duke.TaskList.TaskList;
import duke.UI.UI;
import duke.data.DataStorage;
import duke.exception.DukeException;

public class Command {

    protected static String input;
    protected static boolean isExit;

    public Command (String input, TaskList taskList){
        this.input = input;
        this.isExit = false;
    }

    public TaskList execute(TaskList tasklist, UI ui, DataStorage storage) throws DukeException {
        if(this.input.equals("bye")){
            Command ec = new ExitCommand();
            ec.execute(tasklist,ui,storage);
        }
        return tasklist;

    }

    public static boolean isExit(){
        return isExit;
    }






}
