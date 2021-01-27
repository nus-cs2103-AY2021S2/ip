import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
	private final String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

    public void createDirectoryAndFile() {
        try {
            final int i = filePath.lastIndexOf("/");
            String dirPath = filePath.substring(0, i);

            Path dir = Paths.get(dirPath);
            Path path = Paths.get(filePath);
            if (!Files.exists(dir))
                Files.createDirectory(dir);
            Files.createFile(path);
        } catch (IOException e) {
            System.err.println("Parent directory does not exist.");
            e.printStackTrace();
        }
    }

    public void saveFile(List<String> data) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path))
            createDirectoryAndFile();
        try {
            Files.write(path, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Duke cannot save file.");
            e.printStackTrace();
        }
    }

    public List<String> loadFile() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path))
            return null;
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Duke cannot read the file.");
            e.printStackTrace();
            return null;
        }
    }

}
