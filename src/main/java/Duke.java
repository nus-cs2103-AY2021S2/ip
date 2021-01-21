
import java.util.*;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        /*
        System.out.println("    ____________________________________________________________\n" +
                            "    Hello! I'm Duke\n" + "    What can I do for you?\n" +
                            "    ____________________________________________________________\n");

         */
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        //overall stringList
        String[] strArr = new String[100];
        //overall Task count
        int count = 0;
        //overall TaskList
        ArrayList<Task> TaskList = new ArrayList<>();

        while(sc.hasNextLine()){
            //System.out.println("    ____________________________________________________________");
            String str = sc.nextLine();
            if(str.equals("bye")){
                /*
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                */
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(str.equals("list")){
                if(TaskList.isEmpty()){
                    /*
                    System.out.println("    No tasks in the list");
                    System.out.println("    ____________________________________________________________\n");

                     */
                    System.out.println("No tasks in the list");
                }
                else{
                    //System.out.println("    Here are the tasks in your list:");
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < TaskList.size(); i++){
                        int num = i + 1;
                        //System.out.println("     " + num + "." + TaskList.get(i));
                        System.out.println(num + "." + TaskList.get(i));
                    }
                    //System.out.println("    ____________________________________________________________\n");
                }

            }

            else{
                if(str.contains("done")) {
                    int tNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
                    if(!TaskList.isEmpty() && tNum <= TaskList.size() && tNum > 0){
                        //System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("Nice! I've marked this task as done:");
                        TaskList.get(tNum - 1).markAsDone();
                        /*
                        System.out.println("       " + TaskList.get(tNum - 1) + "\n");
                        System.out.println("    ____________________________________________________________\n");

                         */
                        System.out.println(TaskList.get(tNum - 1));
                    }
                    else{
                        if(TaskList.isEmpty()){
                            //System.out.println("    No tasks in the list");
                            System.out.println("No tasks in the list");
                        }
                        else{
                            //System.out.println("    Invalid task number");
                            System.out.println("Invalid task number");
                        }
                        //System.out.println("    ____________________________________________________________\n");
                    }

                }

                else if(str.contains("todo")){
                    String newStr = str.substring(5);
                    strArr[count] = newStr;
                    count++;
                    //create task
                    //add task to taskList;
                    //TaskList.add(new Todo(newStr));
                    Todo tdtask = new Todo(newStr);
                    TaskList.add(tdtask);
                    /*
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + TaskList.get(count - 1) + "" +
                            "     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");

                     */
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskList.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");

                }
                else if(str.contains("deadline") && str.contains("/by")){
                    //task description
                    String tdStr = str.substring(9, str.indexOf('/') - 1);
                    strArr[count] = tdStr;
                    count++;
                    //task by
                    String tbyStr = str.substring(str.indexOf('/') + 4, str.length());
                    Deadlines dlTask = new Deadlines(tdStr, tbyStr);
                    TaskList.add(dlTask);

                    /*
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + TaskList.get(count - 1) +
                            "\n     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");

                     */
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskList.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");


                }
                else if(str.contains("event") && str.contains("/at")){
                    //task description
                    String tdStr = str.substring(6, str.indexOf('/') - 1);
                    strArr[count] = tdStr;
                    count++;
                    //task at
                    String tatStr = str.substring(str.indexOf('/') + 4, str.length());
                    Events evTask = new Events(tdStr, tatStr);
                    TaskList.add(evTask);

                    /*
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + TaskList.get(count - 1) +
                            "\n     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________\n");

                     */
                    System.out.println("Got it. I've added this task:");
                    System.out.println(TaskList.get(count - 1));
                    System.out.println("Now you have " + count + " tasks in the list.");
                }
                else{
                    System.out.println("NO INPUT MATCHED");
                    //System.out.println("    ____________________________________________________________\n");
                }
            }
        }

    }
}
