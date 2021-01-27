import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                        newTodo.completeTask();
                    }
                    taskList.addTask(newTodo);
                } else if (taskData.startsWith("D")) {
                    String[] listOfData = taskData.split("!@#", 4);
                    LocalDate localDate = LocalDate.parse(listOfData[3]);
                    Task newDeadline = new Deadline(listOfData[2], localDate);
                    if (listOfData[1].equals("1")) {
                        newDeadline.completeTask();
                    }
                    taskList.addTask(newDeadline);
                } else if (taskData.startsWith("E")) {
                    String[] listOfData = taskData.split("!@#", 5);
                    LocalDate startDate = LocalDate.parse(listOfData[3]);
                    LocalDate endDate = LocalDate.parse(listOfData[4]);
                    Task newEvent = new Event(listOfData[2], startDate, endDate);
                    if (listOfData[1].equals("1")) {
                        newEvent.completeTask();
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
