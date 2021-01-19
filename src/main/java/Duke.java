import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String sep = "****************************\n";
        System.out.println(sep);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> msgList = new ArrayList<>();
        while(true) {
            String str = sc.nextLine();

            if(str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if(str.equals("list")) {
                int counter = 1;
                for(String msg: msgList) {
                    System.out.println(counter + ". " + msg);
                    counter++;
                }
            }
            else {
                msgList.add(str);
                System.out.println("added: " + str);
            }
            System.out.println(sep);
        }
    }
}
