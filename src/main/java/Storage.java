import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
  protected String path;
  protected File file;

  /**
   * Creates Storage object with the filepath.
   * @ param filepath The filepath storing all the previous tasklists data
   */
  public Storage(String filepath) throws IOException {
    this.path = filepath;
    File filestr = new File(path);
    filestr.createNewFile();
    this.file = filestr;
  }

  /**
   * Iterates through the task arraylist and stores each element into a textfile.
   */

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

  /**
   * Loads any previous tasks from a text file by storing the contents inside ArrayList.
   * @ return The Arraylist consisting of all the tasks in the text file.
   * @ throws IOException if the file cannot be found in the specified path.
   */

  public ArrayList<Task> load() throws IOException {
    ArrayList<Task> tasks = new ArrayList<Task>();
    try {
      Scanner fileReader = new Scanner(this.file);
      while (fileReader.hasNextLine()) {
        String line = fileReader.nextLine();
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
          DateTimeFormatter informat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
          DateTimeFormatter outformat = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
          LocalDateTime date = LocalDateTime.parse(stringArr[3], informat);
          String formattedDate = outformat.format(date);
          Deadline task = new Deadline(taskDesc, formattedDate);
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

