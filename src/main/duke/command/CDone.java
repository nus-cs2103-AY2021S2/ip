package duke.command;
import java.util.List;
import duke.DukeException;
import duke.DukeList;
import duke.Ui;
import duke.io.Storage;
import duke.tasktype.Task;
public class CDone implements ICommand{
    private String input;
    public CDone(String command){
        this.input = command;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    @Override
    public String run(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Task> taskList = dukeList.getTaskList();
        int index = Integer.parseInt(input.substring(5))-1;
        Task task = taskList.get(index);
        if(task == null){
            throw new DukeException("No such task");
        }
        String oldLine = task.convertToFile();
        task.setDone();
        String newLine = task.convertToFile();
        try{
            storage.changeText(oldLine,newLine);
        }
        catch (StringIndexOutOfBoundsException e){
            System.out.println("Line to replace cannot be found" + e.getMessage());
        }
        return ui.makeDone(task.getStatus());
    }
}
