import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DukeList {
    private final List<DukeTask> list;

    public DukeList() {
        this.list = new ArrayList<DukeTask>();
    }

    public DukeList(List<DukeTask> list) {
        this.list = list;
    }

    public void add(String item) {
        this.list.add(new DukeTask(item));
        System.out.println("added: " + item + "\n");
    }

    public void add(DukeTask task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", this.list.size()));
    }

    public void listItems() {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (DukeTask items : this.list) {
            System.out.println(String.format("%d.%s", i, items));
            i++;
        }
        System.out.println("");
    }

    public void done(int index) {
        DukeTask task = this.list.get(index - 1).markDone();
        this.list.set(index - 1, task);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task + "\n");
    }

    public void delete(int index) {
        DukeTask task = this.list.get(index - 1);
        this.list.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println(String.format("Now you have %d tasks in the list.\n", this.list.size()));
    }

    public int size() {
        return this.list.size();
    }

    public void load(File loadfile) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(loadfile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] moreData = data.split(" [|] ", 2);
                String taskType = moreData[0];
                try {
                    switch (taskType) {
                        case ("T"):
                            String info[] = moreData[1].split(" [|] ", 2);
                            if (info[0].equals("1")) {
                                this.list.add(new ToDos(info[1], true));
                            } else {
                                this.list.add(new ToDos(info[1], false));
                            }
                            break;
                        case ("D"):
                            String info2[] = moreData[1].split(" [|] ", 3);

                            if (info2[0].equals("1")) {
                                this.list.add(new Deadlines(info2[1], true, info2[2]));
                            } else {
                                this.list.add(new Deadlines(info2[1], false, info2[2]));
                            }
                            break;
                        case ("E"):
                            String info3[] = moreData[1].split(" [|] ", 3);
                            if (info3[0].equals("1")) {
                                this.list.add(new Events(info3[1], true, info3[2]));
                            } else {
                                this.list.add(new Events(info3[1], false, info3[2]));
                            }
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("It seems one of your tasks is missing some info:");
                    System.out.println("-->    " + data);
                    System.out.println("We will be skipping this task!\n");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading!!");
        }
    }

    public List<String> savefile() {
        List<String> lister = new ArrayList<>();

        for (DukeTask dukes : this.list) {
            lister.add(dukes.formatDuke());
        }
        return lister;
    }

    public void save(Path p) throws IOException {
         Files.write(p, this.savefile());
    }

}
