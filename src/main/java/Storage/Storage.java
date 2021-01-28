package Storage;

import Exceptions.DukeException;
import TaskList.TaskList;
import Tasks.Deadlines;
import Tasks.DukeTask;
import Tasks.Events;
import Tasks.Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final Path dataFolder = Paths.get(System.getProperty("user.dir"), "data");
    private final Path dukeTxt = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
    private List<DukeTask> loadfile;

    public Storage() {
        this.loadfile = new ArrayList<>();
    }

    public List<DukeTask> load() throws DukeException {
        return parseFile(this.getFileFromPath());
    }

    public File getFileFromPath() throws DukeException {
        if (!Files.exists(this.dataFolder) || !Files.exists(this.dukeTxt)) {
            System.out.println("Oops! You don't seem to have a load file!");
            System.out.println("Creating one now!!\n");
        }
        try {
            Files.createDirectory(this.dataFolder);
            Files.createFile(this.dukeTxt);
        } catch (IOException e) {
            System.out.println("Nice! We found your load file!");
            System.out.println("Loading...\n");
        }

        File loadData = this.dukeTxt.toFile();
        return loadData;
    }

    public List<DukeTask> parseFile(File data) throws DukeException {
        try {
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] lineData = line.split(" [|] ", 2);
                String taskType = lineData[0];
                try {
                    switch (taskType) {
                    case ("T"):
                        String info[] = lineData[1].split(" [|] ", 2);
                        if (info[0].equals("1")) {
                            this.loadfile.add(new Todos(info[1], true));
                        } else {
                            this.loadfile.add(new Todos(info[1], false));
                        }
                        break;
                    case ("D"):
                        String info2[] = lineData[1].split(" [|] ", 3);

                        if (info2[0].equals("1")) {
                            this.loadfile.add(new Deadlines(info2[1], true, info2[2]));
                        } else {
                            this.loadfile.add(new Deadlines(info2[1], false, info2[2]));
                        }
                        break;
                    case ("E"):
                        String info3[] = lineData[1].split(" [|] ", 3);
                        String[] information = Events.parseEvent(info3[2]);
                        if (info3[0].equals("1")) {
                            this.loadfile.add(new Events(info3[1], true, information[0],
                                    information[1], information[2]));
                        } else {
                            this.loadfile.add(new Events(info3[1], false, information[0],
                                    information[1], information[2]));
                        }
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("It seems one of your tasks is missing some info:");
                    System.out.println("-->    " + line);
                    System.out.println("We will be skipping this task!\n");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("LoadFileError");
        }
        return this.loadfile;
    }

    public void save(TaskList updatedList) {
        List<String> lister = new ArrayList<>();

        for (DukeTask dukes : updatedList.getList()) {
            lister.add(dukes.formatDuke());
        }
        try {
            Files.write(dukeTxt, lister);
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }
}
