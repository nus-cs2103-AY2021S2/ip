package main.java.command;

import main.java.classes.DuckieException;
import main.java.classes.Storage;
import main.java.classes.TaskList;
import main.java.classes.Ui;

/**
 * Abstract Command class with methods execute and isEnd. Inherited by all keyword classes.
 */
public abstract class Command {

    /**
     * Execute method.
     * @param lst a TaskList object containing Task Objects.
     * @param ui a Ui object.
     * @param storage a storage object.
     * @throws DuckieException if user enters commands besides accepted ones.
     */
    public abstract void execute(TaskList lst, Ui ui, Storage storage) throws DuckieException;

    /**
     * Method to determine whether to exit program.
     * @return Returns true or false.
     */
    public abstract boolean isEnd();
}