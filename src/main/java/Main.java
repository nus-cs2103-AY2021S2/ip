import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Chatbox chatbox = new Chatbox();
        Scanner sc = new Scanner(System.in);

        chatbox.initialize();

        String currInput = "";

        while(true){
            currInput = sc.nextLine();

            if(currInput.equals("bye")) break;

            chatbox.acceptInput(currInput);
            chatbox.executeCommand();
        }

        chatbox.end();

        sc.close();
    }
}
