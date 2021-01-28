public class CommandToDo extends Command {
    private final String description;

    public CommandToDo(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(description);
        tasks.addTask(toDo);
        storage.save(tasks);
        ui.printCommand(this);
        ui.printTask(toDo);
    }

    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new ToDo: ";
    }
}
