import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                bye();
                break;
            }
            command(s);
        }
    }

    static public void command(String str) {
        System.out.println("    ____________________________________________________________\n     " +
                str + "\n" +
                "    ____________________________________________________________\n");
    }

    static public void bye() {
        System.out.println("    ____________________________________________________________\n     " +
                "Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }

    static public void greet() {
        System.out.println("    ____________________________________________________________\n     " +
                " ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n\n     " +
                "Hello! I'm Duke\n     " +
                "What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }
}
