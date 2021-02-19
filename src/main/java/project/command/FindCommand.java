package project.command;

import project.common.PrintedText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage ... storage) {
        try {
            String[] searchTerms = Parser.parseParameter(userInput, " ", 1).split(",");
            TaskList matches = taskList.findTasks(searchTerms);

            if (matches.hasTasks()) {
                // todo: index of matched Tasks could follow index in original taskList
                String output = "  Here are the tasks that match your search:\n\n" + matches.toString();
                return ui.showFormatResponse(output);
            } else {
                return ui.showFormatResponse("  Welps, no tasks match your search...");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showFormatError(PrintedText.FIND_FORMAT);
        }
    }
}
