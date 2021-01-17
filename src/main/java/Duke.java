import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> col = new ArrayList<>(100);
        int i = 1;

        System.out.println("----------------------------------------------");
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
        System.out.println("----------------------------------------------");

        String next = sc.nextLine();
        while(!next.equals("bye")) {
            col.add(next);
            System.out.println("----------------------------------------------");
            System.out.println("added: " + next);
            System.out.println("----------------------------------------------");
            next = sc.nextLine();
            while (next.equals("list")) {
                System.out.println("----------------------------------------------");
                Object[] strarr = col.toArray();
                for (Object s : strarr) {
                    System.out.println(i + ". " + s);
                    i++;
                }
                System.out.println("----------------------------------------------");
                i = 1;
                next = sc.nextLine();
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------------------");
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";