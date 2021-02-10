package commands;

import exceptions.MissingArgumentException;
import tasklist.TaskList;

// can be generalised to two arg command
// need to make it clear whether you're counting "todo" as an argument
public abstract class AddTaskWithTimeCommand extends CommandWithParameters {

    protected String timeArgDelimiter = "";
    protected String secondArg;
    protected String thirdArg;
    protected String missingThirdArgErrMsg = "Missing third argument";
    protected String missingDelimiter = "Please ensure you've used the right delimiter " + timeArgDelimiter;
    
    protected AddTaskWithTimeCommand(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleSplittingArgs() throws Exception {
        // actually don't need init here; use class vars for catch block
        int thirdArgIdx = 0;
        String desc = "";
        String thirdArg = "";
        try {
            assert this.timeArgDelimiter != null : "arg delimiter hasn't been initialised in child subclass";


            thirdArgIdx = this.commandBody.indexOf(this.timeArgDelimiter); // assuming valid
            desc = this.commandBody.substring(0, thirdArgIdx).trim();
            thirdArg = this.commandBody.substring(thirdArgIdx + this.timeArgDelimiter.length()).trim();

            this.secondArg = desc;
            this.thirdArg = thirdArg;

        } catch (StringIndexOutOfBoundsException e) {

            System.out.println("=========");
            System.out.println(desc);
            System.out.println(thirdArgIdx);
            System.out.println(thirdArg);
            System.out.println("___________");

            if (thirdArgIdx == -1) {
                throw new MissingArgumentException(missingDelimiter + "'" + timeArgDelimiter + "'");
            }

            if (desc.isEmpty()) {
                throw new MissingArgumentException("Missing description");
            }

            if (thirdArg.isEmpty()) {
                throw new MissingArgumentException(missingThirdArgErrMsg);
            }


        } catch (Exception e) {
            // make this exception more specific
            // this.commandOutputMsg = e.getMessage();
            throw e;
        }

        // handleEmptyArgs();
    }
}
