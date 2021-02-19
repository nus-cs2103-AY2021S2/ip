package commands;

import exceptions.MissingArgumentException;

public abstract class AddTaskWithTimeCommand extends CommandWithParameters {

    protected String timeArgDelimiter = "";

    // the command keyword is being counted as the first argument here
    protected String secondArg;
    protected String thirdArg;
    protected String missingThirdArgErrMsg = "Missing third argument";
    protected final String missingDelimiterErrMsg =
            "Missing delimiter. Please type help for method usage.";

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
