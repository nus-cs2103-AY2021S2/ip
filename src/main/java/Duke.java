import java.util.*;

public class Duke {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;

    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(line + "Hello! I'm Kobe\n" + ind + "What can I do for you?\n" + line);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String text = sc.next();
            if (text.equals("bye")) {
                goodbye();
                break;
            } else {
                echo(text);
            }
        }
        sc.close();
    }

    public static void echo(String echoedText) {
        System.out.println(line + echoedText + "\n" + line);
    }

    public static void goodbye() {
        System.out.println(line + "Bye. Kobe hopes to see you again soon!\n" + line);
    }

}
