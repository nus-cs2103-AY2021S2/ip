package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.*;
import ui.Snomio;

public class AddCommand extends Command{
    public AddCommand(CommandEnum type) {
        super(type);
    }

    /**
     * Executes todo, deadline, event command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @throws SnomException   if command execution failed
     */
    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        Task task;
        if(type == CommandEnum.TODO){
            String content = snomio.readContent(type.name());
            task = new Todo(content);
        }else if(type == CommandEnum.DEADLINE){
            String[] dlArr = snomio.readContentWithDate(type.name(), "/by");
            task = new Deadline(dlArr[0], dlArr[1]);
        }else{
            String[] dlArr = snomio.readContentWithDate(type.name(), "/at");
            task = new Event(dlArr[0], dlArr[1]);
        }
        taskList.addTask(task);
        snomio.showTaskAdded(task, taskList.getSize());
        storage.saveFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
