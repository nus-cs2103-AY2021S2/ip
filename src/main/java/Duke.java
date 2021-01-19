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
                        System.out.println(tasks.get(i-1));
                    }
                }
                else{
                    System.out.println("No tasks");
                }
            }
            else if(pre[0].equals("done")){
                if(parseInt(pre[1]) <= tasks.size()) {
                    tasks.set(parseInt(pre[1]) - 1,tasks.get(parseInt(pre[1]) - 1).finish());
                    System.out.println("Nice! I've marked this task as done: \n");
                    System.out.println(tasks.get(parseInt(pre[1]) - 1));
                }
                else{
                    System.out.println("error");
                }
            }
            else{
                System.out.println("Got it. I've added this task:");
                if(pre[0].equals("todo")) {
                    String s = "";
                    for(int i = 1 ; i < pre.length ; i++){
                         s = s + pre[i] + " ";
                    }
                    tasks.add(new Todo(s, tasks.size() + 1, false));
                }
                if(pre[0].equals("deadline")) {
                    String s = "";
                    String t = "";
                    for(int i = 1 ; i < pre.length ; i++){
                        if(pre[i].equals("/by")){
                            for(int j = i+1 ; j < pre.length ; j++){
                                t = t +pre[j] + " ";
                            }
                            t = t.substring(0,t.length()-1);
                            break;
                        }
                        else {
                            s = s + pre[i] + " ";
                        }
                    }
                    tasks.add(new Deadline(s, tasks.size() + 1, false,t));
                }
                if(pre[0].equals("event")) {
                    String s = "";
                    String t = "";
                    for(int i = 1 ; i < pre.length ; i++){
                        if(pre[i].equals("/at")){
                            for(int j = i+1 ; j < pre.length ; j++){
                                t = t +pre[j] + " ";
                            }
                            t = t.substring(0,t.length()-1);
                            break;
                        }
                        else {
                            s = s + pre[i] + " ";
                        }
                    }
                    tasks.add(new Event(s, tasks.size() + 1,false,t));
                }
                System.out.println(tasks.get(tasks.size()-1));
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
            cmd = sc.nextLine();
            pre = cmd.split("\\s+");

        }
        System.out.println("Duke: "+goodbye);



    }
}
