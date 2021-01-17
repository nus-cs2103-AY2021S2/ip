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
        ArrayList<String> list = new ArrayList<String>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.equals("bye")){
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(line.equals("list")) {
                String reply = "";
                for(int i=0; i<list.size(); i++){
                    if(i!=0) reply += "\t";
                    reply += (i+1) + ". " + list.get(i);
                    if(i!=list.size()-1) reply += "\n";
                }
                dukeReply(reply);
            } else {
                list.add(line);
                dukeReply("added: " + line);
            }
        }
        sc.close();
    }
}
