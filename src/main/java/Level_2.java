import java.util.*;

public class Level_2 {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Duke bot = new Duke();

        while(!(input.equals("bye"))) {
            if (input.equals("list")) {
                bot.printList();
            }else{
                bot.addToList(input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
