package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class Storage {

    private File data;

    /**
     * initializes by finding out the location of the code and creating a folder for the data to be stored in
     */
    Storage() {
        try {
            File jarDir = new File(Parser.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath());
            Path location = Path.of(jarDir.getParentFile().getPath());
            Path dir = location.resolve("data");
            Path file = dir.resolve("list.txt");

            Files.createDirectories(dir);

            boolean fileExists = java.nio.file.Files.exists(file);
            if (!fileExists) {
                Files.createFile(file);
            }

            this.data = new File(file.toUri());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads the data in the file and returns a TaskList
     * @return TaskList created from the data of the file
     */
    ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            BufferedReader br = Files.newBufferedReader(this.data.toPath());
            String line;

            while ((line = br.readLine()) != null) {
                String[] info = line.split("1!1");

                if (info[0].equals("todo")) {
                    list.add(new ToDo(info[1], Boolean.parseBoolean(info[2])));
                } else if (info[0].equals("deadline")) {
                    list.add(new Deadline(info[1], info[2], Boolean.parseBoolean(info[3])));
                } else if (info[0].equals("event")) {
                    list.add(new Event(info[1], info[2], Boolean.parseBoolean(info[3])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * saves the given Task ArrayList into the txt file
     * @param mem Task ArrayList
     */
    void save(ArrayList<Task> mem) {

        ArrayList<String> list = new ArrayList<String>();

        for (Task t : mem) {
            list.add(t.saveName());
        }

        try {
            Files.write(this.data.toPath(), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
