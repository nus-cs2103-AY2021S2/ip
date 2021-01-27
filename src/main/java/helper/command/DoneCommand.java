package helper.command;


import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class DoneCommand extends Command {

    private String doneString;

    public DoneCommand(String s) {
        doneString = s;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(doneString) - 1;
        try {
            tasks.get(index).setDone(true);
            ui.dukePrint("Good! We finished task: " + doneString + ". " + tasks.get(index));
        } catch (Exception e) {
            ui.dukePrint("Uhhh.... Our list starts at 1..." +
                    " and ends at " + (tasks.size() + 1));
        }
    }

}
