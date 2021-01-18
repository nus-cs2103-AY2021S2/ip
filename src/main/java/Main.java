import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Duke chatbot = new Duke();
        chatbot.Greet();
        String input = s.next();

        while(!input.equals("bye")){
            chatbot.echo(input);
            input = s.next();
        }
        chatbot.exit();
    }
}
