import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     Konichiwa~~ Watashi wa \n" + logo + "     desu!\n     What can I do for you today?");
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            String command = sc.nextLine();

            List<Task> tasks = new ArrayList<>();

            while(!command.equals("bye")){
                if(command.equals("list")){
                    System.out.println("     Koko wa list of tasks desu~");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("       " + i + ". " + tasks.get(i - 1).toString());
                    }
                    System.out.println("     Ganbatte ne!");

                } else if(command.startsWith("done")){
                    try {
                        int taskNum = Integer.valueOf(command.substring(5));
                    } catch (Exception e){
                        throw new DukeException(":( Task number not detected! Please recheck format of command.");
                    }
                    int taskNum = Integer.valueOf(command.substring(5));
                    if(taskNum <= 0 || taskNum > tasks.size()){
                        throw new DukeException(":( Invalid task number! Please try again.");
                    }
                    tasks.get(taskNum - 1).setCompleted();
                    System.out.println("     Otsukare! I've marked this task as done:");
                    System.out.println("       " + tasks.get(taskNum - 1).toString());

                } else if(command.startsWith("delete")){
                    try {
                        int taskNum = Integer.valueOf(command.substring(7));
                    } catch (Exception e){
                        throw new DukeException(":( Task number not detected! Please recheck format of command.");
                    }
                    int taskNum = Integer.valueOf(command.substring(7));
                    if(taskNum <= 0 || taskNum > tasks.size()){
                        throw new DukeException(":( Invalid task number! Please try again.");
                    }
                    System.out.println("     Otsukare! I've removed the task:");
                    System.out.println("       " + tasks.get(taskNum - 1).toString());
                    tasks.remove(taskNum - 1);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.list");


                } else if(command.startsWith("todo")){
                    if(command.length()< 5){
                        throw new DukeException(":( Description of ToDos cannot be empty! Please try again.");
                    }
                    Todo curr = new Todo(command.substring(5));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");

                } else if(command.startsWith("deadline")){
                    int cut = command.indexOf("/");
                    if(cut == -1 || command.length()<= cut +4 ){
                        throw new DukeException(":( Deadline timing not specified! Please try again.");
                    }
                    try {
                        command.substring(9, cut-1);
                    } catch (Exception e){
                        throw new DukeException(":( Task name not detected! Please try again.");
                    }
                    Deadline curr = new Deadline(command.substring(9, cut-1), command.substring(cut+4));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");

                } else if(command.startsWith("event")){
                    int cut = command.indexOf("/");
                    if(cut == -1 || command.length()<= cut +4 ){
                        throw new DukeException(":( Event timing not specified! Please try again.");
                    }
                    try {
                        command.substring(6, cut-1);
                    } catch (Exception e){
                        throw new DukeException(":( Task name not detected! Please try again.");
                    }
                    Event curr = new Event(command.substring(6, cut-1), command.substring(cut+4));
                    tasks.add(curr);
                    System.out.println("     Hai, I've added this task:\n       " + curr.toString() +
                            "\n     Now you have " + tasks.size() + " tasks in the list.");

                } else {
                    throw new DukeException(":( Sumimasen I cannot understand the command : " + command);

                }

                command = sc.nextLine();
            }

            System.out.println("     Sayonara! Mata ne~ ;)");
        }
    }
}
