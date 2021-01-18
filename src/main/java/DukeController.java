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

            if (doneLine[0].equals("done")) {
                this.list.done(Integer.parseInt(doneLine[1]));
                line = sc.nextLine();
            } else {
                switch (line) {
                    case ("bye"):
                        this.goodbye();
                        validState = false;
                        break;
                    case ("list"):
                        this.list.listItems();
                        line = sc.nextLine();
                        break;
                    default:
                        list.add(line);
                        line = sc.nextLine();
                }
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

