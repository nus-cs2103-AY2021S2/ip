import java.io.IOException;

public class DoneCommand extends Command {
    private String[] checkCommands;
    public DoneCommand(String[] checkCommands) {
        this.checkCommands = checkCommands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (checkCommands.length == 1 || !isNumber(checkCommands[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(checkCommands[1]);
        if (num > 0 && num <= tasks.size()) {
            tasks.get(num - 1).markAsDone();
            //TODO: Output to UI
            System.out.println("\t____________________________________________________________\n"
                    + "\tNice! I've marked this task as done:\n\t\t"
                    + tasks.get(num - 1).toString() + "\n"
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
