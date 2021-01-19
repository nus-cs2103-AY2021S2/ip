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
                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + ". " + tasks.get(i-1));
                    }
                }
                else{
                    System.out.println("No tasks");
                }
            }
            else{
                try {
                    if (pre[0].equals("done")) {
                        if (parseInt(pre[1]) <= tasks.size()) {
                            tasks.set(parseInt(pre[1]) - 1, tasks.get(parseInt(pre[1]) - 1).finish());
                            System.out.println("Nice! I've marked this task as done: \n");
                            System.out.println(tasks.get(parseInt(pre[1]) - 1));
                        } else {
                            System.out.println("error");
                        }
                    }
                    else if(pre[0].equals("delete")){
                        if(parseInt(pre[1]) <= tasks.size()){
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(tasks.get(parseInt(pre[1])-1));
                            tasks.remove(parseInt(pre[1])-1);
                            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());

                        }
                        else{
                            System.out.println("error");
                        }
                    }
                    else if (pre[0].equals("deadline")) {
                        String s = "";
                        String t = "";
                        String[] pre2 = cmd.split("/by");
                        try {
                            s = pre2[0];
                            t = pre2[1];
                            System.out.println("Got it. I've added this task:");
                            tasks.add(new Deadline(s, false, t));
                            System.out.println(tasks.get(tasks.size() - 1));
                            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        }
                        catch (Exception e){
                            System.out.println("Please enter a description for deadline");
                        }
                    } else if (pre[0].equals("event")) {
                        String s = "";
                        String t = "";
                        String[] pre2 = cmd.split("/at");
                        try {
                            s = pre2[0];
                            t = pre2[1];
                            System.out.println("Got it. I've added this task:");
                            tasks.add(new Deadline(s, false, t));
                            System.out.println(tasks.get(tasks.size() - 1));
                            System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        }
                        catch (Exception e){
                            System.out.println("Please enter a description for event");
                        }
                    }
                    else if (pre[0].equals("todo")) {
                            String s = "";
                            for (int i = 1; i < pre.length; i++) {
                                s =
                                        s + pre[i] + " ";
                            }
                            if (!s.equals("")) {
                                tasks.add(new Todo(s,false));
                                System.out.println("Got it. I've added this task:");
                                System.out.println(tasks.get(tasks.size() - 1));
                                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                            } else {
                                System.out.println("Please enter a description for todo");
                            }
                    }
                    else{
                        System.out.println("Command not understood");
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid command");
                }
            }
            cmd = sc.nextLine();
            pre = cmd.split("\\s+");
        }
        System.out.println("Duke: "+goodbye);
    }
}
