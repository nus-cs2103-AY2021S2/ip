import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;

public class Storage {
    private static String fileName;

    public ArrayList<Task> load(String fileName) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            //TODO refractor to Praser.java
            File f = new File(fileName);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String strTask = s.nextLine();
                String[] taskArr = strTask.split(",");
                Task task = null;
                switch (taskArr[0]) {
                case "T":
                    task = new Todo(taskArr[2]);
                    break;
                case "E":
                    String[] deadlineArr = { taskArr[3] , taskArr[4]};
                    LocalDateTime[] deadlines = new LocalDateTime[2];
                    for(int i = 0 ; i < deadlineArr.length; i++) {
                        deadlines[i] = Parser.parseDate(deadlineArr[i]);
                    }
                    task = new Event(taskArr[2], deadlines[0], deadlines[1]);
                    break;
                case "D":
                    LocalDateTime deadline = Parser.parseDate(taskArr[3]);
                    task = new Deadline(taskArr[2], deadline);
                }
                if (taskArr[1] == "1") {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            this.fileName = fileName;
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }
        return taskList;
    }

    public void save(Collection<Task> taskList) {
        try{
            FileWriter fw = new FileWriter(this.fileName);
            for(Task t : taskList) {
                fw.write(t.save());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }
}
