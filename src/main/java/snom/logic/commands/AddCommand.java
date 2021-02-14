package snom.logic.commands;

import snom.exceptions.SnomException;
import snom.storage.StorageManager;
import snom.model.task.*;
import snom.ui.Snomio;

public class AddCommand extends Command{
    public AddCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes todo, deadline, event command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of snom.model.Snom
     * @param storage          snom.files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, StorageManager storage) throws SnomException {
        Task task = null;
        if(commandType == CommandEnum.TODO){
            task = new Todo(this.content);
        }else if(commandType == CommandEnum.DEADLINE){
            String[] dlArr = splitContentWithDate("/by");
            task = new Deadline(dlArr[0], dlArr[1]);
        }else if(commandType == CommandEnum.EVENT){
            String[] dlArr = splitContentWithDate("/at");
            task = new Event(dlArr[0], dlArr[1]);
        }else{
            throw new SnomException("Error: Something magical happened while snom.model.Snom trying to create a task!");
        }
        taskList.addTask(task);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.getTaskAdded(task, taskList.getSize()), false);
    }

    /**
     * Returns a string array of size 2
     * first string contains the content of the task
     * second string contains the date of the task
     *
     * @param delim             delimiter for string splitting
     * @return                  string array of content and date
     * @throws SnomException    if no date was given or more than one date is given
     */
    private String[] splitContentWithDate(String delim) throws SnomException {
        String[] splitContent = this.content.split(delim);

        if(splitContent.length < 2){
            throw new SnomException("Please enter at least one date!");
        }

        if(splitContent.length > 2){
            throw new SnomException("Oops! You have entered more than ONE date, please try again!");
        }

        return splitContent;
    }
}
