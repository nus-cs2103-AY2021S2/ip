package duke;

import java.util.ArrayList;

public class CommandList {
    static ArrayList<Command> commands;
    static Storage storage;

    public CommandList(ArrayList<Command> commands, Storage storage) {
        this.commands = commands;
        this.storage = storage;
    }

    static void printList() {
        for (int i = 0; i < commands.size(); i++) {
            Command value = commands.get(i);
            System.out.println((i + 1) + ". " + value);
        }
    }

    static void addCommand(Command command, String commandType) {
        commands.add(command);
        int size = commands.size();

        if (commandType.equals("T")) {
            Ui.printToDo(command, size);
        } else if (commandType.equals("D")) {
            Ui.printDeadline(command, size);
        } else {
            Ui.printEvent(command, size);
        }
        storage.save(commands, storage.filePath);
    }

    static void doneCommand(int id) {
        Command command = commands.get(id);
        command.markDone();
        Ui.printDone(commands);
        storage.save(commands, storage.filePath);
    }

    static void deleteCommand(int id) {
        Command command = commands.get(id);
        commands.remove(id);
        int size = commands.size();
        Ui.printDelete(command, size);
        storage.save(commands, storage.filePath);
    }
}
