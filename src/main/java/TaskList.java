import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    // this should be in a taskList class instead
    public static void saveTasksList(ArrayList<Task> taskList) throws IOException {
        File f = new File(Save.taskListFilePath.toString());
            // doesn't actually create a new file i think, converts an existing file

        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Task t : taskList) {
            bw.write(t.unparse());
        }

        bw.flush();
        bw.close();
        System.out.println("here");
    }
}
