import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        String response = "";
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("Please give me a keyword to search for!");
        } else if (inputs.length > 2) {
            throw new DukeException("Sorry! I can't search for more than one keyword.");
        } else { }
        String keyword = inputs[1];
        List<Task> toPrint = tasks.findKeyword(keyword);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            response = response + i + "." + toPrint.get(i - 1).toString() + "\n";
        }
        return "Here are the matching tasks in your list:\n" + response;
    }
}
