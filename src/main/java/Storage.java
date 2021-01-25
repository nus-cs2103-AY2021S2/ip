import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    static int upload_from_hard_drive(ArrayList<Task> storage) throws FileNotFoundException {
        int count = 0;
        File f = new File("./data/tasks.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            count++;
            String[] spl = s.nextLine().split("@@@", 4);
            Task to_add;
            switch(spl[0]) {
                case "T":
                    to_add = new todo(spl[2]);
                    break;
                case "D":
                    to_add = new deadline(spl[2], LocalDate.parse(spl[3]));
                    break;
                case "E":
                    to_add = new event(spl[2], LocalDate.parse(spl[3]));
                    break;
                default:
                    to_add = new todo("error");
            }
            if (spl[1].equals("1")) {
                to_add.finished();
            }
            storage.add(to_add);
        }
        return count;
    }

    static void upload_to_hard_drive(ArrayList<Task>
                                             storage) throws IOException {
        FileWriter fw = new FileWriter("./data/tasks.txt");
        String between = "@@@";
        for (Task t : storage) {
            String zero = t.get_initial();
            String one =  t.get_done();
            String two = t.get_description();
            if (zero.equals("T")) {
                fw.write(zero + between + one + between + two + "\n");
            } else {
                LocalDate three = t.get_date();
                fw.write(zero + between + one + between + two + between + three + "\n");
            }
        }
        fw.close();
    }
}
