public class DeleteCommand extends Command {

    DeleteCommand(String input, String[] parts, TaskList tasks) {
        super(input, parts, tasks);
    }

    /**
     * Returns a string representation of the Task after it is deleted from the list.
     *
     * @return A string representation of the Task deleted.
     * @throws InsufficientArgumentsException If more than one argument is provided.
     */
    @Override
    public String execute() throws InsufficientArgumentsException {
        if (parts.length > 2) {
            throw new InsufficientArgumentsException("Wrong arguments");
        }
        int index = Integer.parseInt(parts[1]);
        assert index >= 1 : "Value must be at least 1";
        Task toBeRemoved = tasks.get(index - 1);
        tasks.remove(index - 1);
        return "Noted. I've removed this task:\n" + toBeRemoved.toString();
    }
}
