package duke;

import java.util.Scanner;
public class Duke {

    static void run(Parser parser, Scanner sc) {
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            parser.parse(command);
            if(command.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        Ui.greet();
        run(new Parser(taskList), sc);
    }
}
