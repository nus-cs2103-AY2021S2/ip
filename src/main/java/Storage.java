import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private BufferedWriter bufferedWriter;

    public Storage() { // default Storage uses current directory as save location
        try {
            FileWriter fileWriter = new FileWriter("duke_saved_tasks");
            this.bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void save(TaskList taskList) {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            for (Task t : tasks) {
                bufferedWriter.write(t.toLog() + "\n");
            }

            bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

