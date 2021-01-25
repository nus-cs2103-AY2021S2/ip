import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;


public class FileLoader {

    protected File f;

    // Load file containing task list information
    public FileLoader(String pathStr) throws IOException {

        File f = new File(pathStr);
        f.getParentFile().mkdirs();
        f.createNewFile();
        this.f = f;
    }

    public void write(TaskList t) {
        try (FileWriter writer = new FileWriter(f, false)){
            for (String s: t.asArrayList()) {
                System.out.println(s);
                writer.write(s+'\n');
            }
        } catch (IOException e) {
            // Should raise error here
            System.out.println("ERROR");
        }
    }

    public TaskList read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            ArrayList<String> tasks = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                tasks.add(line);
            }
            return new TaskList(tasks);

        } catch (IOException e) {
            // Should raise error here
            System.out.println("ERROR");
            return new TaskList();
        }
    }
}
