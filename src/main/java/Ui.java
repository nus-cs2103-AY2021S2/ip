import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private boolean done;
    public void runUi(TaskList taskList, Storage storage) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________\n" +
                "     Duke... booted...\n" +
                "     requesting tasks\n" +
                "    ____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while (!done) {
            String nextCommand = sc.next();
            String nextOutput = parser.parse(nextCommand, taskList, storage, sc, this);
            System.out.println(nextOutput);
        }
        sc.close();
    }

    public void end() {
        done = true;
    }
}
