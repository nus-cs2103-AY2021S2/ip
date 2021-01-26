import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    /**
     * A class to wrap around the list of Tasks and provide additional functionality.
     */

     private static List<Task> listOfTasks;

     public TaskList() {
         this.listOfTasks = new ArrayList<Task>();
     }

     public TaskList (List<Task> listOfTasks) {
         this.listOfTasks = listOfTasks;
     }

     public void add(Task t) {
         listOfTasks.add(t);
     }

     public Task delete(int indexToDelete) {
         Task taskToReturn = listOfTasks.get(indexToDelete);
         listOfTasks.remove(indexToDelete);
         return taskToReturn;
     }

    public Task markTaskDone(int indexToMarkDone) {
        Task task = listOfTasks.get(indexToMarkDone-1);
        task.markAsDone();
        return task;
    }

    @Override
    public Iterator<Task> iterator() {
        return listOfTasks.iterator();
    }
}
