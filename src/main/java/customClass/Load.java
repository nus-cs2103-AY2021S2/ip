package customClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Load {
    public void loadData(List<Task> list) throws IOException {
        File f = new File("src/data/tasks.txt");
        Scanner sc = new Scanner(f);

        if (!f.exists()) {
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
            return;
        } else {
            while(sc.hasNext()) {
                String currLine = sc.nextLine();
                String[] currValues = currLine.split("\\s*---\\s*");
                switch(currValues[0]) {
                case "T":
                    Todo t = new Todo(currValues[2], currValues[1].equals("1"));
                    list.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(currValues[2], currValues[1].equals("1"), currValues[3]);
                    list.add(d);
                    break;
                case "E":
                    Event e = new Event(currValues[2], currValues[1].equals("1"), currValues[3]);
                    list.add(e);
                    break;
                default:
                    System.out.println("Error with the written file.");
                }
            }
        }
    }
}
