import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DukeController {

    private DukeList list;
    private Path data = Paths.get(System.getProperty("user.dir"), "data");
    private Path duke = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    DukeController() {
        this.list = new DukeList();
    }

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void run() throws DukeException {

        try {
            this.loadTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.introduction();

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        boolean validState = true;

        while (validState) {
            String[] doneLine = line.split(" ", 2);
            line = doneLine[0]; // get the first keyword

            if (line.equals("bye")) {
                this.goodbye();
                validState = false;
                continue;
            } else {
                try {
                    switch (line) {
                        case ("list"):
                            this.list.listItems();
                            break;
                        case ("done"):
                            if (doneLine.length == 1) {
                                throw new DukeException("☹ OOPS!!! I need the index of the task you want done.");
                            }
                            int index = Integer.parseInt(doneLine[1]);
                            if (index > this.list.size() || index == 0) {
                                throw new DukeException("☹ OOPS!!! The index needs to be in range of the list.");
                            } else {
                                this.list.done(index);
                            }
                            break;
                        case ("delete"):
                            if (doneLine.length == 1) {
                                throw new DukeException("☹ OOPS!!! I need the index of the task you want done.");
                            }
                            index = Integer.parseInt(doneLine[1]);
                            if (index > this.list.size() || index == 0) {
                                throw new DukeException("☹ OOPS!!! The index needs to be in range of the list.");
                            } else {
                                this.list.delete(index);
                            }
                            break;
                        case ("todo"):
                            if (doneLine.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            this.list.add(new ToDos(doneLine[1]));
                            break;
                        case ("deadline"):
                            if (doneLine.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            String[] info = doneLine[1].split(" /by ");
                            if (info.length == 1) {
                                throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                            }
                            this.list.add(new Deadlines(info[0], info[1]));
                            break;
                        case ("event"):
                            if (doneLine.length == 1) {
                                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                            }
                            String[] info2 = doneLine[1].split(" /at ");
                            if (info2.length == 1) {
                                throw new DukeException("☹ OOPS!!! The timing of an event cannot be empty.");
                            }
                            this.list.add(new Events(info2[0], info2[1]));
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! Command is not recognized!!");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage() + "\n");
                }

                try {
                    this.saveTasks();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                line = sc.nextLine();
            }
        }

        sc.close();
    }

    public void introduction() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void loadTasks() throws FileNotFoundException {
        System.out.println("--Booting up Application--");
        System.out.println("Checking if loading data exist...\n");

        if (!Files.exists(data)) {
            System.out.println("Oops! You don't seem to have a load file!");
            System.out.println("Creating one now!!");
        }
        try {
            Files.createDirectory(data);
            Files.createFile(duke);
        } catch (IOException e) {
            System.out.println("Nice! We found your load file!");
            System.out.println("Loading...\n");
        }

        File loadData = duke.toFile();
        this.list.load(loadData);
        System.out.println("--- LOADING COMPLETE! ---\n");
    }

    public void saveTasks() throws IOException {
        this.list.save(duke);
    }
}

