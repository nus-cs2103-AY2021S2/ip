import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Class that can create a storage object that contains methods to
 * read and write data to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor that creates a new Storage object.
     *
     * @param filePath the file path that is used to read and write
     *                 data to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that reads the data from the file located at the file path.
     *
     * @return the list of tasks obtained from the stored data in the file.
     */
    public TaskList readDataFromFile() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                String taskData = readFile.nextLine();
                if (taskData.startsWith("T")) {
                    String[] listOfData = taskData.split("!@#", 3);
                    Task newTodo = new ToDo(listOfData[2]);
                    if (listOfData[1].equals("1")) {
                        newTodo.completed();
                    }
                    taskList.addTask(newTodo);
                } else if (taskData.startsWith("D")) {
                    String[] listOfData = taskData.split("!@#", 4);
                    LocalDate localDate = LocalDate.parse(listOfData[3]);
                    Task newDeadline = new Deadline(listOfData[2], localDate);
                    if (listOfData[1].equals("1")) {
                        newDeadline.completed();
                    }
                    taskList.addTask(newDeadline);
                } else if (taskData.startsWith("E")) {
                    String[] listOfData = taskData.split("!@#", 5);
                    LocalDate startDate = LocalDate.parse(listOfData[3]);
                    LocalDate endDate = LocalDate.parse(listOfData[4]);
                    Task newEvent = new Event(listOfData[2], startDate, endDate);
                    if (listOfData[1].equals("1")) {
                        newEvent.completed();
                    }
                    taskList.addTask(newEvent);
                } else {
                    //
                }
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * Method that writes data to the file located at the file path.
     *
     * @param taskList the list of tasks to be stored in the file.
     */
    public void writeDataIntoFile(TaskList taskList) {
        try {
            FileWriter writeFile = new FileWriter(filePath);
            while (taskList.size() != 0) {
                Task task = taskList.deleteTask(0);
                writeFile.write(task.getData());
                writeFile.write("\n");
            }
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
