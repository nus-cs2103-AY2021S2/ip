import main.java.DukeException;
import main.java.ListManager;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        ListManager listManager = new ListManager();

        System.out.println(listManager.welcomeLine());
        String userinput = scanner.nextLine();

        while ( !userinput.equals("bye")){

            try {
                String outputString = listManager.handleAllUserInput(userinput);
                System.out.println(outputString);
            }catch(DukeException e){
                System.out.println(e.getMessage());
            }

            userinput = scanner.nextLine();
        }
        System.out.println(listManager.goodbyeLine());
    }
}
