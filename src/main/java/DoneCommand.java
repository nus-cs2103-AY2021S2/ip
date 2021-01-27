public class DoneCommand extends Command {
    public DoneCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        if (inputs.length == 1) {
            throw new DukeException("OOPS! Please tell me what to mark as done!");
        }
        int taskNo = Integer.parseInt(String.valueOf(fullCommand.charAt(5))) - 1;
        String toPrint = tasks.makeDone(taskNo);
        Ui.print(
                Aligner.align("Good job! I've marked this task as done:"));
        Ui.print(
                Aligner.align(toPrint));
        Ui.printLine();
    }
}
