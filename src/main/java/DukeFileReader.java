import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DukeFileReader {
    private static final String FILE_PATH = "data" + File.separator + "duke.txt";

    private static List<Task> readTaskListFromInternalStorage() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        FileDirectoryChecker.doesFileExist(FILE_PATH);
        File f = new File(FILE_PATH);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();
            String[] taskBreakdown = taskString.split("\\|");
//            for (String str : taskBreakdown) {
//                System.out.println(str + "\n");
//            }
            boolean isTaskDone = taskBreakdown[1].equals("1") ? true : false;
            String taskName = taskBreakdown[2];
            if (taskBreakdown[0].equals("T")) {
                try {
                    Task task = new Todos(taskName);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.add(task);
                } catch (EmptyTaskDukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("D")) {
                try {
                    Task task = new Deadlines(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.add(task);
                } catch (EmptyTaskDukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (taskBreakdown[0].equals("E")) {
                try {
                    Task task = new Events(taskName, taskBreakdown[3]);
                    if (isTaskDone) {
                        task.setDone();
                    }
                    taskList.add(task);
                } catch (EmptyTaskDukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return taskList;
    }

    public static void loadTasksIntoTaskList(List<Task> programTaskList) {
        try {
            List<Task> taskList = readTaskListFromInternalStorage();
            for (Task task : taskList) {
                programTaskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
