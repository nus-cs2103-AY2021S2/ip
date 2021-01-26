public class DeleteCommand extends Command {

    public DeleteCommand(String input, TaskList tl) {
        super(input,tl);
    }

    @Override
    public TaskList execute(TaskList tasks, UI ui, DataStorage storage) throws DukeException {
        tasks.deleteTask(Integer.parseInt(input)-1);
        storage.save(tasks.getTaskListArray());
        return tasks;
    }
}
