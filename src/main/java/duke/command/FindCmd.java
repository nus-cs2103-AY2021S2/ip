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
        // Split the search terms and search individually
        String[] wordsToFind = cmdArgs.split(" ");

        HashSet<Task> hs = new HashSet<>();
        for (String word: wordsToFind) {
            hs.addAll(lst.findTasksWithStr(word));
        }

        Iterator<Task> iter = hs.iterator();
        if (!iter.hasNext()) {
            return String.format("There are no matching tasks in your list for search '%s'.\n", cmdArgs);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Here are the matching tasks in your list for search '%s':\n", cmdArgs));

        int sn = 1;
        while (iter.hasNext()) {
            sb.append(String.format("%d. %s\n", sn, iter.next()));
            sn++;
        }

        return sb.toString();
    }
}
