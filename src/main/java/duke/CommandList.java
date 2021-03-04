package duke;

import java.nio.file.Path;
import java.util.ArrayList;

/** Represents Command list containing commands and carries
 * methods to print, add, delete and indicate command as done.
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.1
 * @since 2021-02-22
 */
public class CommandList {
    private ArrayList<Command> commands = new ArrayList<Command>();
    private ArrayList<Command> archivedCommands = new ArrayList<Command>();
    private Storage storage;
    private Storage archiveStorage;

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
     * @return Numbered Printed List
     */
    String printList() {
        String result = "";
        if (commands.size() == 0) {
            result = "Your list of commands are currently empty!\n";
        } else {
            for (int i = 0; i < commands.size(); i++) {
                Command value = commands.get(i);
                result += ((i + 1) + ". " + value + "\n");
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
     * @return Added notification to user
     */
    String addCommand(Command command, String commandType) {
        commands.add(command);
        int size = commands.size();
        storage.save(storage.getFilePath(), storage.getPath(), commands);

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
     * @return Done notification to user
     * @see duke.Command#markDone() markDone
     */
    String doneCommand(int id) {
        Command command = commands.get(id);
        command.markDone();
        storage.save(storage.getFilePath(), storage.getPath(), commands);
        return Ui.printDone(command);
    }

    /**
     * Deletes a single command off command list based
     * on its index, saving the new command list and
     * printing out appropriate Ui to notify user
     *
     * @param id index of command to be marked done in commands list
     * @return Delete notification to user
     * @see duke.Command#markDone() markDone
     */
    String deleteCommand(int id) {
        Command command = commands.get(id);
        commands.remove(id);
        int size = commands.size();
        storage.save(storage.getFilePath(), storage.getPath(), commands);
        return Ui.printDelete(command, size);
    }

    /**
     * Searches all commands in commandList that match
     * given keyword given by owner
     *
     * @param keyWord to match during search for user
     * @return results of search of keyWord to user
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

    /**
     * Archives all Commands in the current Command
     * List to a text file named archive.txt in the data file
     * (same format as storage.save)
     *
     * @return Successful Archive notification to user
     * @see duke.Storage#save(String, Path, ArrayList) save
     */
    public String archiveCommand() {
        archiveStorage = new Storage(storage.getArchiveFilePath(),
                storage.getArchivePath());

        archivedCommands = commands;

        archiveStorage.save(storage.getArchiveFilePath(),
                storage.getArchivePath(),
                archivedCommands);

        commands = new ArrayList<Command>();
        return Ui.printArchiveCompleted();
    }

    /**
     * Retrieves the previously archived commands and
     * appends them to the current working Command List
     *
     * @return Successful Archive notification to user
     * @see duke.Storage#save(String, Path, ArrayList) save
     */
    public String retrieveCommand() {
        int size = commands.size();

        if (size == 0) {
            commands = archivedCommands;
        } else {
            commands.addAll(archivedCommands);
        }

        storage.save(storage.getFilePath(),
                storage.getPath(),
                commands);

        return Ui.printRetrievalCompleted();
    }
}

