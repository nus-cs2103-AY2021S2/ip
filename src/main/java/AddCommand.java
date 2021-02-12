import java.time.LocalDate;

public class AddCommand extends Command {

    AddCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    public AddCommand() {
        super();
    }

    /**
     * Returns a string representation of the Task after it has been added to the list.
     *
     * @return A string representation of the Task added to the list.
     * @throws InsufficientArgumentsException If no arguments are provided.
     */
    @Override
    public String execute() throws InsufficientArgumentsException {
        if (parts.length == 1) {
            throw new InsufficientArgumentsException("Insufficient arguments provided");
        }
        if (input.equals("todo")) {
            return new TodoCommand(input, parts, tasks).getString();
        } else {
            int slashIndex = getSlashIndex();
            String taskString = getTaskString(slashIndex);
            String dateString = getDateString(slashIndex);
            LocalDate date = LocalDate.parse(dateString);
            if (input.equals("deadline")) {
                return new DeadlineCommand(taskString, date, tasks).getString();
            } else {
                return new EventCommand(taskString, date, tasks).getString();
            }
        }
    }

    public String getTaskString(int slashIndex) {
        StringBuilder str = new StringBuilder();
        for (int j = 1; j < slashIndex; j++) {
            str.append(" ");
            str.append(parts[j]);
        }
        return str.toString();
    }

    public int getSlashIndex() {
        int slashIndex = 0;
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains(Character.toString('/'))) {
                slashIndex = i;
            }
        }
        return slashIndex;
    }

    public String getDateString(int slashIndex) {
        StringBuilder byStringBuilder = new StringBuilder();
        for (int k = slashIndex + 1; k < parts.length; k++) {
            if (k != slashIndex + 1) {
                byStringBuilder.append(" ");
            }
            byStringBuilder.append(parts[k]);
        }
        return byStringBuilder.toString();
    }
}
