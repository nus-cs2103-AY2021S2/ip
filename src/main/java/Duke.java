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

        System.out.println(listManager.defaultFormatting("Hello! I'm Duke\n" + "     What can I do for you?"));
        String userinput = scanner.nextLine();

        while ( !userinput.equals("bye")){
            if (userinput.equals("list")){
                System.out.println(listManager.returnTaskList());
            }else{
                listManager.addTask(userinput);
                System.out.println(listManager.defaultFormatting("added: " + userinput));
            }
            userinput = scanner.nextLine();
        }

        System.out.println(listManager.defaultFormatting("Bye. Hope to see you again soon!"));
    }
}
