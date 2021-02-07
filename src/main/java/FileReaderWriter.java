import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileReaderWriter {
    public ArrayList<Task> getMemList() throws IOException {
        File dir = new File("data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File("data\\duke.txt");

        ArrayList<Task> memList = new ArrayList<>();

        if (f.exists()) {
            // fill with stored data
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(" \\| ");
                String type = input[0];
                Task task;
                if (type.equals("T")) {
                    // todo
                    task = new ToDo(input[2]);
                } else if (type.equals("D")) {
                    // deadline
                    task = new Deadline(input[2], input[3]);
                } else {
                    // event
                    task = new Event(input[2], input[3]);
                }

                // change status
                if (input[1].equals("1")) {
                    task.setCompletion(true);
                }

                memList.add(task);
            }
            sc.close();

        } else {
            f.createNewFile();
        }

        return memList;
    }

    public void storeMemList(ArrayList<? extends Task> taskList) throws IOException {
        FileWriter fw = new FileWriter("data\\duke.txt");

        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).toMemString() + "\n");
        }
        fw.close();

    }

}
