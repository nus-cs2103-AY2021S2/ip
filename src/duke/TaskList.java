package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    Storage storage;

    TaskList(Storage storage) {
        this.storage = storage;
    }

    TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    void loadList() {
        this.list = this.storage.loadFile();
    }

    void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i).toString());
        }
        System.out.println("\n");
    }

    void markDone(int index) {
        Task task = list.get(index);
        task.markDone();
        Ui.printDone(task);
    }

    void delete(int index) {
        Task task = list.get(index);
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexError();
        }
        storage.saveFile(list);
        Ui.printDelete(task, list.size());
    }

    void add(Task task) {
        list.add(task);
        storage.saveFile(task.toFileString());
        Ui.printAdd(task, list.size());
    }


}
