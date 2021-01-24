package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i < list.getSize(); i++) {
            resultStr.append(StringParser.newLiner((i + 1) + "."
                    + list.getJob(i).toString(), length));
        }
        if (list.getSize() == 0) {
            System.out.print("List is empty\n");
        } else {
            System.out.print(resultStr.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Test usage: this is a LIST command";
    }
}
