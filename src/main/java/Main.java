import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Chatbox chatbox = new Chatbox();
        Scanner sc = new Scanner(System.in);

        chatbox.initialize();

        String currCommand = "";

        while(!currCommand.equals("bye")){
            currCommand = sc.next();
            chatbox.acceptCommand(currCommand);
        }

        chatbox.end();

        sc.close();
    }
}
