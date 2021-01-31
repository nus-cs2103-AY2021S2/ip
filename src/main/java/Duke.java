import java.util.List;
import java.util.ArrayList;
public class Duke {
    List<Task> list;

    public Duke(){
        this.list = new ArrayList<>();
    }
    public void greet(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void echo(String input){
        System.out.println(input);

    }

    public void addToList(Task task){
        this.list.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + String.valueOf(this.list.size()) + " tasks in the list.");
    }

    public void addToBot(Task task){
        this.list.add(task);
    }

    public void printList(){
        int counter = 1;
        if(this.list.isEmpty()){
            System.out.println("There are no tasks!");
        }
        for(Task element:this.list){
            System.out.println(String.valueOf(counter) + ". " + element);
            counter++;
        }
    }


    public void markTaskAsDone(int id){
        Task task = this.list.get(id-1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] "+ task.content);
    }

    public void deleteTask(int id){
        Task task = this.list.get(id-1);
        this.list.remove(id-1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("Now you have " + String.valueOf(this.list.size()) + " tasks in the list.");

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
