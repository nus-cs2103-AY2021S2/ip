package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;
import main.java.entity.Todo;

public class AddTodoCommand extends AddCommand {
    private String todoName;
    public AddTodoCommand(String todoName) {
        super();
        this.todoName = todoName;
    }


    @Override
    public void execute(TaskManager tm, Ui ui) {
        Task task = new Todo(todoName);
        tm.addTask(task);
        ui.displayAfterAdd(tm.size(), task);
    }
}
