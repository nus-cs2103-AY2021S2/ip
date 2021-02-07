package commands;

import tasklist.TaskList;

// can be generalised to two arg command
// need to make it clear whether you're counting "todo" as an argument
public abstract class AddTaskWithTimeCommand extends CommandWithParameters {

    protected String timeArgDelimiter;
    protected String secondArg;
    protected String thirdArg;
    
    protected AddTaskWithTimeCommand(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

//    @Override
//    public void run(TaskList taskList) {
    protected void handleSplittingArgs() throws Exception{
        try {
            int thirdArgIdx = this.commandBody.indexOf(this.timeArgDelimiter); // assuming valid
            String desc = this.commandBody.substring(0, thirdArgIdx - 1).trim();
            String thirdArg = this.commandBody.substring(thirdArgIdx + 3).trim();
            this.secondArg = desc;
            this.thirdArg = thirdArg;
        } catch (Exception e) {
            // make this exception more specific
            // this.commandOutputMsg = e.getMessage();
            throw e;
        }
    }
}
