package duke.command;

import duke.task.DeadlineTask;

public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(final String content) {
        super(new DeadlineTask(content.split("/by")[0].trim(), content.split("/by")[1].trim()));
    }
}
