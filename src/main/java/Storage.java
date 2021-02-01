package main.java;

import java.io.File;
import java.io.FileWriter;

public class Storage {

    public Storage() {

    }

    public static void saveTaskList() {
        try {
            File file = new File("src\\main\\java\\duke.txt");
            FileWriter fw = new FileWriter(file);
            //PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < Parser.getTaskList().size(); ++i) {
                fw.write(Parser.getTaskList().get(i).toString() + "\n");
            }
            fw.close();
            //pw.close();
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
    }



}
