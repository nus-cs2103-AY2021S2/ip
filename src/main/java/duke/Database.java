package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Database acts as a storage to store tasklist as Strings
 */

public class Database {

    private String name;

    /**
     * Initialize a database object to handle loading and saving of tasks.
     *
     * @param name The name of the path of the file
     */
    public Database(String name){
        this.name = name;
    }


    /**
     * Read from file and initialize Strings of tasks
     * @return list of strings representing tasks toString method.
     */

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


    /**
     * Write to file and update strings
     * @params Updated list of tasks.
     */

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
