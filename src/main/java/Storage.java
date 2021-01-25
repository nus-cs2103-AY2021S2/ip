import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public String getPath(){
        return this.filePath;
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        File f = new File(this.filePath); // create a File for the given file path
        Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            tasks.add(scanner.nextLine());
        }
        return tasks;
    }
}
