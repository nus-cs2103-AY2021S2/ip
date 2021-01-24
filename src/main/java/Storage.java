import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Storage {
    private File file;

    Storage() {
        this.file = new File("data", "duke.txt");
    }

    public void createFile() throws IOException{
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            System.out.println("Directory created");
        }

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("New file created");
        }

    }

    public File getFile() throws IOException {
        createFile();
        return this.file;
    }

    public void update(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for(Task task : taskList.getList()){
            fw.write(task.toSaveFormat() + "\n");

        }
        fw.close();

    }

    public void setUpFile() throws IOException {
        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        else if (!Files.exists(Paths.get("data/Duke.txt"))) {
            Files.createFile(Paths.get("data/Duke.txt"));
        }
    }
}
