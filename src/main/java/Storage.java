import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<String> load() throws FileNotFoundException{
        ArrayList<String> res = new ArrayList<>();
        //load file if available
        File store = new File(filePath);
        Scanner myReader = new Scanner(store);
        while (myReader.hasNextLine()) {
            res.add(myReader.nextLine());
        }
        System.out.println("Loaded stored information from ./oracle_data.txt");
        myReader.close();
        return res;
    }

    public void store(ArrayList<Task> tasks) {
        // store data
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (Task task : tasks){
                myWriter.write(task.toStorage()+ '\n');
            }
            myWriter.close();
            System.out.println("Successfully saved the information");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
