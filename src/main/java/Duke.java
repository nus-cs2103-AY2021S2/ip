import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void dukeReply(String reply){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + reply);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeReply("Hello! I'm Duke\n" + "\tWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.equals("bye")){
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(line.equals("list")) {
                String reply = "Here are the tasks in your list:\n";
                for(int i=0; i<list.size(); i++){
                    reply += "\t";
                    reply += (i+1) + ". " + list.get(i);
                    if(i!=list.size()-1) reply += "\n";
                }
                dukeReply(reply);
            } else if(line.contains("done")){
                line = line.substring(4);
                Scanner s = new Scanner(line);
                int index = s.nextInt();
                Task t = list.get(index-1);
                t.isDone = true;
                String reply = "Nice! I've marked this task as done:\n\t  "
                        + t.toString();
                dukeReply(reply);

            } else {
                if(line.contains("todo")){
                    Todo t = new Todo(line.substring(5));
                    list.add(t);
                    dukeReply("Got it. I've added this task:\n\t" + t.toString()
                            + "\n\tNow you have " + list.size() + " task" + (list.size()!=1 ? "s " : " ") + "in the list.");
                } else if(line.contains("deadline")){
                    line = line.substring(9);
                    String[] result = line.split("/by ");
                    Deadline t = new Deadline(result[0],result[1]);
                    list.add(t);
                    dukeReply("Got it. I've added this task:\n\t" + t.toString()
                            + "\n\tNow you have " + list.size() + " task" + (list.size()!=1 ? "s " : " ") + "in the list.");
                } else if(line.contains("event")){
                    line = line.substring(6);
                    String[] result = line.split("/at ");
                    Event t = new Event(result[0],result[1]);

                    list.add(t);
                    dukeReply("Got it. I've added this task:\n\t" + t.toString()
                            + "\n\tNow you have " + list.size() + " task" + (list.size()!=1 ? "s " : " ") + "in the list.");
                }
            }
        }
        sc.close();
    }
}
