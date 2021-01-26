package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.HashSet;
import java.util.Iterator;

public class FindCmd extends Command {
    private final String cmdArgs;

    public FindCmd(String cmdArgs) {
        this.cmdArgs = cmdArgs;
    }

    @Override
    public String execute(TaskList lst) {
        String[] wordsToFind = cmdArgs.split(" ");

        HashSet<Task> hs = new HashSet<>();
        for (String word: wordsToFind) {
            hs.addAll(lst.find(word));
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        Iterator<Task> iter = hs.iterator();
        int sn = 1;
        while (iter.hasNext()) {
            sb.append(String.format("%d. %s", sn, iter.next()));
            sn++;
        }

        return sb.toString();
    }
}
