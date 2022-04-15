import java.util.Comparator;

/**
 * Task Comparator is a comparator that compares tasks based on their description's lexicographic order.
 */
public class TaskComparator implements Comparator<Task> {

    /**
     * Overrides compare method in Comparator.
     *
     * @param task1 first task to be compared.
     * @param task2 second task to be compared.
     * @return an int that tells the result after ordering.
     */
    @Override
    public int compare(Task task1, Task task2) {
        String firstDesc = task1.getDescription();
        String secondDesc = task2.getDescription();
        return firstDesc.compareTo(secondDesc);
    }
}
