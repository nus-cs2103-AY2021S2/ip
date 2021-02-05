import Commands.Command;
import Tasks.TaskList;

import java.util.Scanner;

public class Ui {

    public void run(TaskList tasks) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            Parser parser = new Parser(sc.nextLine());

            if (!parser.inputsAreValid()) {
                continue;
            }

            Command c = parser.getCommand();
            c.execute(tasks);
            isExit = c.isExit();
        }
    }
}
