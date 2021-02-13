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
public abstract class Command {

    public abstract CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

//    /**
//     * Executes the command.
//     * Requires the core functions of the
//     * Duke chat bot to be supplied as arguments.
//     *
//     * @param tasks Core TaskList object.
//     * @param ui Core Ui object.
//     * @param storage Core Storage object.
//     * @throws DukeException If unknown command was specified
//     */
//    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
//        try {
//            CommandsEnum ce = CommandsEnum.valueOf(this.commandType.toUpperCase());
//            switch (ce) {
//            case USAGE:
//                return ui.showUsage();
//            case LIST:
//                return this.listCommand(tasks, ui);
//            case FIND:
//                return this.findTasks(tasks, ui);
//            case TODO:
//            case DEADLINE:
//            case EVENT:
//                return this.addTask(tasks, ui);
//            case DONE:
//                return this.markDone(tasks, ui);
//            case DELETE:
//                return this.deleteTask(tasks, ui);
//            case SAVE:
//                return this.saveTasks(tasks, ui, storage);
//            default:
//                throw new DukeException("I'm not trained with these commands yet...");
//            }
//        } catch (IllegalArgumentException e) {
//            throw new DukeException("I'm not trained with these commands yet...");
//        }
//    }
