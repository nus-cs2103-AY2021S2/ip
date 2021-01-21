
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
        String[] strArr = new String[100];
        int count = 0;

        while(sc.hasNextLine()){
            System.out.println("    ____________________________________________________________");
            String str = sc.nextLine();
            if(str.equals("bye")){
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else if(str.equals("list")){
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
            }
            else{
                strArr[count] = str;
                System.out.println("    added: " + strArr[count]);
                System.out.println("    ____________________________________________________________\n");
                count++;
            }
        }

    }
}
