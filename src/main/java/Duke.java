import java.util.Scanner;

public class  Duke {
    public static void main(String[] args) {
        String[] list = new String[100];
        String[] done = new String[100];
        int numitems = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                for (int i = 0; i < numitems; i++) {
                    System.out.println("Here are the tasks in your list!");
                    System.out.println(i+1 + "." + done[i] + list[i]);
                }

            } else if (str.equals("done")) {

            } else {
                System.out.println("added: " + str);
                done[numitems] = "[ ] ";
                list[numitems] = str;
                numitems += 1;
            }
            str = sc.nextLine();
        }
        System.out.println("Bye friend, see you soon!");
    }
}
