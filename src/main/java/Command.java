package main.java;

public interface Command {
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage);
}
