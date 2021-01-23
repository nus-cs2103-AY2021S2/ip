public class ToDoCommand extends Command {
    private String desc = "";

    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute() throws DukeCommandException {
        try {
            ToDo toDo = taskManager.addToDo(this.desc);
            ui.printAddMsg(toDo, taskManager.getTasksSize());
        } catch(DukeTaskException e) {
            throw new DukeCommandException("todo", desc, e.getMessage());
        }
    }
}
