import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: " + hello);
        String cmd = sc.nextLine();
        String[] pre = cmd.split("\\s+");
        while(cmd.equals("bye") == false){
            if(cmd.equals("list") == true) {

                if(tasks.size() > 0) {
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(tasks.get(i-1));
                    }
                }
                else{
                    System.out.println("No tasks");
                }

            }
            else if(pre[0].equals("done")){
                if(parseInt(pre[1]) <= tasks.size()) {
                    tasks.get(parseInt(pre[1]) - 1).finish();
                    System.out.println("Nice! I've marked this task as done: \n");
                    System.out.println(tasks.get(parseInt(pre[1]) - 1));
                }
                else{
                    System.out.println("error");
                }
            }
            else{
                System.out.println("added: " + cmd);
                tasks.add(new Task(cmd,tasks.size()+1));
            }
            cmd = sc.nextLine();
            pre = cmd.split("\\s+");

        }
        System.out.println("Duke: "+goodbye);



    }
}
