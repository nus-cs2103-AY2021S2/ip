public class DoneCommand extends Command {

    DoneCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    @Override
    public String execute() {
        int index = Integer.parseInt(parts[1]);
        assert index >= 1 : "Value must be at least 1";
        Task toMark = TaskList.getTasklist().get(index - 1);
        toMark.markAsDone();
        TaskList.getTasklist().get(index - 1).markAsDone();
        return toMark.toString();
    }
}
