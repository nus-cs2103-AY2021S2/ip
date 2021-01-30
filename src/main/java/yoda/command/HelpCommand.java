package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

public class HelpCommand extends Command {

    public HelpCommand(String[] details) {
        super(details);
        taskType = Input.valueOf(details[0]);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskType == Input.ERROR) {
            System.out.println("That is not a valid command! Here's some help!");
        }
        ui.showHelp();
    }
}
