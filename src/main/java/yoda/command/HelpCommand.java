package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

/**
 * HelpCommand class that handles helping the user navigate the Yoda chatbot
 * and a child of the Command class.
 */
public class HelpCommand extends Command {
    /**
     * Creates a HelpCommand object.
     * @param details Details of HelpCommand object.
     */
    public HelpCommand(String[] details) {
        super(details);
        commandType = CommandType.valueOf(details[0]);
    }

    /**
     * Shows the list of commands available to the user and the valid inputs accepted by
     * the Yoda chatbot.
     * @param taskList TaskList associated with the HelpCommand being executed.
     * @param ui Ui associated with the HelpCommand being executed.
     * @param storage Storage associated with the HelpCommand being executed.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (commandType == CommandType.ERROR) {
            System.out.println("That is not a valid command! Here's some help!");
        }
        ui.showHelp();
    }
}
