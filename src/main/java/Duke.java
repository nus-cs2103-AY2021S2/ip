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
            String[] command = line.split(" ");
            if (command[0].equals("bye")){
                break;
            } else {
                if(command[0].equals("list")){
                    System.out.println("____________________________________________________________\n"+
                            "Here are the tasks in your list");
                    for(int i=1;i<=taskList.size();i++){
                        Task curTask = taskList.get(i-1);
                        System.out.println(i+". ["+curTask.getStatusIcon()+"] "+curTask.getDescription());
                    }
                    System.out.println("____________________________________________________________");
                } else if(command[0].equals("done")){
                    int index = Integer.valueOf(command[1])-1;
                    Task curTask = taskList.get(index);
                    curTask.markAsDone();
                    System.out.println("____________________________________________________________\n"+
                            "Nice! I've marked this task as done:\n"+index+". ["+curTask.getStatusIcon()+"] "+curTask.getDescription()+
                            "\n____________________________________________________________");

                }
                else{
                    Task newTask = new Task(line);
                    taskList.add(newTask);
                    System.out.println("____________________________________________________________\n"+
                            "added: "+ line +"\n____________________________________________________________");
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

    public String getDescription(){
        return this.description;
    }

    public void markAsDone(){
        this.isDone = true;
    }

}