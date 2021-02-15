/**
 * Class that handles the Done command. Marking a task as complete in the
 * current list of existing entries.
 */
public class DoneCommand extends Command{

    private TaskList tasks;
    private int value;

    public DoneCommand(TaskList tasks, int value) {
        this.tasks = tasks;
        this.value = value;
    }

    public Result execute() throws DukeException {
        String output = "";
        if (value <= 0 || value > tasks.getStorage().size()) {
            throw new DukeException("No such list item.");
        }
        tasks.getStorage().get(value - 1).setDone();
        output += "ALRIGHT. THIS TASK HAS BEEN MARKED AS COMPLETE\n"
                + tasks.getStorage().get(value - 1)
                + "\n";
        assert output != "" : "output should not be empty.";
        Result result = new Result(output);
        return result;
    }
}
