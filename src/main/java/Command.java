package main.java;
public abstract class Command {

    public abstract void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException;

    public abstract boolean isEnd();
}