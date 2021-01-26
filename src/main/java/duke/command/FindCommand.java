package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    private String keyword;
    public FindCommand(String keyword){
        super("");
        this.keyword = keyword;
    }

    public void execute(Ui ui, Storage s, TaskList list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            if(list.getItem(i).getDescription().contains(keyword)){
                sb.append("\t");
                sb.append(i + 1).append(". ").append(list.getItem(i));
                if (i != list.getSize() - 1) sb.append("\n");
            }
        }
        if (list.getSize() == 0) sb.append("\tYour list is empty!");
        this.reply = sb.toString();
        ui.reply(this.reply);
    }

}
