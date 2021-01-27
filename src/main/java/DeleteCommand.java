public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        int taskNo = Integer.parseInt(String.valueOf(fullCommand.charAt(7))) - 1;
        String toPrint = tasks.remove(taskNo);
        Ui.print(
                Aligner.align("Alright! I've removed this task:"));
        Ui.print(
                Aligner.align(toPrint));
        Ui.print(
                Aligner.align("Now you have a whopping "
                        + tasks.size()
                                + " task(s) in the list."));
        Ui.printLine();
    }
}
