package commands;

import exceptions.MissingArgumentException;

// can be generalised to two arg command
// need to make it clear whether you're counting "todo" as an argument
public abstract class AddTaskWithTimeCommand extends CommandWithParameters {

    protected String timeArgDelimiter = "";
    protected String secondArg;
    protected String thirdArg;
    protected String missingThirdArgErrMsg = "Missing third argument";
    protected final String missingDelimiterErrMsg =
            "Missing delimiter. Please type help for method usage.";
            // Please use this command this way: " + methodUsage;
    
    protected AddTaskWithTimeCommand(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    private void handleMissingDelimiter() throws MissingArgumentException {
        if (!this.commandBody.contains(this.timeArgDelimiter)) {
            throw new MissingArgumentException(missingDelimiterErrMsg);
        }
    }

    private void splitArgs() {
        int thirdArgIdx = this.commandBody.indexOf(this.timeArgDelimiter);
        this.secondArg = this.commandBody.substring(0, thirdArgIdx).trim();
        this.thirdArg = this.commandBody.substring(thirdArgIdx + this.timeArgDelimiter.length()).trim();
    }

    private void handleEmptyArgs() throws MissingArgumentException {
        if (this.secondArg.isEmpty()) {
            throw new MissingArgumentException("Missing description");
        }

        if (this.thirdArg.isEmpty()) {
            throw new MissingArgumentException(missingThirdArgErrMsg);
        }
    }

    protected void parseCommandBody() throws MissingArgumentException {
        handleMissingDelimiter();

        splitArgs();

        handleEmptyArgs();

        this.hasRunSuccessfully = true;
    }
}
