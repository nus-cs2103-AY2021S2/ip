package commands;

import exceptions.*;

import tasks.TaskList;

import ui.Ui;

import storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;
    public abstract boolean isExit();
}
