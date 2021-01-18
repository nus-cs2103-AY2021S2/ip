import java.util.LinkedList;

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
            // Exception case
            if (numOfTasks == 0) Chatbox.chatbotDisplay("No Tasks right now!");

            else {
                LinkedList<Task> tasks = Task.getTasks();
                StringBuilder builder = new StringBuilder();
                builder.append("Here are the tasks in your list\n");
                for (int i = 0; i < numOfTasks; i++) {
                    Task task = tasks.get(i);
                    String taskName = task.getTaskName();
                    String icon = task.getStatusIcon();
                    String index = Integer.toString(i + 1);
                    builder.append(index + ". " + "[" + icon + "]");
                    builder.append(taskName + "\n");
                }
                String botMessage = builder.toString();
                Chatbox.chatbotDisplay(botMessage);
            }
        }

        // Level-3 : When user want a task done:
        else if(userMessage.contains("done")){
            String [] arr = userMessage.split("\\s+");
            //Exception (Undone): 可以通过判断arr的长度来判断是否有误输入。

            int taskIndex = Integer.valueOf(arr[1]) - 1;
            LinkedList<Task> tasks = Task.getTasks();
            Task task = tasks.get(taskIndex);


            StringBuilder builder = new StringBuilder();
            builder.append("Nice! I've marked this as done!\n");
            builder.append("["+task.getStatusIcon()+"]"+task.getTaskName());
            String botMessage = builder.toString();
            Chatbox.chatbotDisplay(botMessage);
            
            task.markAsDone();

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
