package duke.command;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.Ui;

class ListCommand extends Command{

    ListCommand() {
        super(null, null, null, null, false);
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getList());
    }
}
