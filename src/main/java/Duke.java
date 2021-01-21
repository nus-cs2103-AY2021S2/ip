
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________\n" +
                            "    Hello! I'm Duke\n" + "    What can I do for you?\n" +
                            "    ____________________________________________________________\n");
        //overall stringList
        String[] strArr = new String[100];
        //overall Task count
        int count = 0;
        //overall TaskList
        ArrayList<Task> TaskList = new ArrayList<>();

        while(sc.hasNextLine()){
            System.out.println("    ____________________________________________________________");
            String str = sc.nextLine();
            if(str.equals("bye")){
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else if(str.equals("list")){

                /*
                if(count == 0){
                    System.out.println("    No items in the list");
                    System.out.println("    ____________________________________________________________\n");
                }
                else{
                    for(int i = 0; i < count; i++){
                        int num = i + 1;
                        System.out.println("    " + num + ". " + strArr[i]);
                    }
                    System.out.println("    ____________________________________________________________\n");
                }
                */

                if(TaskList.isEmpty()){
                    System.out.println("    No tasks in the list");
                    System.out.println("    ____________________________________________________________\n");
                }
                else{
                    System.out.println("    Here are the tasks in your list:");
                    for(int i = 0; i < TaskList.size(); i++){
                        int num = i + 1;
                        System.out.println("     " + num + "." + TaskList.get(i));
                    }
                    System.out.println("    ____________________________________________________________\n");
                }

            }

            else{
                if(str.contains("done")) {
                    int tNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
                    if(!TaskList.isEmpty() && tNum <= TaskList.size() && tNum > 0){
                        System.out.println("    Nice! I've marked this task as done:");
                        TaskList.get(tNum - 1).markAsDone();
                        System.out.println("       " + TaskList.get(tNum - 1));
                        System.out.println("    ____________________________________________________________\n");
                    }
                    else{
                        if(TaskList.isEmpty()){
                            System.out.println("    No tasks in the list");
                        }
                        else{
                            System.out.println("    Invalid task number");
                        }
                        System.out.println("    ____________________________________________________________\n");
                    }

                }
                else {
                    strArr[count] = str;
                    System.out.println("    added: " + strArr[count]);
                    System.out.println("    ____________________________________________________________\n");
                    count++;

                    //create task
                    Task t = new Task(str);
                    //add task to taskList;
                    TaskList.add(t);
                }
            }
        }

    }
}
