import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Boolean continueProgram = true;
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);


        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")){
                break;
            } else {
                System.out.println(
                        "____________________________________________________________\n"
                                + command
                                + "\n____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________\n"
                +"Bye. Hope to see you again soon!"
                + "\n____________________________________________________________");


    }
}
