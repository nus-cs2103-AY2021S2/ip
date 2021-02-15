public class DeleteCommand extends Command {

    private TaskList tasks;
    private int value;

    public DeleteCommand(TaskList tasks, int value) {
        this.tasks = tasks;
        this.value = value;
    }

    public Result execute() throws DukeException {
        String output = "";
        int sizeAfterDelete = tasks.getStorage().size() - 1;
        if (value <= 0 || value > tasks.getStorage().size()) {
            throw new DukeException("No such list item.");
        }
        output += "OK. TASK REMOVED.\n"
                + tasks.getStorage().get(value - 1)
                + "\n"
                + "Now you have "
                + sizeAfterDelete
                + " tasks in the list.";

        tasks.getDuplicateChecker().remove(tasks.getStorage().get(value - 1));
        tasks.getStorage().remove(value - 1);

        assert sizeAfterDelete >= 0 : "size cannot be negative.";
        assert output != "" : "output should not be empty.";
        Result result = new Result(output);
        return result;
    }
}