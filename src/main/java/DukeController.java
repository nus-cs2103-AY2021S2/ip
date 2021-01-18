import java.util.Scanner;

public class DukeController {

    private DukeList list;

    DukeController() {
        this.list = new DukeList();
    }

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void run() {
        this.introduction();

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        boolean validState = true;

        while (validState) {
            String[] doneLine = line.split(" ", 2);
            line = doneLine[0]; // get the first keyword
            String line2 = "";

            if (doneLine.length != 1) {
                line2 = doneLine[1];
            }

            if (line.equals("bye")) {
                this.goodbye();
                validState = false;
                continue;
            } else {
                switch (line) {
                    case ("list"):
                        this.list.listItems();
                        break;
                    case ("done"):
                        this.list.done(Integer.parseInt(doneLine[1]));
                        break;
                    case ("todo"):
                        System.out.println(doneLine[1]);
                        this.list.add(new ToDos(doneLine[1]));
                        break;
                    case ("deadline"):
                        String[] info = doneLine[1].split(" /by ");
                        this.list.add(new Deadlines(info[0], info[1]));
                        break;
                    case ("event"):
                        String[] info2 = doneLine[1].split(" /at ");
                        this.list.add(new Events(info2[0], info2[1]));
                        break;
                    default:
                        list.add(line + " " + line2);

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
}

