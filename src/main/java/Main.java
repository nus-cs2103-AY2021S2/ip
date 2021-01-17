import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Chatbox chatbox = new Chatbox();
        Scanner sc = new Scanner(System.in);

        chatbox.initialize();

        String currInput = "";

        while(!currInput.equals("bye")){
            currInput = sc.nextLine();

            if(currInput.equals("list")){
                chatbox.acceptCommand("list");
            }else{
                chatbox.acceptCommand("add", currInput);
                System.out.println("added: " + currInput);
            }

        }

        chatbox.end();

        sc.close();
    }
}
