import java.util.*;

public class Duke {
    static final String INDENT = "        ";
    static final String BORDER = INDENT + "__________________________________________________";
    public static void main(String[] args) {
        String logo = "      _       __  __ \n"
                    + "     | |     / _|/ _|  \n"
                    + "     | | ___| |_| |_   \n"
                    + " _   | |/ _ \\  _|  _|  \n"
                    + "| |__| |  __/ | | |    \n"
                    + " \\____/ \\___|_| |_|    \n";
        System.out.println(BORDER);
        System.out.println(INDENT + "Hello from\n" + logo);
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(BORDER + "\n");

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while(true) {
            String msg = sc.next();
            if(msg.equals("bye")) {
                System.out.println(BORDER);
                System.out.println(INDENT + "Bye. Hope to see you again!");
                System.out.println(BORDER + "\n");
                break;
            } else if(msg.equals("list")){
                System.out.println(BORDER);
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(INDENT + (i+1)  + ". " + tasks.get(i).getName());
                }
                System.out.println(BORDER + "\n");
            } else {
                tasks.add(new Task(msg));
                System.out.println(BORDER);
                System.out.println(INDENT + "added: " + msg);
                System.out.println(BORDER + "\n");
            }
        }
    }
}
