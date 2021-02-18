public class CommandToDo extends Command {
    private final String description;

    public CommandToDo(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(description);
        tasks.addTask(toDo);
        storage.save(tasks);
        return this.toDukeOutput() + "\n" + toDo.toString();
    }

    @Override
    public String toDukeOutput() {
        return "Roger that boss, I've added a new ToDo: ";
    }
}
