package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command used by the Duke chat bot.
 * It is represented by a command type, followed by
 * a series of arguments.
 */
public class Command {

    protected String commandType;
    protected String[] commandArgs;
    protected boolean toExit;

    public enum CommandsEnum {
        USAGE, LIST,
        TODO, DEADLINE, EVENT,
        DONE, DELETE, SAVE, BYE
    }

    /**
     * Initialises the command by specifying
     * its type and their corresponding arguments.
     *
     * @param commandType Command type.
     * @param commandArgs Command arguments.
     */
    public Command(String commandType, String[] commandArgs) {
        this.commandType = commandType;
        this.commandArgs = commandArgs;
        this.toExit = false;
    }

    /**
     * Executes the command.
     * Requires the core functions of the
     * Duke chat bot to be supplied as arguments.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @throws DukeException If unknown command was specified
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            CommandsEnum ce = CommandsEnum.valueOf(this.commandType.toUpperCase());
            switch (ce) {
                case USAGE:
                    ui.showUsage();
                    break;
                case LIST:
                    this.listCommand(tasks, ui);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    this.addTask(tasks, ui);
                    break;
                case DONE:
                    this.markDone(tasks, ui);
                    break;
                case DELETE:
                    this.deleteTask(tasks, ui);
                    break;
                case SAVE:
                    this.saveTasks(tasks, ui, storage);
                    break;
                case BYE:
                    this.toExit = ui.toExit();
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm not trained with these commands yet...");
        }
    }

    /**
     * Lists all the tasks information currently
     * handled by the Duke chat box.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     */
    public void listCommand(TaskList tasks, Ui ui) {
        ui.say(tasks.showList());
    }

    /**
     * Adds task into the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException  If invalid task type or arguments specified.
     */
    public void addTask(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.addTask(this.commandType, this.commandArgs));
    }

    /**
     * Marks specific task from the task list as done.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException If invalid index specified.
     */
    public void markDone(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.markDone(this.commandArgs[0]));
    }

    /**
     * Deletes specific task from the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException If invalid index specified.
     */
    public void deleteTask(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.deleteTask(this.commandArgs[0]));
    }

    /**
     * Saves tasks from the task list into the disk.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @throws DukeException If the file cannot be found.
     */
    public void saveTasks(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.say(storage.save(tasks.getList()));
    }

    /**
     * Checks if chat bot is required to exit.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return toExit;
    }
}
