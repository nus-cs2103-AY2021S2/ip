import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("Hello! I'm Benny");
        System.out.println("What can I do for you?");
        System.out.println(line);

        UserInput userInput = new UserInput(sc);
        userInput.executeInput();


    }

}
