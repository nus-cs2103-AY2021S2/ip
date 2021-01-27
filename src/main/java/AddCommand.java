public class AddCommand extends Command {
    public AddCommand(String fullCommand, String action) {
        super(fullCommand, action);
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        Ui.printLine();
        String[] inputs = this.fullCommand.split(" ");
        String action = inputs[0];
        if (action.equals("todo")) {
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a todo cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this todo:"));
            String toPrint = tasks.add(fullCommand, "todo");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else if (action.equals("deadline")) { //input format yyyy-mm-dd tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a deadline cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this deadline:"));
            String toPrint = tasks.add(fullCommand, "deadline");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else if (action.equals("event")) { //input format: yyyy-mm-dd tttt-tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of an event cannot be empty.");
            }
            Ui.print(Aligner.align("Sure! I've added this event:"));
            String toPrint = tasks.add(fullCommand, "event");
            Ui.print(Aligner.align(toPrint));
            Ui.print(Aligner.align("Now you have a whopping " + tasks.size() + " task(s) in the list."));
        } else {
            throw new DukeException("OOPS! Sorry, I have no idea what that means :(");
        }
        Ui.printLine();
    }
}
