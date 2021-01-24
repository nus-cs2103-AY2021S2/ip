import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private static final String PATH = "./data/";
  private static final String fileName = "duke.txt";
  private static final String PATH_TO_FILE = PATH + fileName;
  private List<Task> taskList;

  public TaskList() {
    taskList = new ArrayList<>();
  }

  public void add(Task task) throws IOException {
    taskList.add(task);
    Duke.printLineBreak();
    Duke.printIndented("Got it. I've added this task:");
    Duke.printIndented(String.format("  %s", task));
    Duke.printIndented(String.format("Now you have %d tasks in the list.", taskList.size()));
    Duke.printLineBreak();
    saveToHardDisk();
  }

  public void delete(int idx) throws IOException {
    Task deleted = taskList.remove(idx - 1);
    Duke.printLineBreak();
    Duke.printIndented("Noted. I've removed this task:");
    Duke.printIndented(String.format("  %s", deleted));
    Duke.printIndented(String.format("Now you have %d tasks in the list.", taskList.size()));
    Duke.printLineBreak();
    saveToHardDisk();
  }

  public void markDone(int idx) throws IOException {
    taskList.get(idx - 1).setDone();
    Duke.printLineBreak();
    Duke.printIndented("Nice! I've marked this task as done");
    Duke.printIndented(taskList.get(idx - 1).toString());
    Duke.printLineBreak();
    saveToHardDisk();
  }

  public void show() {
    Duke.printLineBreak();
    Duke.printIndented("Here are the tasks in your list:");
    for (int i = 0; i < taskList.size(); i++) {
      Duke.printIndented(String.format("%d.%s", i + 1, taskList.get(i).toString()));
    }
    Duke.printLineBreak();
  }

  public int size() {
    return taskList.size();
  }

  private void saveToHardDisk() throws IOException {
    File directory = new File(PATH);
    if (!directory.exists()) {
      directory.mkdir();
    }
    FileWriter fw = new FileWriter(PATH_TO_FILE);
    for (int i = 0; i < taskList.size(); i++) {
      fw.write(taskList.get(i).toSavedString());
      fw.write("\n");
    }
    fw.close();
  }
}
