import java.util.Scanner;

public class  Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        while (!str.equals("bye")) {
            System.out.println(str);
            str = sc.next();
        }
        System.out.println("Bye friend, see you soon!");
    }
}
