package commands;

import exceptions.SnomException;
import storage.Storage;
import tasks.*;
import ui.Snomio;

public class AddCommand extends Command{
    public AddCommand(CommandEnum commandType, String content) {
        super(commandType, content);
    }

    /**
     * Executes todo, deadline, event command.
     *
     * @param taskList         list of task
     * @param snomio           I/O of Snom
     * @param storage          files handler of snom
     * @return CommandResponse response after command execution
     * @throws SnomException   if command execution failed
     */
    @Override
    public CommandResponse execute(TaskList taskList, Snomio snomio, Storage storage) throws SnomException {
        Task task;
        if(commandType == CommandEnum.TODO){
            task = new Todo(this.content);
        }else if(commandType == CommandEnum.DEADLINE){
            String[] dlArr = splitContentWithDate("/by");
            task = new Deadline(dlArr[0], dlArr[1]);
        }else{
            String[] dlArr = splitContentWithDate("/at");
            task = new Event(dlArr[0], dlArr[1]);
        }
        taskList.addTask(task);
        storage.saveFile(taskList);
        return new CommandResponse(snomio.showTaskAdded(task, taskList.getSize()), false);
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
        String[] splittedContent = this.content.split(delim);
        if(splittedContent.length == 2){
            return splittedContent;
        }else if(splittedContent.length < 2){
            throw new SnomException("Please enter at least one date!");
        }else{
            throw new SnomException("Oops! You have entered more than ONE date, please try again!");
        }
    }
}
