package main.java.command;

import main.java.TaskManager;
import main.java.Ui;
import main.java.entity.Task;
import main.java.entity.Todo;

/**
 * Command to add a Todo Task
 */
public class AddTodoCommand extends AddCommand {
    private String todoName;

    /**
     * Creates a Command to add a Todo Task
     * @param todoName name of Todo Task to be added
     */
    public AddTodoCommand(String todoName) {
        super();
        this.todoName = todoName;
    }


    /**
     * execute add task command
     * call TaskManager to add the particular task
     * and Ui to display add message
     * @param tm Associated TaskManager
     * @param ui Associated Ui
     * @return execution result string
     */
    @Override
    public String execute(TaskManager tm, Ui ui) {
        Task task = new Todo(todoName);
        tm.addTask(task);
        return ui.displayAfterAdd(tm.size(), task);
    }
}
