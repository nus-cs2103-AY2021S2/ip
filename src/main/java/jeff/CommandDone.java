package jeff;

public class CommandDone extends Command {

    CommandDone (String main) {
        super(main);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        try {
            int taskIndex = Integer.parseInt(mainInfo) - 1;
            Task doneTask = tasks.getTask(taskIndex);
            doneTask.setDone();
            return "Nice! I've marked this task as done:\n" + doneTask;
        } catch (NumberFormatException e) {
            throw new JeffException("indicate task number as an integer");
        } catch (IndexOutOfBoundsException e) {
            throw new JeffException("task number does not exist");
        }
    }
}
