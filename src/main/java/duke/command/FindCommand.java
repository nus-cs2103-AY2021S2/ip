package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand implements Command {
    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponString(TaskList tasks, Storage storage) {
        TaskList filteredList = new TaskList();

        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDetail().indexOf(this.searchString) >= 0) {
                filteredList.add(currTask);
            }
        }

        String findResponse = filteredList.toString();
        return findResponse;
    }
    
}
