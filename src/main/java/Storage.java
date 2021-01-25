import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static final String path = "data/duke.txt";

    public Storage() {
        try {
            File file = new File(path);
            file.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLines() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeLines(List<String> lines) {
        try {
            FileWriter fw = new FileWriter(path);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < lines.size(); i++) {
                sb.append(lines.get(i));
                if (i != lines.size() - 1) {
                    sb.append("\n");
                }
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}