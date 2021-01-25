import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Store {
  protected String path;
  protected File file;

  public Store() throws IOException {
    File filestr = new File("src/main/java/duke.text");
    filestr.createNewFile();
    this.file = filestr;
    this.path = file.getAbsolutePath();
  }

  public void save(ArrayList<Task> task) throws IOException {
    FileWriter fileWriter = new FileWriter(this.file);
    try {
      for (Task t : task) {
        fileWriter.write(t.saveText());
        fileWriter.write(System.lineSeparator());
      }
      fileWriter.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println("An unexpected Error occured during Saving");
    }
  }

  public ArrayList<Task> load() throws IOException {
    ArrayList<Task> tasks = new ArrayList<Task>();
    try {
      Scanner fileReader = new Scanner(this.file);
      while (fileReader.hasNextLine()) {
        String line = fileReader.nextLine();
        System.out.println(line);
        String[] stringArr = line.split(",");
        String taskType = stringArr[0];
        String taskIcon = stringArr[1];
        String taskDesc = stringArr[2];
        if (taskType.equals("T")) {
          Todo task = new Todo(taskDesc);
          if (taskIcon.equals("\u2718")) {
            task.taskDone();
          }
          tasks.add(task);
        }
        if (taskType.equals("E")) {
          Event task = new Event(taskDesc, stringArr[3]);
          if (taskIcon.equals("\u2718")) {
            task.taskDone();
          }
          tasks.add(task);
        }
        if (taskType.equals("D")) {
          Deadline task = new Deadline(taskDesc, stringArr[3]);
          if (taskIcon.equals("\u2718")) {
            task.taskDone();
          }
          tasks.add(task);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("no file");
    }
    return tasks;
  }


}

