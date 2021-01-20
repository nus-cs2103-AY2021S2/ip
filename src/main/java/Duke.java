import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________"+
                "________________\n";
        System.out.println(line + " Hey there! I'm Duke\n" +
                " How can I help you?\n" + line);

        while(sc.hasNext()) {
            String str = sc.nextLine();
            if(str.equals("bye")) {
                break;
            } else {
                System.out.println(line + " " + str + "\n" + line);
            }
        }

        System.out.println(line + " Bye, see you again!\n"
                + line);
    }
}
