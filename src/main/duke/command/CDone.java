package main.duke.command;
import java.util.List;
import main.duke.DukeException;
import main.duke.DukeList;
import main.duke.Ui;
import main.duke.io.Storage;
import main.duke.tasktype.Task;
public class CDone implements ICommand{
    private String input;
    public CDone(String command){
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
        }catch (NumberFormatException e){
            System.out.println("You have an illegal character" + e.getMessage());
        }
        return ui.makeDone(task.getStatus());
    }
}
