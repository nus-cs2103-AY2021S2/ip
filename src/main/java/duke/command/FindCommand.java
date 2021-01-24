package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;

public class FindCommand extends Command {

    private final String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Invalid argument: Argument field cannot be empty.");
        } else {
            String subStr = command.substring(5);
            if (StringParser.isBlank(subStr)) {
                throw new DukeException("Invalid argument: Content field is blank");
            } else {
                TaskList tempList = new TaskList();
                for (int i = 0; i < list.getSize(); i++) {
                    if (list.getJob(i).getDescription().contains(subStr)) {
                        tempList.addJob(list.getJob(i));
                    }
                }
                StringBuilder resultStr = new StringBuilder();
                for (int i = 0; i < tempList.getSize(); i++) {
                    resultStr.append(StringParser.newLiner((i + 1) + "."
                            + tempList.getJob(i).toString(), length));
                }
                if (list.getSize() == 0) {
                    System.out.print("No result found\n");
                } else {
                    System.out.print(resultStr.toString());
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
