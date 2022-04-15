package project.command;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.Task;
import project.task.TaskList;
import project.task.Todo;

public class AddTodo extends AddTask {
    public AddTodo() {
        super();
    }

    public AddTodo(String userInput) {
        super(userInput);
    }

    @Override
    public Task loadFromStorage(String line) {
        String expression = Parser.parseParameter(line, "] ", 1);
        return new Todo(expression);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            String description = Parser.parseParameter(userInput, " ", 1);
            if (description.length() == 0) {
                throw new Exception("*! The task description cannot be empty :/");
            }
            Todo newTodo = new Todo(description);
            taskList.addTask(newTodo);
            this.saveTasks(taskList, storage);
            return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                    newTodo, taskList.getTotalNumberOfTasksUndone());
        } catch (IndexOutOfBoundsException e) {
            return ui.showFormatError(PrintedText.TODO_FORMAT);
        } catch (Exception e) {
            return ui.showFormatResponse(e.getMessage());
        }
    }
}
