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

    /**
     * Loads saved data into list attribute.
     */
    void loadList() {
        this.list = this.storage.loadFile();
    }

    /**
     * Prints out task list.
     */
    void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i).toString());
        }
        System.out.println("\n");
    }
    static void displayTabbedList(ArrayList<Task> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n",i + 1, tasks.get(i).toString());
        }
        System.out.println("\n");
    }
    /**
     * Marks the task specified as done.
     *
     * @param index Index of task to mark.
     */
    void markDone(int index) {
        try {
            Task task = list.get(index);
            task.markDone();
            Ui.printDone(task);
            storage.saveFile(list);
            // Ui.printDone(task);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexError();
        }
    }

    /**
     * Deletes the task specified.
     * @param index Index of task to delete.
     */
    void delete(int index) {
        try {
            Task task = list.get(index);
            list.remove(index);
            storage.saveFile(list);
            Ui.printDelete(task, list.size());
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexError();
        }

    }

    /**
     * Adds the task specified to the list.
     * @param task Task to be added to list.
     */
    void add(Task task) {
        list.add(task);
        storage.saveFile(task.toFileString());
        Ui.printAdd(task, list.size());
    }

    void find(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String taskString = list.get(i).toString();
            if (taskString.contains(keyword)) {
                newList.add(list.get(i));
            }
        }
        Ui.printFind(newList);
    }


}
