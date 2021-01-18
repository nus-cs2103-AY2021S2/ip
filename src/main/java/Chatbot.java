public class Chatbot {
    public void welcome(){
        Chatbox.chatbotDisplay("Hello! I am Duke!\nWhat can I do for you?");
    }

    public void goodbye(){
        Chatbox.chatbotDisplay("Bye! Hope to see you again soon.");
    }

    public void response(String userMessage){
        // On level-1, the chatbot's response is only what the user says.
        Chatbox.chatbotDisplay(userMessage);
    }

}
