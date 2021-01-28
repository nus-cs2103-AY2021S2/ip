package main.java;

public class ToDoCommand implements Command{
    String toDoDescription;

    public ToDoCommand(String s){
        toDoDescription = s;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task toDoTask = dukeTaskList.addToDoTask(toDoDescription);
        dukeUi.showAddedTask(toDoTask, dukeTaskList.getNumberOfTasks());
    }
}
