import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileHandler {
    public List<Task> readFile(String pathname) {
        List<Task> taskList = new ArrayList<>();
        try {
            File file = new File(pathname);
            if (!file.createNewFile()) {
                File myObj = new File(pathname);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String[] data = myReader.nextLine().split(" \\| ");
                    if (data.length == 0) {
                        break;
                    }
                    switch (data[0]) {
                        case "TODO":
                            taskList.add(new ToDo(data[2], TaskType.TODO, data[1].equals("1")));
                            break;
                        case "DEADLINE":
                            taskList.add(new Deadline(data[2], TaskType.DEADLINE, LocalDate.parse(data[3]), data[1].equals("1")));
                            break;
                        case "EVENT":
                            taskList.add(new Event(data[2], TaskType.EVENT, LocalDate.parse(data[3]), data[1].equals("1")));
                            break;
                        default:
                            return taskList;
                    }

                }
                myReader.close();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("\t  Your tasks from previous session has been successfully loaded.");
        return taskList;
    }

    public String updateFile(List<Task> taskList, String pathname) {
        String updatedString = "";
        for (Task t : taskList) {
            updatedString += t.getType();
            updatedString += " | ";
            updatedString += (t.getDone() ? 1 : 0);
            updatedString += " | ";
            updatedString += t.getName();
            if (t.getType().equals(TaskType.DEADLINE)) {
                updatedString += " | ";
                updatedString += ((Deadline) t).getTime().toString();
            } else if (t.getType().equals(TaskType.EVENT)) {
                updatedString += " | ";
                updatedString += ((Event) t).getTime().toString();
            }
            updatedString += "\n";
        }
        try {
            FileWriter file = new FileWriter(pathname);
            file.write(updatedString);
            file.close();
            return "\t  Your tasks are successfully saved to the file.\n";
        } catch (IOException e) {
            return "\t  Your tasks fail to saved to the file.\n";
        }

    }
}
