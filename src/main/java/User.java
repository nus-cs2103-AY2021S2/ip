import java.util.Scanner;

public class User {
    public String inputMessage(Scanner keyboard){
        String message = new String();
        System.out.println("****************** User Message *****************");
        if (keyboard.hasNextLine()) {
            message = keyboard.nextLine();
        }
        System.out.println("*************************************************");
        return message;
    }
}

