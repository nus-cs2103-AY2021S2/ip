import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int counter = 0;
        Duke.printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke.printHorizontalLine();
        while(true) {
            String s = sc.nextLine();
            if(!s.equals("bye") && !s.equals("list")) {
                Duke.printHorizontalLine();
                System.out.printf("added: %s%n",s);
                Duke.printHorizontalLine();
                list[counter++] = s;
            } else if(s.equals("list")) {
                Duke.printHorizontalLine();
                for(int i = 0; i < counter; i++) {
                    int j = i + 1;
                    System.out.printf("%d. %s%n", j, list[i]);
                }
                Duke.printHorizontalLine();

            } else {
                Duke.printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                Duke.printHorizontalLine();
                break;
            }
        }
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}