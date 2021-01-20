import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
            List<String> ls = new ArrayList<>();
            String s = "";
            while(!(s=sc.nextLine()).equals("bye")) {
                if (!s.equals("list")) {
                    ls.add(s);
                    System.out.println("added: " + s);
                }
                else {
                    for(int i = 0; i < ls.size() ; i++) {
                        System.out.println((i + 1 + ". " + ls.get(i)));
                    }
                }
            }
            System.out.println("Bye. Hope to see you again soon!");
        }
}
