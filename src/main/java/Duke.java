
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

        while(sc.hasNext()){
            System.out.println("    ____________________________________________________________");
            String str = sc.next();
            if(str.equals("bye")){
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else{
                System.out.println("    " + str);
                System.out.println("    ____________________________________________________________\n");
            }
        }

    }
}
