package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskHandler;
import duke.exceptions.ChatBotException;
import duke.tasks.Task;

public class FindCommand extends ChatBotCommand {
    private String keyWord;

    /**
     * Default constructor for the FindCommand class.
     *
     * @param keyWord the word that is to be searched in the list.
     */
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
