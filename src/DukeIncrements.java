import java.util.Scanner;

public class DukeIncrements {
    public static void main(String[] agrs) {
        System.out.println("yo im Duke!");
        System.out.println("what can i do for ya ;)");
        String line = ":) :) :) :) :) :) :) :) :) :) :) :) :) :) :) :)";
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        int count = 0;
        String[] arr = new String[100];
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("sayonara nerd! c ya soon ;)");
                System.out.println(line);
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + arr[i]);
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
                arr[count] = input;
                count++;
                }
            }
        }
}
