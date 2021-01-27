public class ListCommand extends Command {

    public ListCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 0) {
            throw new DukeException("OOPS! Please enter a command or say bye so I can go back to sleep!");
        }
        Ui.print(Aligner.align("Here are the tasks in your list:"));
        tasks.listing();
        Ui.printLine();
    }
}
