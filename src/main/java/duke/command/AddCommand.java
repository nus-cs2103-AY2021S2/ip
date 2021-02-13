package duke.command;

import java.io.IOException;

import duke.*;

public class AddCommand extends Command {
    private final Task taskToBeAdded;

    public AddCommand(Task tasktoBeAdded) {
        this.taskToBeAdded = tasktoBeAdded;
    }


    @Override
    public CommandResult execute(Ui ui, TaskList tasks, Storage storage) {
        tasks.add(taskToBeAdded);
        try {
            storage.saveTasks(tasks);
        } catch (IOException err) {
            System.out.println("Error in accessing storage from addCommand.execute...Check data/duke.txt");
            this.isExit = true;
        }
        String addMessage = ui.generateAddMessage(taskToBeAdded,tasks);
        return new CommandResult(addMessage,false);
    }

}
