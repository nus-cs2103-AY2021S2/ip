import java.util.Scanner;

public class Chatbox {
    public static void run(){
        Chatbot robot = new Chatbot();
        User user = new User();
        Scanner keyboard = new Scanner(System.in);

        robot.welcome();

        while (true) {
            String userMessage = user.inputMessage(keyboard);
            if (robot.wantExit(userMessage)) break;
            try{
                robot.response(userMessage);
            }
            catch(DukeException e){
                Chatbox.chatbotDisplay(e.getMessage());
            }
        }
        robot.goodbye();
        keyboard.close();

    }

    public static void chatbotDisplay(String botMessage){
        System.out.println("**************** Chatbot Message ****************");
        System.out.println(botMessage);
        System.out.println("*************************************************");
    }


}
