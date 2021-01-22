import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataHandler {
    String l;

    public DataHandler(String filePath) {
        this.l = filePath;
    }

    public static void saveData(TaskList li) {
        //potential problem: saveData doesnt update .txt file when i change done status of item to done
        try {
            Path currPath = Paths.get("");
            FileWriter fw = new FileWriter(currPath.toAbsolutePath().toString() + "/duke.txt");
            for (int i = 0; i < li.getSize(); i++) {
                String write = li.getInd(i) + "\n";
                fw.write(write);
            } fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static TaskList loadData() throws FileNotFoundException {
        Path currPath = Paths.get("");
        Path dukePath = Paths.get(currPath.toAbsolutePath().toString() + "/duke.txt");
        TaskList list = new TaskList();

        if (Files.exists(dukePath)) { //load list data
            File info = new File(String.valueOf(dukePath));
            Scanner sc = new Scanner(info);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] arr = task.split("");
                if (arr[1].equals("T")) {
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    String taskDetails = "";
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }

                    Task t = new Todo(taskDetails);
                    if (done) {
                        t.setDone();
                    }
                    list.addToDo(t);
                } else if (arr[1].equals("D")) {
                    String taskDetails = "";
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }
                    arr = taskDetails.split("[(]");
                    String[] deets = arr[0].split("");
                    String description = "";
                    for (int t = 0; t < arr[0].length() - 1; t++) {
                        description += deets[t];
                    }
                    taskDetails = arr[1];
                    System.out.println(Arrays.toString(description.toCharArray()));
                    System.out.println(Arrays.toString(taskDetails.toCharArray()));
                    arr = taskDetails.split(" by:" , 2);
                    System.out.println(Arrays.toString(arr));
                    String by = "";
                    String details = arr[0];
                    arr = details.split("");
                    for (int k = 4; k < arr.length - 1; k++) {
                        by += arr[k];
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                    Task t = new Deadline(description, dateTime);
                    if (done) {
                        t.setDone();
                    }
                    list.addDeadline(t);
                } else if (arr[1].equals("E")) {
                    String taskDetails = "";
                    Boolean done = false;
                    if (arr[4].equals("X")) {
                        done = true;
                    }
                    for (int j = 7; j < arr.length; j++) {
                        taskDetails += arr[j];
                    }
                    arr = taskDetails.split("[(]");
                    String[] deets = arr[0].split("");
                    String description = "";
                    for (int t = 0; t < arr[0].length() - 1; t++) {
                        description += deets[t];
                    }
                    taskDetails = arr[1];
                    arr = taskDetails.split(" at: ");
                    String at = "";
                    String details = arr[0];
                    arr = details.split("");
                    for (int k = 4; k < arr.length - 1; k++) {
                        at += arr[k];
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
                    Task t = new Event(description, dateTime);
                    if (done) {
                        t.setDone();
                    }
                    list.addEvent(t);
                }
            }
        } else { //create new file in folder
//            String folderPath = System.getProperty("user.dir") + "/data";
//            File folder = new File(folderPath);
            File f = new File(currPath.toAbsolutePath().toString() + "/duke.txt");
        }
        return list;
    }
}





















//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class WriteToFile {
//    String f;
//    ArrayList<Task> l;
//
//    public WriteToFile(String f, ArrayList<Task> li) {
//        this.f = f;
//        this.l = li;
//    }
//
//    private static void write(String filePath, ArrayList<Task> list) throws IOException {
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(Arrays.toString(list.toArray()));
//        fw.close();
//    }
//
//    public static void main(String[] args) {
//        Path currPath = Paths.get("");
//        String dukePath = currPath.toAbsolutePath().toString() + "/duke.txt";
//        try {
//            write(dukePath, this.l);
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }
//}
