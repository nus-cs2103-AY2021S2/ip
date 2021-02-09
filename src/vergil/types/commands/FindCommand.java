package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

public class FindCommand extends Command {
    private String keywords;
    private TaskList resultList;

    public FindCommand(String... args) {
        super(CommandType.FIND, args);
    }


    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        keywords = getArgument(0);

        resultList = taskList.find(keywords);

        if (resultList.getLength() > 0) {
            return ui.getFoundMessage(resultList);
        } else {
            return ui.getNotFoundMessage();
        }
    }
}
