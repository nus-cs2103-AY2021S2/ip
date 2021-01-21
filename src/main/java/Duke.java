import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //test push, it doesnt work!
        //starts by greeting the user
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        //echos commands entered by the user
        while(!str.equals("bye")){
            System.out.println(str+"\n");
            str = sc.nextLine();
        }

        //exits when the user types bye
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
