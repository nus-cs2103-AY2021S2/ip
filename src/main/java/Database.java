import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    String name;

    public Database(String name){
        this.name = name;
    }

    public ArrayList<String> readFile() throws FileNotFoundException {
        try {
            ArrayList<String> tasksStringName = new ArrayList<>();
            File f = new File(name);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasksStringName.add(s.nextLine());
            }
            return tasksStringName;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public  void writeTaskToFile(List<Task> tasks){
        String string = "";
        for (Task task : tasks) {
            string = string + task.toString() + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(name);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


 }

