package com.nus.duke.command;

import com.nus.duke.data.Todo;

public class TodoCommand extends Command {

    public static final String COMMAND = "todo";
    public static final String EMPTY_DESCRIPTION_MESSAGE = "Description for a Todo cannot be empty";
    public static final String USAGE_MESSAGE = COMMAND + ": Adds a new todo to the task list\n"
            + "Usage: todo [description]\n"
            + "Example: todo read books";
    public static final String SUCCESS_MESSAGE_TEMPLATE = "Added a new Todo:\n%s\nNow you have %d tasks in the list.";

    private final Todo todo;

    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    @Override
    public String execute() {
        int size = this.taskList.addTask(this.todo);
        return String.format(SUCCESS_MESSAGE_TEMPLATE, this.todo, size);
    }

    public static Command parseArguments(String arguments) {
        if (arguments != null && !arguments.trim().isEmpty()) {
            return new TodoCommand(new Todo(arguments.trim()));
        } else {
            return new IncorrectCommand(EMPTY_DESCRIPTION_MESSAGE + "\n" + USAGE_MESSAGE);
        }
    }
}
