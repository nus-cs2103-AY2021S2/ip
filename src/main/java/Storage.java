

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Storage {
    private ArrayList<String> itemList;
    private String path;

    public Storage (String path) {
        File logFile = new File(path);
        try {
            if (logFile.isFile()) {
                Scanner logs = new Scanner(logFile);
                logs.close();
            } else {
                logFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("no file created " + e);
        }

        this.path = path;
        try {
            this.itemList = new ArrayList<>(Files.readAllLines(Paths.get(path)));
        } catch (IOException e) {
            System.out.println(e);
            this.itemList = new ArrayList<>();
        }
    }
    public void add(Task task) {
        this.itemList.add(task.toString());
        this.update();
    }
    public void set(int index, Task newTask) {
        this.itemList.set(index -1, newTask.toString());
        this.update();
    }
    public void remove(int index) {
        this.itemList.remove(index -1);
        this.update();
    }

    public ArrayList<String> readStorage () {
        return this.itemList;
    }
    public void update() {
        try {
            PrintWriter writer = new PrintWriter(path);
            for (String line : this.itemList) {
                writer.println(line);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}

