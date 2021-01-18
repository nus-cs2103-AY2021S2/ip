import java.util.Scanner;

public class DukeL1 {
    public static void main(String[] agrs) {
        System.out.println("yo im Duke!");
        System.out.println("what can i do for ya ;)");
        String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";
        System.out.println(line);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String echo = sc.nextLine();
            if (echo.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("sayonara nerd! c ya soon ;)");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println(echo);
                System.out.println(line);
            }
        }
    }
}
