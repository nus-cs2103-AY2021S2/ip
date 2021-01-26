public abstract class Command {
    CommandEnum type;

    public Command(CommandEnum type){
        this.type = type;
    }

    public abstract void execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException;
    public abstract boolean isExit();
}
