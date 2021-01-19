import java.util.ArrayList;

public class myList {
    ArrayList<Task> taskList;

    public myList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);

        taskList.add(task);
        System.out.println("Added to list:\n" + task.toString());
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    public void addDeadline(String description, String datetime) {
        Deadline deadline = new Deadline(description, datetime);

        taskList.add(deadline);
        System.out.println("Added to list:\n" + deadline.toString());
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    public void addEvent(String description, String datetime) {
        myEvent event = new myEvent(description, datetime);

        taskList.add(event);
        System.out.println("Added to list:\n" + event.toString());
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    public void markCompleted(int index) {
        taskList.get(index).markCompleted();
        System.out.println("Good job! I've marked it as completed for you as well!\n");
    }

    public void delete(int index) {
        taskList.remove(index);
        System.out.println("Got it! I've removed the task for you\n");
        System.out.println("You now have " + taskList.size() + " tasks\n");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here's your list of tasks!");

        for (int i = 0; i < taskList.size(); i++) {
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