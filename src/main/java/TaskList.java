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

  public String listTask() {
    int i = 1;
    StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
    for (Task task : tasklist) {
      output.append(i + ". " + task + "\n");
      i++;
    }
    return output.toString();
  }



  public void addList(Task input) {

    this.tasklist.add(input);
  }

  /**
   * delete the tasks from the arraylist and output the notification text.
   */

  public String delete(int num) {
    Task task = this.tasklist.remove(num - 1);
    String output = "\tNoted. I've removed this task: \n" + task + "\n"
            + "Now you have " + this.tasklist.size() + " tasks in the list.";
    return output;
  }

  /**
   * Outputs matching taks in the arraylist to a keyword
   */

  public String findTask(String keyword) {
    ArrayList<Task> list = new ArrayList<>();
    int i = 1;
    for (Task t : this.tasklist) {
      if (t.getDescription().contains(keyword)) {
        list.add(t);
      }
    }
    StringBuilder output = new StringBuilder("Here are the tasks in your matching list: \n");
    for (Task task : list) {
      output.append(i + ". " + task);
      i++;
    }
    return output.toString();
  }



}

