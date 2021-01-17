import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Chatbox chatbox = new Chatbox();
        Scanner sc = new Scanner(System.in);

        chatbox.initialize();

        String currInput = "";

        while(!currInput.equals("bye")){
            currInput = sc.nextLine();
            Scanner sc2 = new Scanner(currInput);

            String firstWord = sc2.next();

            if(firstWord.equals("done")) {
                chatbox.acceptCommand("done", sc2.next());
            }else if(firstWord.equals("list")){
                chatbox.acceptCommand("list");
            }else{
                chatbox.acceptCommand("add", currInput);
            }

        }

        chatbox.end();

        sc.close();
    }
}
