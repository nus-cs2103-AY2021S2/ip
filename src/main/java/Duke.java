import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    public static String line = "------------------------------------------------------";

    public static void handleInput(TaskList manager) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            try{
                String input = scanner.nextLine();
                if(input.equals("bye")){
                    System.out.println("\t" + line + "\n\tBye. Need to go now since I am impeached twice\n\t" + line);
                    break;
                }
                else if(input.equals("list")){
                    manager.listTask();
                }
                else if(input.split(" ")[0].equals("done")){
                    int index = Integer.parseInt(input.split(" ")[1]);
                    manager.finishTask(index);
                }
                else if(input.split(" ")[0].equals("delete")){
                    int index = Integer.parseInt(input.split(" ")[1]);
                    manager.deleteTask(index);
                }

                else if(input.split(" ")[0].equals("todo")){
                    if(input.split(" ").length == 1){
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String res = input.split("todo")[1];
                    manager.addTask(new ToDo(res));


                }
                else if(input.split(" ")[0].equals("deadline")){
                    String[] res = (input.split("deadline")[1]).split(" /by ");
                    if(res.length != 2){
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String description = res[0];
                    String by = res[1];
                    manager.addTask(new Deadlines(description, by));
                }

                else if(input.split(" ")[0].equals("event")){
                    String[] res = (input.split("event")[1]).split(" /at ");
                    String description = res[0];
                    String by = res[1];
                    manager.addTask(new Events(description, by));
                }

                else{
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException  | IOException e) {
                System.out.println(e.getMessage());
                break;
            }


        }

    }

    public static void main(String[] args) throws IOException {
        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        else if (!Files.exists(Paths.get("data/Duke.txt"))) {
            Files.createFile(Paths.get("data/Duke.txt"));
        }

        TaskList tasklist = new TaskList();
        tasklist.greeting();
        handleInput(tasklist);


    }
}
