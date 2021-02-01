package main.java.command;

import main.java.classes.DuckieException;
import main.java.classes.Storage;
import main.java.classes.TaskList;
import main.java.classes.Ui;

public abstract class Command {

    public abstract void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException;

    public abstract boolean isEnd();
}