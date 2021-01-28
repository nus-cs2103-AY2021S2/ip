import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
//        System.out.println("Here's a new task: " + task);
//        saveList();
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);

        return task;
//        System.out.println("Alrighty bossman. I shall wipe this task off the face of the earth: \n"
//                + task
//                + "\nGood riddance.");
//        saveList();
    }

    public Task doneTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();

        return task;
//        System.out.println("Impressive, yet another task has been done: \n"
//                + task
//                + "\nOne step closer to freedom now boss.");
//        saveList();
    }

    public void printTasks() {
        System.out.println("You have " + tasks.size() + " task(s) in the list:");

        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
