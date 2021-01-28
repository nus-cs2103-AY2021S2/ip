import java.util.ArrayList;

/**
 * Tasklist class to deal with adding deleting in arrayList
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
public class TaskList {

    public TaskList() {
    }

    /**
     * This is the method to add to the arrayList
     * @param arrayList specifies the arraylist being added
     * @param task takes in the a task to add
     * @return Nothing.
     */
    public void addToList(ArrayList<Task> arrayList, Task task) {
        task.index = arrayList.size() + 1;
        arrayList.add(task);
        System.out.println(task);
    }

    /**
     * This is the method to delete from the arrayList
     * @param arrayList specifies the arraylist being added
     * @param deletedNumber specifies which element to be deleted.
     * @return Nothing.
     */
    public void deleteFromList(ArrayList<Task> arrayList, int deletedNumber) {
        int moved = deletedNumber - 1;
        System.out.println(arrayList.get(moved));
        arrayList.remove(arrayList.get(moved));
        for (int i = moved; i < arrayList.size(); i++) {
            arrayList.set(i, arrayList.get(i).decreaseIndex());
        }
    }
}
