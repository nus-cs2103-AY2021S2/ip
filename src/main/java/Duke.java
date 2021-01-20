import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String currString = sc.nextLine();
        while(!currString.equals("bye")){

            System.out.println( line + "\n" + currString +"\n" + line);
            currString = sc.nextLine();
        }
        System.out.println(line + "\n" + " Bye. Hope to see you again soon!"+"\n" + line);


    }
}
