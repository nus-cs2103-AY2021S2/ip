public class ListCommand extends Command {
    private int listCounter;

    ListCommand(String input, String[] parts, TaskList tasks, int listCounter) {
        super(input, parts, tasks);
        this.listCounter = listCounter;
    }

    @Override
    public String execute() throws InsufficientArgumentsException {
        StringBuilder listStringBuilder = new StringBuilder();
        for (Task t : TaskList.getTasklist()) {
            listStringBuilder.append(listCounter).append(".").append(t.toString());
            listStringBuilder.append("\n");
            listCounter++;
        }
        return listStringBuilder.toString();
    }
}
