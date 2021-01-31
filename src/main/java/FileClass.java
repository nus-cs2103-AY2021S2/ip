import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class FileClass {

    public static void main(String[] args) {
        Path path = java.nio.file.Paths.get("C:", "ip", "src", "main", "java", "Duke.java");
        File file = new File(path.toString());
    }
}
