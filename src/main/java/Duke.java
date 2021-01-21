import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     Konichiwa~~ Watashi wa \n" + logo + "     desu!\n     What can I do for you today?");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            String command = sc.nextLine();

            List<Task> tasks = new ArrayList<>();

            while(!command.equals("bye")){
                if(command.equals("list")){
                    System.out.println("     Koko wa list of tasks desu~");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("       " + i + ". " + tasks.get(i - 1).toString());
                    }
                    System.out.println("     Ganbatte ne!");

                } else if(command.startsWith("done")){
                    int taskNum = Integer.valueOf(command.substring(5));
                    tasks.get(taskNum-1).setCompleted();
                    System.out.println("     Otsukare! I've marked this task as done:");
                    System.out.println("       " + tasks.get(taskNum - 1).toString());

                } else if(command.startsWith("todo")){
                    Todo curr = new Todo(command.substring(5));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");


                } else if(command.startsWith("deadline")){
                    int cut = command.indexOf("/");
                    Deadline curr = new Deadline(command.substring(9, cut-1), command.substring(cut+4));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");

                } else if(command.startsWith("event")){
                    int cut = command.indexOf("/");
                    Event curr = new Event(command.substring(6, cut-1), command.substring(cut+4));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");


                } else {
                    System.out.println("     Etto, was there an mistake with [" + command + "] ?");
                }

                command = sc.nextLine();
            }

            System.out.println("     Sayonara! Mata ne~ ;)");
        } 
    }
}
