public class Chatbot {

    public void welcome(){
        Chatbox.chatbotDisplay("Hello! I am Duke!\nWhat can I do for you?");
    }

    public void goodbye(){
        Chatbox.chatbotDisplay("Bye! Hope to see you again soon.");
    }

    public void response(String userMessage){
        // Level-2: when user calls 'list' :
        if (userMessage.equals("list")) {
            int numOfTasks = Task.getNumOfTasks();
            if (numOfTasks == 0) Chatbox.chatbotDisplay("No Tasks right now!");
            else {
                String[] tasks = Task.getTasks();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < numOfTasks; i++) {
                    String task = tasks[i];
                    String index = Integer.toString(i + 1);
                    builder.append(index + ". ");
                    builder.append(task + "\n");
                }
                String botMessage = builder.toString();
                Chatbox.chatbotDisplay(botMessage);
            }
        }
        // Level-2 : When user add tasks:
        else {
            Task newTask = new Task(userMessage);
            Chatbox.chatbotDisplay("Added: " + userMessage);
        }

    }

    public boolean wantExit(String userMessage){
        return userMessage.equals("bye") ? true:false;
    }

}
