package duke;

import java.util.ArrayList;

public class CommandList {
    ArrayList<Command> commands = new ArrayList<Command>();
    Storage storage;

    public CommandList(Storage storage, ArrayList<Command> commands) {
        this.storage = storage;
        this.commands = commands;
    }

    public CommandList(Storage storage) {
        this.storage = storage;
    }

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

     void doneCommand(int id) {
        Command command = commands.get(id);
        command.markDone();
        Ui.printDone(command);
        storage.save(storage.filePath, commands);
    }

     void deleteCommand(int id) {
        Command command = commands.get(id);
        commands.remove(id);
        int size = commands.size();
        Ui.printDelete(command, size);
        storage.save(storage.filePath, commands);
    }
}
