//package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    Storage storage;

    TaskList(Storage storage) {
        this.storage = storage;
        this.list = this.storage.loadFile();
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
    public String listString() {
        String listString = "";
        for(int i = 0; i < list.size(); i++) {
            listString += String.format("%d. %s%n",i + 1, list.get(i).toString());
        }
        return listString;
    }
    static String tabbedListString(ArrayList<Task> tasks) {
        String tabbedList = "";
        for(int i = 0; i < tasks.size(); i++) {
            tabbedList += String.format("    %d. %s%n\n",i + 1, tasks.get(i).toString());
        }
        return tabbedList;
    }
    /**
     * Marks the task specified as done.
     *
     * @param index Index of task to mark.
     */
    String markDone(int index) {
        try {
            Task task = list.get(index);
            task.markDone();
            storage.saveFile(list);
            return Ui.doneString(task);
            // Ui.printDone(task);
        } catch (IndexOutOfBoundsException e) {
            return Ui.indexErrorString();
        }
    }

    /**
     * Deletes the task specified.
     * @param index Index of task to delete.
     */
    String delete(int index) {
        try {
            Task task = list.get(index);
            list.remove(index);
            storage.saveFile(list);
            return Ui.deleteString(task, list.size());
        } catch (IndexOutOfBoundsException e) {
            return Ui.indexErrorString();
        }

    }

    /**
     * Adds the task specified to the list.
     * @param task Task to be added to list.
     */
    String add(Task task) {
        list.add(task);
        storage.saveFile(task.toFileString());
        return Ui.addString(task, list.size());
    }

    String find(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String taskString = list.get(i).toString();
            if (taskString.contains(keyword)) {
                newList.add(list.get(i));
            }
        }
        return Ui.findString(newList);
    }


}
