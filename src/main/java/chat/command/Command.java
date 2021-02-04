package chat.command;

import chat.ChatException;
import chat.TaskList;
import chat.Storage;
import chat.Ui;
import chat.task.Task;

/**
 * Abstract class for commands.
 * <p>Commands refer to the commands that Chat the Cat receives from the user.</p>
 */

public abstract class Command {

    /**
     * Abstract method that executes the command.
     * @param taskList TaskList object that contains a list of tasks.
     * @param ui Ui object that gives responses to user.
     * @param storage Storage object that interacts with task data in hard disk.
     * @throws ChatException If format of command is wrong.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ChatException;

    /**
     * Returns true if command requires Chat the Cat to exit.
     * 
     * @return Boolean that shows if Chat should exit.
     */
    public boolean isExit() { 
        return false;
    };

    /**
     * Method that can be used to check format for commands with indexes.
     * 
     * @param taskList TaskList object that contains a list of tasks.
     * @param commandName Command name.
     * @param str Inputted command string from user to Chat the Cat.
     * @return Task.
     * @throws ChatException If format of command is wrong.
     */
    public Task checkCommandIndex(TaskList taskList, String commandName, String str) throws ChatException {
        //for commands with format: [command] [index]
        //returns valid index if index is correct
        String formatStr = String.format("Please input with this format:\n" + 
                "%s [index]", commandName);
        if (str.strip().equals(commandName)) {
            throw new ChatException("Missing index\n" + formatStr);
        } else if (!str.contains(" ")) {
            throw new ChatException("Missing space before index\n" + formatStr);
        }
        
        try {
            int i = Integer.parseInt(str.split(" ")[1]) - 1;
            return taskList.getTasks().get(i);
        } catch (IndexOutOfBoundsException e1) {
            //list is empty, hence i results in index out of bounds
            //or when i >= taskList.getTasks().size()
            throw new ChatException("List is empty or index is out of bounds");
        } catch (NumberFormatException e3){
            //i.e. [command] string
            throw new ChatException("Index should be an integer\n" + formatStr);
        }
    }
    
}
