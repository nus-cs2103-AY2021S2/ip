package duke;

public class FindCommand extends Command {
    String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        TaskList tempTL = new TaskList();
        for (Task t : tl.getList()) {
            String taskName = t.getName();
            if (taskName.contains(pattern)) {
                tempTL.add(t);
            }
        }
        ui.printFoundTasks(tempTL);
    }
}