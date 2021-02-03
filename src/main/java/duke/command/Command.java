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
        USAGE, LIST, FIND,
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            CommandsEnum ce = CommandsEnum.valueOf(this.commandType.toUpperCase());
            switch (ce) {
            case USAGE:
                return ui.showUsage();
            case LIST:
                return this.listCommand(tasks, ui);
            case FIND:
                return this.findTasks(tasks, ui);
            case TODO:
            case DEADLINE:
            case EVENT:
                return this.addTask(tasks, ui);
            case DONE:
                return this.markDone(tasks, ui);
            case DELETE:
                return this.deleteTask(tasks, ui);
            case SAVE:
                return this.saveTasks(tasks, ui, storage);
            default:
                throw new DukeException("I'm not trained with these commands yet...");
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
    public String listCommand(TaskList tasks, Ui ui) {
        return ui.say(tasks.showList());
    }

    /**
     * Filters the task list to find tasks
     * that matches a certain keyword.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     */
    public String findTasks(TaskList tasks, Ui ui) {
        return ui.say(tasks.findTasks(this.commandArgs[0]));
    }

    /**
     * Adds task into the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException  If invalid task type or arguments specified.
     */
    public String addTask(TaskList tasks, Ui ui) throws DukeException {
        return ui.say(tasks.addTask(this.commandType, this.commandArgs));
    }

    /**
     * Marks specific task from the task list as done.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException If invalid index specified.
     */
    public String markDone(TaskList tasks, Ui ui) throws DukeException {
        return ui.say(tasks.markDone(this.commandArgs[0]));
    }

    /**
     * Deletes specific task from the task list.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @throws DukeException If invalid index specified.
     */
    public String deleteTask(TaskList tasks, Ui ui) throws DukeException {
        return ui.say(tasks.deleteTask(this.commandArgs[0]));
    }

    /**
     * Saves tasks from the task list into the disk.
     *
     * @param tasks Core TaskList object.
     * @param ui Core Ui object.
     * @param storage Core Storage object.
     * @throws DukeException If the file cannot be found.
     */
    public String saveTasks(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.say(storage.save(tasks.getList()));
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
