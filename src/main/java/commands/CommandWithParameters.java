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

    /**
     *
     * @param size
     * @param userIdx Index starting from 1, not 0
     * @return
     */
    protected boolean isInvalidListIndex(int size, int userIdx) {
        return userIdx < 1 || userIdx > size;
    }

    protected void handleInvalidListIdx(int size, int userIdx) {
        if (isInvalidListIndex(size, userIdx)) {
            int minIdx = 1; // for error message. should this be abstracted somewhere in tasklist
            String invalidNumErrMsg = "Invalid task number given: " + userIdx
                    + ". Number needs to be between " + minIdx + " and " + size + " (inclusive). "; // should be handled in exception.java
            handleException(new InvalidArgumentException(invalidNumErrMsg));
        }
    }

    protected void checkInvalidListIdx(int size, int userIdx) throws InvalidArgumentException {
        if (isInvalidListIndex(size, userIdx)) {
            int minIdx = 1; // for error message. should this be abstracted somewhere in tasklist
            String invalidNumErrMsg = "Invalid task number given: " + userIdx
                    + ". Number needs to be between " + minIdx + " and " + size + " (inclusive). "; // should be handled in exception.java
            throw new InvalidArgumentException(invalidNumErrMsg);
        }
    }
}
