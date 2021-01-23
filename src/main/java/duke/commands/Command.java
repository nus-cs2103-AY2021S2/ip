package duke.commands;

import duke.exceptions.*;

import duke.tasks.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;
    public abstract boolean isExit();
}
