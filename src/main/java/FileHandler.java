import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                            taskList.add(new ToDo(data[2], TaskType.TODO, data[1] == "1"));
                            break;
                        case "DEADLINE":
                            taskList.add(new Deadline(data[2], TaskType.DEADLINE, data[3], data[1] == "1"));
                            break;
                        case "EVENT":
                            taskList.add(new Deadline(data[2], TaskType.EVENT, data[3], data[1] == "1"));
                            break;
                        default:
                            return taskList;
                    }
                    System.out.println("\t  Your tasks from previous session has been successfully loaded.");
                }
                myReader.close();
            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * T | 1 | join sports club
     */

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
                updatedString += ((Deadline) t).getTime();
            } else if (t.getType().equals(TaskType.EVENT)) {
                updatedString += " | ";
                updatedString += ((Event) t).getTime();
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
