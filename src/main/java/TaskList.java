import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Abstracts the data structure and relevant methods used to store the Task objects.
 * An ArrayList is used for this purpose.
 */
public class TaskList {

    private static ArrayList<Task> todoArray;
    private static ArrayList<EventDeadline> eventDeadlineArray;
    private static ArrayList<? extends Task> overallArray;

    TaskList(ArrayList<Task> tasks) {
        overallArray = tasks;
        todoArray = new ArrayList<>();
        eventDeadlineArray = new ArrayList<>();
        for (Task t : tasks ) {
            if (t.getTaskType() == 0) {
                todoArray.add(t);
                mergeArrays();
            }
            if (t.getTaskType() == 1 || t.getTaskType() == 2) {
                eventDeadlineArray.add((EventDeadline) t);
                mergeArrays();
                sortByDate(eventDeadlineArray);
            }
        }
    }

    TaskList() {
        overallArray = new ArrayList<>();
    }

    /**
     * Adds a task object to the data structure.
     * @param task A task object.
     */
    public void add(Task task) {
        System.out.println(task.getTaskType());
        if (task.getTaskType() == 0) {
            todoArray.add(task);
            mergeArrays();
        }
        if (task.getTaskType() == 1 || task.getTaskType() == 2) {
            eventDeadlineArray.add((EventDeadline) task);
            mergeArrays();
            sortByDate(eventDeadlineArray);
        }
    }

    /**
     * Find out the number of items in the data structure.
     * @return the number of task objects in the ArrayList.
     */
    public int size() {
        return overallArray.size();
    }

    /**
     * Obtain an item at a specific index in the data structure.
     * @param index Index where the item is to be retrieved.
     * @return the task object at the specific index in the ArrayList.
     */
    public Task get(int index) {
        return overallArray.get(index);
    }

    /**
     * Deletes an item at a specific index in the data structure.
     * @param index Index where the task object is to be deleted.
     */
    public void remove(int index) {
        Task toRemove = overallArray.get(index);
        if (toRemove.getTaskType() == 0) {
            todoArray.remove(toRemove);
            System.out.println(toRemove.toString());
            mergeArrays();
        } if (toRemove.getTaskType() == 1 || toRemove.getTaskType() == 2) {
            eventDeadlineArray.remove((EventDeadline) toRemove);
            mergeArrays();
            sortByDate(eventDeadlineArray);
        }
    }

    /**
     * Allows other classes to ask for a copy of the data structure.
     * @return the ArrayList.
     */
    public static ArrayList<? extends Task> getTasklist() {
        return overallArray;
    }


    public void mergeArrays() {
        sortByDate(eventDeadlineArray);
        ArrayList<Task> todoClone = new ArrayList<>(todoArray);
        for (EventDeadline e : eventDeadlineArray) {
            todoClone.add((Task) e);
        }
        overallArray = todoClone;
    }

    public static void sortByDate(ArrayList<? extends EventDeadline> list) {
        Collections.sort(list, (Comparator<EventDeadline>) (o1, o2) -> {
            int compareDate = 0;
            compareDate = o1.getDateInfo().compareTo(o2.getDateInfo());
            return compareDate;
        });
    }
}
