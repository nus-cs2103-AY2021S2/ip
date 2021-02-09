package duke;

public class FindCommand extends Command {
    private String pattern;

    /**
     * Constructs new find command.
     * @param pattern String pattern to find in task list.
     */
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Finds all taskName that contains pattern.
     * Matching tasks would then be printed out.
     *
     * @param tl Task list for Duke.
     * @param ui User Interface for Duke.
     * @param storage Storage to save list to drive.
     * @return String of all task that matches pattern.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        TaskList tempTL = new TaskList();
        for (Task t : tl.getList()) {
            String taskName = t.getName();
            if (taskName.contains(pattern)) {
                tempTL.add(t);
            }
        }
        return ui.printFoundTasks(tempTL);
    }
}
