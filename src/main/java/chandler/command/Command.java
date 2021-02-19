package chandler.command;

import chandler.ChandlerException;
import chandler.Storage;
import chandler.TaskList;

/**
 * The Command interface provides an interface for all Command classes to implement.
 */
public interface Command {
    String run(Storage storage, TaskList taskList) throws ChandlerException;
}
