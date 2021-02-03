package dbot.command;

import dbot.ui.Ui;
import dbot.exception.DukeException;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;

public abstract class Command {
    protected String description;
    private boolean isDone;
    private int targetIndex = -1;

    public Command() {
        isDone = false;
    }

    public Command(String description) {
        this.description = description;
        isDone = false;
    }

    protected void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected int getTargetIndex() {
        return targetIndex;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExit() {
        return false;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract void quietExecute(TaskList tasks, Storage storage) throws DukeException;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Command) {
            Command other = (Command) o;
            boolean isSameClass = this.getClass() == other.getClass();
            boolean hasSameDescription = this.getDescription().equals(other.getDescription());
            boolean isSameDone = this.getIsDone() == other.getIsDone();
            return isSameClass && hasSameDescription && isSameDone;
        } else {
            return false;
        }
    }
}
