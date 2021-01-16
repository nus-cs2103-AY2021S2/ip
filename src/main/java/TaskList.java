import java.util.*;

public class TaskList {
  private List<Task> taskList;

  public TaskList() {
    taskList = new ArrayList<>();
  }

  public void add(String task) {
    taskList.add(new Task(task));
    Duke.printLineBreak();
    Duke.printIndented(String.format("added: %s", task));
    Duke.printLineBreak();
  }

  public void markDone(int idx) {
    taskList.get(idx-1).isDone = true;
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
