import java.util.*;

public class Duke{
    enum Command{
        list,done,delete,todo,deadline,event,bye;
    }
    public static void main(String[] args) {

            List<Task> taskList = new ArrayList<Task>(100);
            Scanner sc = new Scanner(System.in);
            String logo = "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
            System.out.println(logo);

            while (sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    String[] lineSplit = line.split(" ", 2);
                    Command command = Command.valueOf(lineSplit[0]);
                    if (command.equals(Command.bye)) {
                    break;
                    } else {
                        if (command.equals(Command.list)) {
                            System.out.println("____________________________________________________________\n" +
                                    "Here are the tasks in your list");
                            for (int i = 1; i <= taskList.size(); i++) {
                                Task curTask = taskList.get(i - 1);
                                System.out.println(i + "." + curTask.toString());
                            }
                            System.out.println("____________________________________________________________");
                        } else if (command.equals(Command.done)) {
                            try {
                            int index = Integer.valueOf(lineSplit[1]) - 1;
                            Task curTask = taskList.get(index);
                            curTask.markAsDone();
                            System.out.println("____________________________________________________________\n" +
                                    "Nice! I've marked this task as done:\n" + curTask.toString() +
                                    "\n____________________________________________________________");
                            }catch (ArrayIndexOutOfBoundsException ex){
                                throw new DukeException("\u00a9 OOPS!!! The description of a done cannot be empty.");
                            }
                        } else if (command.equals(Command.delete)) {
                            try {
                                int index = Integer.valueOf(lineSplit[1]) - 1;
                                Task curTask = taskList.get(index);
                                taskList.remove(index);
                                System.out.println("____________________________________________________________\n" +
                                        "Noted. I've removed this task:\n  " + curTask.toString() +
                                        "\nNow you have "+ taskList.size() +" tasks in the list.\n"+
                                        "____________________________________________________________");
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! The description of a delete cannot be empty.");
                            } catch (IndexOutOfBoundsException ex){
                                throw new DukeException("\u00a9 OOPS!!! There is nothing to delete at "+lineSplit[1]);
                            }
                        } else {
                            if (command.equals(Command.todo)) {
                                try {
                                    String[] item = lineSplit[1].split("/");
                                    Task newTask = new ToDos(item[0]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n" +
                                            "Got it. I've added this task:\n  " + newTask.toString() +
                                            "\nNow you have " + taskList.size() + " tasks in the list.\n" +
                                            "____________________________________________________________");
                                }catch (ArrayIndexOutOfBoundsException ex){
                                    throw new DukeException("\u00a9 OOPS!!! The description of a todo cannot be empty.");
                                }
                            } else if (command.equals(Command.deadline)) {
                                try {
                                String[] item = lineSplit[1].split("/by ");
                                Task newTask = new Deadline(item[0], item[1]);
                                taskList.add(newTask);
                                System.out.println("____________________________________________________________\n" +
                                        "Got it. I've added this task:\n  " + newTask.toString() +
                                        "\nNow you have " + taskList.size() + " tasks in the list.\n" +
                                        "____________________________________________________________");
                                }catch (ArrayIndexOutOfBoundsException ex){
                                    throw new DukeException("\u00a9 OOPS!!! The description of a deadline cannot be empty.");
                                }
                            } else if (command.equals(Command.event)) {
                                try {
                                String[] item = lineSplit[1].split("/at ");
                                Task newTask = new Events(item[0], item[1]);
                                taskList.add(newTask);
                                System.out.println("____________________________________________________________\n" +
                                        "Got it. I've added this task:\n  " + newTask.toString() +
                                        "\nNow you have " + taskList.size() + " tasks in the list.\n" +
                                        "____________________________________________________________");
                                }catch (ArrayIndexOutOfBoundsException ex){
                                    throw new DukeException("\u00a9 OOPS!!! The description of a event cannot be empty.");
                                }
                            } else{
                                throw new DukeException("\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-(");
                            }
                        }
                    }
                } catch(DukeException ex){
                System.out.println( "____________________________________________________________\n"+
                        ex.getMessage() +
                        "\n____________________________________________________________" );
                }
            }
            System.out.println("____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!"
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
class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by){
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

    public ToDos(String description)  {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class Events extends Task {

    protected String duration;

    public Events(String description,String duration){
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration + ")";
    }
}
class DukeException extends Exception{
    public DukeException(String message){
        super(message);
    }

}