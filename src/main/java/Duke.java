import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String line = "--------------------------------------------\n";
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: " + hello);
        String cmd = sc.nextLine();
        while(cmd.equals("bye") == false){
            System.out.println("Duke: "+ cmd);
            System.out.println(line);
            cmd = sc.nextLine();
        }
        System.out.println("Duke: "+goodbye);



    }
}
