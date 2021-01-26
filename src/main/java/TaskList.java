import java.util.ArrayList;

public class TaskList {

  private ArrayList<Task> tasklist;

  public TaskList(ArrayList<Task> task) {
    this.tasklist = task;
  }

  public TaskList() {

  }

  public ArrayList<Task> getTasklist() {
    return this.tasklist;
  }

  /**
   * Iterates the arraylist and outputs each tasks inside.
   */

  public void list() {
    int i = 1;
    System.out.println(" ___________________________________________");
    System.out.println("Here are the tasks in your list:");
    for (Task task : tasklist) {
      System.out.println(i + ". " + task);
      i++;
    }
    System.out.println(" ___________________________________________");
  }

  public void addList(Task input) {
    this.tasklist.add(input);
  }

  /**
   * delete the tasks from the arraylist and output the notification text.
   */

  public void delete(int num) {
    Task task = this.tasklist.remove(num - 1);
    System.out.println("Noted. I've removed this task: ");
    System.out.println(task);
    System.out.println("Now you have " + this.tasklist.size() + " tasks in the list.");
  }

  /**
   * Outputs matching taks in the arraylist to a keyword
   */

  public void findtask(String keyword) {
    ArrayList<Task> list = new ArrayList<>();
    int i = 1;
    for (Task t : this.tasklist) {
      if (t.getDescription().contains(keyword)) {
        list.add(t);
      }
    }
    System.out.println(" ___________________________________________");
    System.out.println("Here are the tasks in your matching list:");
    for (Task task : list) {
      System.out.println(i + ". " + task);
      i++;
    }
    System.out.println(" ___________________________________________");
  }

}
