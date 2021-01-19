import java.util.ArrayList;

public class myList {
    ArrayList<Task> taskList;
    int counter;

    public myList() {
        taskList = new ArrayList<>();
        counter = 0;
    }

    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);

        taskList.add(task);
        counter++;
        System.out.println("Added to list: " + taskDescription + "\n");
    }

    public void markCompleted(int index) {
        taskList.get(index).markCompleted();
        System.out.println("Good job! I've marked it as completed for you as well!\n");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here's your list of tasks! ");

        for (int i = 0; i < counter; i++) {
            sb.append("\n"
                    + (i + 1)
                    + ". "
                    + taskList.get(i).toString()
            );
        }

        sb.append("\n");

        return sb.toString();
    }
}