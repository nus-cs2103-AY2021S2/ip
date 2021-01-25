import java.util.Scanner;

public class Ui {

    public String readCommand(Scanner keyboard){
        String message = new String();
        System.out.println("****************** User Message *****************");
        if (keyboard.hasNextLine()) {
            message = keyboard.nextLine();
        }
        System.out.println("*************************************************");
        return message;
    }

    public void welcome(){
        display("Hello! I am Duke!\nWhat can I do for you?");
    }

    public void display(String botMessage){
        System.out.println("**************** Chatbot Message ****************");
        System.out.println(botMessage);
        System.out.println("*************************************************");
    }


}
