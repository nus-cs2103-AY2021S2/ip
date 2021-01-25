import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Chatbot {
    public void welcome(){
        Chatbox.chatbotDisplay("Hello! I am Duke!\nWhat can I do for you?");
    }

    public void goodbye(){
        Chatbox.chatbotDisplay("Bye! Hope to see you again soon.");
    }

    public void response(String userMessage) throws DukeException{

        // Level-2: when user calls 'list' :
        if (userMessage.equals("list")) {
            doList();
        }

        // Level-3 : When user want a task done:
        else if(userMessage.startsWith("done")){
            doDone(userMessage);
        }

        // Level-4 : When user add tasks(T,D,E):
        // Todo
        else if (userMessage.startsWith("todo")) doAddTodo(userMessage);
        // Deadline
        else if (userMessage.startsWith("deadline")) doAddDeadline(userMessage);
        // Event
        else if (userMessage.startsWith("event")) doAddEvent(userMessage);

        else if (userMessage.startsWith("delete")) doDelete(userMessage);

        else if (userMessage.startsWith("search time")) doSearchByTime(userMessage);

        else throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

    // These will only be used in the response methods.
    private void doList(){
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
    private void doDone(String userMessage) throws DukeException {
        String [] arr = userMessage.split("\\s+");
        //Exception: If the input is like done 1 2 3:
        if (arr.length > 2) throw new DukeException("OOPS!!! The description of a done is wrong.");

        try {
            // Possible exceptions like done A.
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
        catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a done is wrong.");
        }
        catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! The event index of a done is wrong.");
        }
    }

    private void doAddTodo(String userMessage) throws DukeException{
        StringBuilder builder = new StringBuilder();
        builder.append("Got it! I've added this task:\n");
        int spaceIndex = userMessage.indexOf(" ");
        if (spaceIndex == -1) throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        String todoName = userMessage.substring(spaceIndex+1);
        ToDo todo = new ToDo(todoName);

        builder.append("[" + todo.getStatusIcon() + "] " + todo.getTaskName());
        builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");

        String botMessage = builder.toString();
        Chatbox.chatbotDisplay(botMessage);

    }
    private void doAddDeadline(String userMessage) throws DukeException{
            StringBuilder builder = new StringBuilder();
            builder.append("Got it! I've added this task:\n");

            int spaceIndex = userMessage.indexOf(" ");
            int dateIndex = userMessage.indexOf('/');
            if (dateIndex == -1) throw new DukeException("OOPS!!! I can't find your deadline time.");
            if (spaceIndex == -1 || dateIndex - spaceIndex == 1) throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");

            String deadlineName = userMessage.substring(spaceIndex + 1,dateIndex - 1);
            String by = userMessage.substring(dateIndex + 4);
            Deadline deadline = new Deadline(deadlineName,by);

            builder.append("[" + deadline.getStatusIcon() + "] " + deadline.getTaskName());
            builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");
            String botMessage = builder.toString();
            Chatbox.chatbotDisplay(botMessage);
    }
    private void doAddEvent(String userMessage) throws DukeException{
            StringBuilder builder = new StringBuilder();
            builder.append("Got it! I've added this task:\n");
            int spaceIndex = userMessage.indexOf(" ");
            int dateIndex = userMessage.indexOf('/');
            if (dateIndex == -1) throw new DukeException("OOPS!!! I can't find your event time.");
            if (spaceIndex == -1 || dateIndex - spaceIndex == 1) throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            String eventName = userMessage.substring(spaceIndex + 1,dateIndex - 1);
            String at = userMessage.substring(dateIndex + 4);
            Event event = new Event(eventName,at);

            builder.append("[" + event.getStatusIcon() + "] " + event.getTaskName());
            builder.append("\nNow you have " + Integer.toString(Task.getNumOfTasks()) + " tasks in the list.");
            String botMessage = builder.toString();
            Chatbox.chatbotDisplay(botMessage);
        }

    private void doDelete(String userMessage) throws DukeException{
        String [] arr = userMessage.split("\\s+");
        //Exception: If the input is like delete 1 2 3:
        if (arr.length > 2) throw new DukeException("OOPS!!! The description of a delete is wrong.");
        try {
            // Possible exceptions like delete A.
            int taskIndex = Integer.valueOf(arr[1]) - 1;
            LinkedList<Task> tasks = Task.getTasks();
            Task task = tasks.get(taskIndex);

            StringBuilder builder = new StringBuilder();
            builder.append("Noted. I've removed this task:\n");
            builder.append("["+task.getStatusIcon()+"]"+task.getTaskName());
            task.delete();
            builder.append("\nNow you have " + Task.getNumOfTasks() + " tasks in the list.");
            String botMessage = builder.toString();
            Chatbox.chatbotDisplay(botMessage);


        }
        catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of a delete is wrong.");
        }
        catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! The event index of a delete is wrong.");
        }
    }

    // Sample input: "search time 2020-01-01 18:00", this is an exact search, the next step would be add vague search.
    private void doSearchByTime(String userMessage) throws DukeException{
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] info;
        LocalDateTime time;

        // prevent input mistakes.
        try{
            info = userMessage.split(" ",3);
            time = LocalDateTime.parse(info[2],df);
        }
        catch (Exception e){
            throw new DukeException("The search input format is wrong, the format should be yyyy-MM-dd HH:mm!");
        }

        LinkedList<Task> tasks = Task.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the search results: \n");
        int numOfTasksFound = 0;

        // Search by loop
        for (Task single : tasks) {
            if (single instanceof ToDo){
                continue;
            }
            else if (single instanceof Event){
                LocalDateTime eventTime = ((Event) single).getAt();
                if (eventTime.isEqual(time)){
                    builder.append("[" + single.getStatusIcon() + "]" + single.getTaskName() + "\n");
                    numOfTasksFound++;
                }
            }
            else if (single instanceof Deadline){
                LocalDateTime deadlineTime = ((Deadline) single).getBy();
                if (deadlineTime.isEqual(time)){
                    builder.append("[" + single.getStatusIcon() + "]" + single.getTaskName() + "\n");
                    numOfTasksFound++;
                }
            }
        }


        if (numOfTasksFound == 0){
            throw new DukeException("OOPS! There is no task that matches the time.");
        }

        String botMessage = builder.toString();
        Chatbox.chatbotDisplay(botMessage);

    }

    public boolean wantExit(String userMessage){
        return userMessage.equals("bye") ? true:false;
    }

}
