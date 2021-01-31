package duke.commands;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends ChatBotCommand {
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Returns message for completing a task.
     *
     * @param th TaskHandler.
     * @param storage Storage to write and read file.
     * @return Command response.
     * @throws ChatBotException if encounters error.
     */
    public String runTask(TaskHandler th, Storage storage) throws ChatBotException {
        ArrayList<Task> tasks = th.findTask(this.keyWord);
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            output.append(num)
                    .append(": ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        if (tasks.size() == 0) {
            output.append("--- No Tasks Found ---");
        }
        return output.toString();
    }
}
