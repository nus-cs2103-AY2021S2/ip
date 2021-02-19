package commands;

import exceptions.InvalidArgumentException;
import exceptions.MissingArgumentException;

public abstract class CommandWithParameters extends Command {
    protected static final String INSUFFICIENT_ARGS_ERR_MSG =
            "There are missing arguments inputted for this %s command.";

    protected CommandWithParameters(String commandName, String commandBody) {
        super(commandName, commandBody);
    }

    protected void handleNoArgs() throws MissingArgumentException {
        if (this.commandBody.isEmpty()) {
            throw new MissingArgumentException(String.format(INSUFFICIENT_ARGS_ERR_MSG, commandName));
        }
    }

    /**
     * @param size
     * @param userIdx Index starting from 1, not 0
     * @return
     */
    protected boolean isInvalidListIndex(int size, int userIdx) {
        return userIdx < 1 || userIdx > size;
    }

    protected void checkInvalidListIdx(int size, int userIdx) throws InvalidArgumentException {
        if (isInvalidListIndex(size, userIdx)) {
            int minIdx = 1;

            String invalidNumErrMsg = String.format(
                    "Invalid task number given: %d. Number needs to be between %d and %d (inclusive). ",
                    userIdx, minIdx, size);

            throw new InvalidArgumentException(invalidNumErrMsg);
        }
    }
}
