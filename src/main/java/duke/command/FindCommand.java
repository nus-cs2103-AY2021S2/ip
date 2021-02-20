package duke.command;

import duke.DukeResponse;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * @param taskList
     * @param ui
     * @param storage
     * @return DukeResponse
     */
    public DukeResponse execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = new ArrayList<>();
        LevenshteinDistance ld = new LevenshteinDistance();
        for (Task t : taskList.getTasks()) {
            String[] words = t.getName().split(" ");
            for (String word : words) {
                if (ld.apply(word, this.keyword) < 2) {
                    tasks.add(t);
                    break;
                }
            }
        }
        return new DukeResponse(ui.showFound(tasks));
    }
}
