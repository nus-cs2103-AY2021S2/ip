import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horzLine = "    _________________________________________________";
        List<String> list = new ArrayList<>();

        String logo = " ____        _        \n"
                + "               |  _ \\ _   _| | _____ \n"
                + "               | | | | | | | |/ / _ \\\n"
                + "               | |_| | |_| |   <  __/\n"
                + "               |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(horzLine
                + "\n     Hello! I'm" + logo
                + "\n     What can I do for you?\n"
                + horzLine);

        boolean exit = false;
        while (!exit) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                exit = true;
                System.out.println(horzLine
                        + "\n     Bye. Hope to see you again soon!\n"
                        + horzLine);
            } else if (userInput.equals("list")) {
                System.out.println(horzLine);

                for (int i = 0; i < list.size(); i++) {
                    int number = 1 + i;
                    System.out.println("     " + number + ". " + list.get(i));
                }

                System.out.println(horzLine);
            } else {
                list.add(userInput);
                System.out.println(horzLine
                        + "\n      added: " + userInput + "\n"
                        + horzLine);
            }
        }

        sc.close();
    }
}
