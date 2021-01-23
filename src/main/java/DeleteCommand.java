import java.io.IOException;

public class DeleteCommand extends Command {
    private String[] checkCommands;
    public DeleteCommand(String[] checkCommands) {
        this.checkCommands = checkCommands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws
            DukeException, IOException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            Task deleted = tasks.get(num - 1);
            tasks.remove(num - 1);
            //TODO: Change output to UI
            System.out.println("\t____________________________________________________________\n"
                    + "\tNoted. I've removed this task:\n"
                    + "\t   " + deleted.toString() + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private boolean isNumber(String checkString) {
        try {
            Integer.parseInt(checkString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
