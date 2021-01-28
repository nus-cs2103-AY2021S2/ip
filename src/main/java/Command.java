import java.util.Scanner;

public class Command {

    protected String commandType;
    protected String[] commandArgs;
    protected boolean toExit;

    public enum CommandsEnum {
        USAGE, LIST,
        TODO, DEADLINE, EVENT,
        DONE, DELETE, SAVE, BYE
    }

    public Command(String commandType, String[] commandArgs) {
        this.commandType = commandType;
        this.commandArgs = commandArgs;
        this.toExit = false;
    }

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

    public void listCommand(TaskList tasks, Ui ui) {
        ui.say(tasks.showList());
    }

    public void addTask(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.addTask(this.commandType, this.commandArgs));
    }

    public void markDone(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.markDone(this.commandArgs[0]));
    }

    public void deleteTask(TaskList tasks, Ui ui) throws DukeException {
        ui.say(tasks.deleteTask(this.commandArgs[0]));
    }

    public void saveTasks(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.say(storage.save(tasks.getList()));
    }

    public boolean isExit() {
        return toExit;
    }
}
