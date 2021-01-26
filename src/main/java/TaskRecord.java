import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class TaskRecord {

    public static void taskRecorder(String path) {
        File taskHistory = new File(path);
        if (!taskHistory.exists()) {
            try {
                taskHistory.createNewFile();
                FileWriter fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void taskHistory(String path) {
        try {
            File history = new File(path);
            Scanner s = new Scanner(history);
            while (s.hasNext()) {
                String str = s.nextLine();
                System.out.println(str);
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("user history is empty");
        }

    }

    public static void record(ArrayList<Task> tasklist, String path) {
        try {
            int i = 1;
            FileWriter fw = new FileWriter(path);
            for (Task t : tasklist) {
                fw.write(i + ". "
                        + t.toString() + System.lineSeparator());
                i++;
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadTask(String path) {
        try {
            File history = new File(path);
            Scanner s = new Scanner(history);
            while(s.hasNext()) {
                String task = s.nextLine();
                if(task.contains("[T]")) {
                    String[] content = task.split(" ");
                    String description = content[3];
                    Todo myTask = new Todo(description);
                    if(content[1].contains("\u2713")) {
                        myTask.markAsDone();
                    }
                    Duke.taskList.add(myTask);

                } else if (task.contains("[D]")) {
                    String[] content = task.split("by: ");
                    String description = content[3];


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
