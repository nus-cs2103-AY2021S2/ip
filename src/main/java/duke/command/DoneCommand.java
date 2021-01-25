package duke.command;

import duke.TaskList;

public class DoneCommand implements Command {

    private final int id;
    private String response;

    public DoneCommand(int id) {
        this.id = id;
    }

    
    /** 
     * @return boolean
     */
    public boolean shouldExit() {
        return false;
    }

    
    /** 
     * @return String
     */
    public String getResponse() {
        return "Nice! I've marked this duke.task as done:\n  " + response;
    }

    
    /** 
     * @param taskList
     * @return duke.TaskList
     */
    public TaskList execute(TaskList taskList) {
        taskList.markDone(id);
        response = taskList.getTask(id).toString();
        return taskList;
    }
    
}
