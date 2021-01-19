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

        // Level-4 : When user add tasks(T,D,E):
        else {

            StringBuilder builder = new StringBuilder();
            builder.append("Got it! I've added this task:\n");

            // Todo
            if (userMessage.startsWith("todo")){
                int spaceIndex = userMessage.indexOf(" ");
                String todoName = userMessage.substring(spaceIndex+1);
                ToDo todo = new ToDo(todoName);

                builder.append("[" + todo.getStatusIcon() + "] " + todo.getTaskName());
                builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");
            }
            // Deadline
            if (userMessage.startsWith("deadline")){
                int spaceIndex = userMessage.indexOf(" ");
                int dateIndex = userMessage.indexOf('/');
                String deadlineName = userMessage.substring(spaceIndex+1,dateIndex);
                String by = userMessage.substring(dateIndex + 4);
                Deadline deadline = new Deadline(deadlineName,by);

                builder.append("[" + deadline.getStatusIcon() + "] " + deadline.getTaskName());
                builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");
            }
            // Event
            if (userMessage.startsWith("event")){
                int spaceIndex = userMessage.indexOf(" ");
                int dateIndex = userMessage.indexOf('/');
                String eventName = userMessage.substring(spaceIndex+1,dateIndex);
                String at = userMessage.substring(dateIndex + 4);
                Event event = new Event(eventName,at);

                builder.append("[" + event.getStatusIcon() + "] " + event.getTaskName());
                builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");
            }

            String botMessage = builder.toString();
            Chatbox.chatbotDisplay(botMessage);

        }


    }

    public boolean wantExit(String userMessage){
        return userMessage.equals("bye") ? true:false;
    }

}
