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
    ArrayList<Command> archivedCommands = new ArrayList<Command>();
    Storage storage;
    Storage archiveStorage;

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
     String printList() {
         String result = "";
        if (commands.size() == 0) {
            result = "Your list of commands are currently empty!\n";
        } else {
            for (int i = 0; i < commands.size(); i++) {
                Command value = commands.get(i);
                result += ((i + 1) + ". " + value +"\n");
            }
        }
         return result;
     }

    /**
     * Adds and saves a single command to the command list,
     * printing out appropriate Ui based on command
     * type
     *
     * @param command command of children classes ToDo, Deadline, Event
     * @param commandType identifier to sort command classes
     */
     String addCommand(Command command, String commandType) {
        commands.add(command);
        int size = commands.size();
        storage.save(storage.filePath, storage.path, commands);

         if (commandType.equals("T")) {
            return Ui.printToDo(command, size);
        } else if (commandType.equals("D")) {
            return Ui.printDeadline(command, size);
        } else {
            return Ui.printEvent(command, size);
        }
    }

    /**
     * Marks a single command as done based on its index
     * with markDone(), saves the updated command list and
     * printing out appropriate Ui to notify user
     *
     * @param id index of command to be marked done in commands list
     * @see duke.Command#markDone() markDone
     */
     String doneCommand(int id) {
        Command command = commands.get(id);
        command.markDone();
        storage.save(storage.filePath, storage.path, commands);
        return Ui.printDone(command);
    }

    /**
     * Deletes a single command off command list based
     * on its index, saving the new command list and
     * printing out appropriate Ui to notify user
     *
     * @param id index of command to be marked done in commands list
     * @see duke.Command#markDone() markDone
     */
     String deleteCommand(int id) {
        Command command = commands.get(id);
        commands.remove(id);
        int size = commands.size();
        storage.save(storage.filePath, storage.path, commands);
        return Ui.printDelete(command, size);
    }

    /**
     * Searches all commands in commandList that match
     * given keyword given by owner
     *
     * @param keyWord to match during search for user
     */
    public String findCommand(String keyWord) {
        ArrayList<Command> targetList = new ArrayList<Command>();
        String result = "";

        for (int i = 0; i < commands.size(); i++) {
            Command value = commands.get(i);
            String description = value.getDescription();
            if (description.contains(keyWord)) {
                targetList.add(value);
            }
        }
        if (commands.isEmpty()) {
            result = "Your list of commands are currently empty!\n";
        } else {
            if (targetList.isEmpty()) {
                result = Ui.printFind(targetList, false);
            } else {
                result = Ui.printFind(targetList, true);
            }
        }
        return result;
    }

    public String archiveCommand() {
        archiveStorage = new Storage(storage.archiveFilePath
                , storage.archivePath);

        archivedCommands = commands;

        archiveStorage.save(storage.archiveFilePath
                , storage.archivePath
                , archivedCommands);

        commands = new ArrayList<Command>();
        return Ui.printArchiveCompleted();
    }

    public String retrieveCommand() {
        int size = commands.size();

        if (size == 0) {
            commands = archivedCommands;
        } else {
            commands.addAll(archivedCommands);
        }

        storage.save(storage.filePath
                , storage.path
                , commands);

        return Ui.printRetrievalCompleted();
    }
}
