package duke;

/** Represents Command list containing commands and carries
 * methods to print, add, delete and indicate command as done.
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */

import java.util.ArrayList;

public class CommandList {
    ArrayList<Command> commands = new ArrayList<Command>();
    Storage storage;

    /**
     * Constructor for command list
     *
     * @param storage to reference storage instance to save most updated command list
     */
    public CommandList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Iterates through non-empty commands list
     * and prints each command in ascending order
     *
     */
     void printList() {
        if (commands.size() == 0) {
            System.out.println("Your list of commands are currently empty!\n");
        } else {
            for (int i = 0; i < commands.size(); i++) {
                Command value = commands.get(i);
                System.out.println((i + 1) + ". " + value);
            }
        }
    }

    /**
     * Adds and saves a single command to the command list,
     * printing out appropriate Ui based on command
     * type
     *
     * @param command command of children classes ToDo, Deadline, Event
     * @param commandType identifier to sort command classes
     */
     void addCommand(Command command, String commandType) {
        commands.add(command);
        int size = commands.size();

        if (commandType.equals("T")) {
            Ui.printToDo(command, size);
        } else if (commandType.equals("D")) {
            Ui.printDeadline(command, size);
        } else {
            Ui.printEvent(command, size);
        }
        storage.save(storage.filePath, commands);
    }

    /**
     * Marks a single command as done based on its index
     * with markDone(), saves the updated command list and
     * printing out appropriate Ui to notify user
     *
     * @param id index of command to be marked done in commands list
     * @see duke.Command#markDone() markDone
     */
     void doneCommand(int id) {
        Command command = commands.get(id);
        command.markDone();
        Ui.printDone(command);
        storage.save(storage.filePath, commands);
    }

    /**
     * Deletes a single command off command list based
     * on its index, saving the new command list and
     * printing out appropriate Ui to notify user
     *
     * @param id index of command to be marked done in commands list
     * @see duke.Command#markDone() markDone
     */
     void deleteCommand(int id) {
        Command command = commands.get(id);
        commands.remove(id);
        int size = commands.size();
        Ui.printDelete(command, size);
        storage.save(storage.filePath, commands);
    }
}
