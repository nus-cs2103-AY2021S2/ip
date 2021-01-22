package com.nus.duke.command;

import com.nus.duke.data.TaskList;

public abstract class Command {

    protected TaskList taskList;

    public void setContext(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract String execute();
}
