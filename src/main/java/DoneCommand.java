package main.java;

public class DoneCommand implements Command {
    int taskDoneInt;
    public DoneCommand(int i){
        taskDoneInt = i;
    }
    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage){
        try{
            Task doneTask = dukeTaskList.checkTaskAsDone(taskDoneInt);
            dukeUi.showTaskDone(doneTask);
        }catch (DukeException e){
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}
