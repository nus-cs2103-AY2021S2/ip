package main.java;

public class DeleteCommand implements Command {
    int taskDeleteInt;
    public DeleteCommand(int i){
        taskDeleteInt = i;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage){
        try {
            Task deletedTask = dukeTaskList.deleteTask(taskDeleteInt);
            dukeUi.showTaskDeleted(deletedTask, dukeTaskList.getNumberOfTasks());
        }catch (DukeException e){
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}

