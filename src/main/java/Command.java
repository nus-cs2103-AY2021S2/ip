public abstract class Command {
    abstract String[] run();
    abstract TaskList.Action getType();
}
