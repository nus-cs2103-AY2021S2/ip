package duke.command;

import duke.task.*;

import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand(ArrayList<Task> list){
        super("");
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            s.append("\t");
            s.append(i + 1).append(". ").append(list.get(i));
            if (i != list.size() - 1) s.append("\n");
        }
        if (list.size() == 0) s.append("\tYour list is empty!");
        this.reply = s.toString();
        super.dukeReply();
    }

}

