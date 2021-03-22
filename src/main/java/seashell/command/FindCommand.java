package seashell.command;

import seashell.storage.SaveHandler;
import seashell.exception.SeashellException;
import seashell.TaskList;
import seashell.task.Task;

import java.util.ArrayList;

public class FindCommand implements Command {

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    /**
     * Finds task that contain some keyword specified by user
     * @param taskList the task list to search
     * @param saveHandler save handler to save changes to the save file
     * @return String output of the program
     * @throws SeashellException if task list is empty or no task containing keyword was found
     */
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) throws SeashellException {
        if (taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            ArrayList<Task> foundList = new ArrayList<>();
            for (Task t : taskList.taskList) {
                if (t.getName().contains(toFind)) {
                    foundList.add(t);
                }
            }
            if (foundList.isEmpty()) {
                throw new SeashellException("No tasks with the keyword " + toFind + " was found!");
            } else {
                TaskList foundTaskList = new TaskList(foundList);
                StringBuilder sb = new StringBuilder();
                sb.append("Here are the matching tasks in your list:\n");
                sb.append(new ListCommand().execute(foundTaskList, saveHandler));
                return sb.toString();
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof FindCommand
                && this.toFind.equals(((FindCommand) other).toFind));
    }
}
