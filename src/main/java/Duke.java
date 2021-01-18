import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<Task> taskList= new ArrayList<Task>(100);
        Scanner sc= new Scanner(System.in);
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);


        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] command = line.split(" ",2);
            if (command[0].equals("bye")){
                break;
            } else {
                if(command[0].equals("list")){
                    System.out.println("____________________________________________________________\n"+
                            "Here are the tasks in your list");
                    for(int i=1;i<=taskList.size();i++){
                        Task curTask = taskList.get(i-1);
                        System.out.println(i+"."+curTask.toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if(command[0].equals("done")){
                    int index = Integer.valueOf(command[1])-1;
                    Task curTask = taskList.get(index);
                    curTask.markAsDone();
                    System.out.println("____________________________________________________________\n"+
                            "Nice! I've marked this task as done:\n" +curTask.toString()+
                            "\n____________________________________________________________");

                }
                else {
                    if (command[0].equals("todo")) {
                        String[] item=command[1].split("/");
                        Task newTask = new ToDos(item[0]);
                        taskList.add(newTask);
                        System.out.println("____________________________________________________________\n" +
                                "Got it. I've added this task:\n  " + newTask.toString() +
                                "\nNow you have "+taskList.size()+" tasks in the list.\n"+
                                "____________________________________________________________");
                    } else if (command[0].equals("deadline")){
                        String[] item = command[1].split("/by ");
                        Task newTask = new Deadline(item[0],item[1]);
                        taskList.add(newTask);
                        System.out.println("____________________________________________________________\n" +
                                "Got it. I've added this task:\n  " + newTask.toString() +
                                "\nNow you have "+taskList.size()+" tasks in the list.\n"+
                                "____________________________________________________________");

                    } else if (command[0].equals("event")){
                        String[] item=command[1].split("/at ");
                        Task newTask = new Events(item[0],item[1]);
                        taskList.add(newTask);
                        System.out.println("____________________________________________________________\n" +
                                "Got it. I've added this task:\n  " + newTask.toString() +
                                "\nNow you have "+taskList.size()+" tasks in the list.\n"+
                                "____________________________________________________________");

                    }
                }
            }
        }
        System.out.println("____________________________________________________________\n"
                +"Bye. Hope to see you again soon!"
                + "\n____________________________________________________________");


    }
}
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "["+this.getStatusIcon()+"] "+ this.description;
    }

}
class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
class ToDos extends Task {

    protected String by;

    public ToDos(String description) {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class Events extends Task {

    protected String duration;

    public Events(String description,String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration + ")";
    }
}