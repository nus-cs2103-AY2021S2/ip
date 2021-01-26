import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;

public class Storage {

    static int uploadFromHardDrive(ArrayList<Task> storage) throws FileNotFoundException {
        int count = 0;
        File f = new File("./data/tasks.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            count++;
            String[] spl = s.nextLine().split("@@@", 4);
            Task to_add;
            switch(spl[0]) {
            case "T":
                to_add = new Todo(spl[2]);
                break;
            case "D":
                to_add = new Deadline(spl[2], LocalDate.parse(spl[3]));
                break;
            case "E":
                to_add = new Event(spl[2], LocalDate.parse(spl[3]));
                break;
            default:
                to_add = new Todo("error");
            }

            if (spl[1].equals("1")) {
                to_add.finished();
            }
            storage.add(to_add);
        }
        return count;
    }

    static void uploadToHardDrive(ArrayList<Task>
                                             storage) throws IOException {
        FileWriter fw = new FileWriter("./data/tasks.txt");
        String between = "@@@";
        for (Task t : storage) {
            String zero = t.getInitial();
            String one =  t.getDone();
            String two = t.getDescription();
            if (zero.equals("T")) {
                fw.write(zero + between + one + between + two + "\n");
            } else {
                LocalDate three = t.getDate();
                fw.write(zero + between + one + between + two + between + three + "\n");
            }
        }
        fw.close();
    }
}
