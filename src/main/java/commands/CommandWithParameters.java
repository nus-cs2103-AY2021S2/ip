package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;

public abstract class CommandWithParameters extends Command {
    protected static final String insufficientArgsErrMsg = "There are missing arguments inputted for this %s command.";
    protected String methodUsage; // todo can be used in err msg

    protected CommandWithParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleNoArgs() throws MissingArgumentException {
        if (this.commandBody.isEmpty()) {
            throw new MissingArgumentException(String.format(insufficientArgsErrMsg, commandName));
        }
    }

    protected boolean isInvalidListIndex(int size, int userIdx) {
        int offZeroIdx = userIdx - 1; // abstract this how?
        return offZeroIdx > 0 && offZeroIdx < size;
    }

    protected void handleInvalidListIdx(int size, int userIdx) {
        if (isInvalidListIndex(size, userIdx)) {
            int minIdx = 1; // for error message. should this be abstracted somewhere in tasklist
            String invalidNumErrMsg = "Invalid task number given: " + userIdx
                    + ". Number needs to be between " + minIdx + " and " + size + " (inclusive). "; // should be handled in exception.java
            handleException(new InvalidArgumentException(invalidNumErrMsg));
        }
    }
}
