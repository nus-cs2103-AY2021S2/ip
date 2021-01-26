package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command{

    public ListCommand(){
        super("");
    }

    public void execute(Ui ui, Storage s, TaskList list) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            sb.append("\t");
            sb.append(i + 1).append(". ").append(list.getItem(i));
            if (i != list.getSize() - 1) sb.append("\n");
        }
        if (list.getSize() == 0) sb.append("\tYour list is empty!");
        this.reply = sb.toString();
        ui.reply(this.reply);
    }
}

