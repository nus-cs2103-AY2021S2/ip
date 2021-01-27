import java.util.List;

public class FindCommand extends Command {
    public FindCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("Please give me a keyword to search for!");
        } else if (inputs.length > 2) {
            throw new DukeException("Sorry! I can't search for more than one keyword.");
        } else { }
        Ui.print(Aligner.align("Here are the matching tasks in your list:"));
        String keyword = inputs[1];
        List<Task> toPrint = tasks.findKeyword(keyword);
        for (int i = 1; i < toPrint.size() + 1; i++) {
            Ui.print(Aligner.align(i + "." + toPrint.get(i - 1).toString()));
        }
        Ui.printLine();
    }
}
