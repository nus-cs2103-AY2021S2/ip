public abstract class Command {
    String description;
    int index;

    public Command() {
    }

    public Command(int index) {
        this.index = index;
    }

    public Command(String description) {
        this.description = description;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
