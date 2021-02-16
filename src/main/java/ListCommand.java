/**
 * Handles the list command, where the user wants to display all the tasks currently in the list.
 */
public class ListCommand extends Command {
    private int listCounter;

    ListCommand(String input, String[] parts, TaskList tasks, int listCounter) {
        super(input, parts, tasks);
        this.listCounter = listCounter;
    }

    /**
     * Returns a string representation of one or more Tasks that are stored in the list.
     *
     * @return A string representation of all the Tasks currently stored in the list.
     */
    @Override
    public String execute() throws DukeException {

        if (parts.length > 1) {
            throw new DukeException("Too many arguments. Provide just one number");
        }
        assert tasks != null : "tasks cannot be null";
        StringBuilder listStringBuilder = new StringBuilder();
        for (Task t : TaskList.getTasklist()) {
            listStringBuilder.append(listCounter).append(".").append(t.toString());
            listStringBuilder.append("\n");
            listCounter++;
        }
        return Ui.showListText() + listStringBuilder.toString();
    }
}
