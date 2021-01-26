package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import ui.Snomio;

public class AddCommand extends Command{
    public AddCommand(CommandEnum type) {
        super(type);
    }

    @Override
    public void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        Task task;
        if(type == CommandEnum.TODO){
            String content = snomio.readContent(type.name());
            task = new Todo(content);
        }else{
            String[] dlArr = snomio.readContentWithDate(type.name(), "/by");
            task = new Deadline(dlArr[0], dlArr[1]);
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
