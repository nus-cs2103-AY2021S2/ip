import java.util.*;

public class TaskList {
  private List<Task> taskList;

  public TaskList() {
    taskList = new ArrayList<>();
  }

  public void add(Task task) {
    taskList.add(task);
    Duke.printLineBreak();
    Duke.printIndented("Got it. I've added this task:");
    Duke.printIndented(String.format("  %s", task));
    Duke.printIndented(String.format("Now you have %d tasks in the list", taskList.size()));
    Duke.printLineBreak();
  }

  public void markDone(int idx) {
    taskList.get(idx-1).setDone();
    Duke.printLineBreak();
    Duke.printIndented("Nice! I've marked this task as done");
    Duke.printIndented(taskList.get(idx-1).toString());
    Duke.printLineBreak();
  }

  public void show() {
    Duke.printLineBreak();
    Duke.printIndented("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      Duke.printIndented(String.format("%d.%s", i+1, taskList.get(i).toString()));
    }
    Duke.printLineBreak();
  }
}
