import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        ArrayList<String> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: " + hello);
        String cmd = sc.nextLine();
        while(cmd.equals("bye") == false){
            if(cmd.equals("list") == false) {
                System.out.println("added: " + cmd);
                tasks.add(cmd);
            }
            else{
                if(tasks.size() > 0) {
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.print(i);
                        System.out.println(". " + tasks.get(i - 1));
                    }
                }
                else{
                    System.out.println("No tasks");
                }

            }
            cmd = sc.nextLine();

        }
        System.out.println("Duke: "+goodbye);



    }
}
