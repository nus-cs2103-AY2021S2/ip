import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String line = "------------------------------------------------------";

    public static String listTask(List<Task> ls){
        String res = "\t" + line + "\n\tHere are the tasks in your list:\n";
        for(int i = 0; i < ls.size(); i++){
            res += "\t" + (i+1) + "." + ls.get(i) + "\n";
        }
        return res + "\t" + line;
    }

    public static void finishTask(List<Task> ls, int index){
        Task task = ls.get(index - 1);
        task.done = true;
        Task newTask = task;
        if(task instanceof ToDo){
            newTask = new ToDo(task.taskName, true);
        }
        else if(task instanceof Deadlines){
            newTask = new Deadlines(task.taskName, true, ((Deadlines) task).by);
        }
        else{
            newTask = new Events(task.taskName, true, ((Deadlines) task).by);
        }
        String res = "\t" + line + "\n\t" + "Nice! I've marked this task as done: \n\t\t" + newTask + "\n\t" + line ;
        System.out.println(res);

    }

    public static void addTask(List<Task> ls, Task task){
        String res = "\t" + line + "\n\tGot it. I've added this task:\n\t\t" + task.toString() + "\n";
        ls.add(task);
        int numOfTasks = ls.size();
        res += "\tNow you have " + numOfTasks + " tasks in the list\n\t" + line;
        System.out.println(res);
    }

    public static void greeting(){
        String logo =
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠛⠋⠉⡉⣉⡛⣛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⡿⠋⠁⠄⠄⠄⠄⠄⢀⣸⣿⣿⡿⠿⡯⢙⠿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⡀⡀⠄⢀⣀⣉⣉⣉⠁⠐⣶⣶⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⡇⠄⠄⠄⠄⠁⣿⣿⣀⠈⠿⢟⡛⠛⣿⠛⠛⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⡆⠄⠄⠄⠄⠄⠈⠁⠰⣄⣴⡬⢵⣴⣿⣤⣽⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⡇⠄⢀⢄⡀⠄⠄⠄⠄⡉⠻⣿⡿⠁⠘⠛⡿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⡿⠃⠄⠄⠈⠻⠄⠄⠄⠄⢘⣧⣀⠾⠿⠶⠦⢳⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣶⣤⡀⢀⡀⠄⠄⠄⠄⠄⠄⠻⢣⣶⡒⠶⢤⢾⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⡿⠟⠋⠄⢘⣿⣦⡀⠄⠄⠄⠄⠄⠉⠛⠻⠻⠺⣼⣿⠟⠋⠛⠿⣿⣿\n" +
                        "⠋⠉⠁⠄⠄⠄⠄⠄⠄⢻⣿⣿⣶⣄⡀⠄⠄⠄⠄⢀⣤⣾⣿⣿⡀⠄⠄⠄⠄⢹\n" +
                        "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢻⣿⣿⣿⣷⡤⠄⠰⡆⠄⠄⠈⠉⠛⠿⢦⣀⡀⡀⠄\n" +
                        "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⠟⡋⠄⠄⠄⢣⠄⠄⠄⠄⠄⠄⠄⠈⠹⣿⣀\n" +
                        "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠘⣷⣿⣿⣷⠄⠄⢺⣇⠄⠄⠄⠄⠄⠄⠄⠄⠸⣿\n" +
                        "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠹⣿⣿⡇⠄⠄⠸⣿⡄⠄⠈⠁⠄⠄⠄⠄⠄⣿\n" +
                        "⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢻⣿⡇⠄⠄⠄⢹⣧⠄⠄⠄⠄⠄⠄⠄⠄⠘";
        System.out.println(line);
        System.out.println(logo);
        System.out.println("I am Donald Trump, the Greatest American President ever\nWhat can I do for you?");
        System.out.println(line);

    }
    public static void main(String[] args) {
        List<Task> ls = new ArrayList<>();
        greeting();
        EchoMachine Duke = new EchoMachine();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                System.out.println("\t" + line + "\n\tBye. Need to go now since I am impeached twice\n\t" + line);
                break;
            }
            else if(input.equals("list")){
                System.out.println(listTask(ls));
            }
            else if(input.split(" ")[0].equals("done")){
                int index = Integer.parseInt(input.split(" ")[1]);
                finishTask(ls, index);
            }

            else if(input.split(" ")[0].equals("done")){
                int index = Integer.parseInt(input.split(" ")[1]);
                finishTask(ls, index);
            }
            else if(input.split(" ")[0].equals("todo")){
                String res = input.split("todo")[1];
                addTask(ls, new ToDo(res));
            }
            else if(input.split(" ")[0].equals("deadline")){
                String[] res = (input.split("deadline")[1]).split(" /by ");
                String description = res[0];
                String by = res[1];
                addTask(ls, new Deadlines(description, by));
            }

            else if(input.split(" ")[0].equals("event")){
                String[] res = (input.split("event")[1]).split(" /at ");
                String description = res[0];
                String by = res[1];
                addTask(ls, new Events(description, by));
            }

            else{
                System.out.println("\t" + line);
                System.out.println(Duke.echo("\tadded: " + input));
                ls.add(new Task(input));
                System.out.println("\t" + line + "\n");
            }
        }

    }
}
