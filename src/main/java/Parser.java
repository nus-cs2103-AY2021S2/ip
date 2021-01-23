import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private final TaskList taskList;
    Parser(){
        this.taskList = new TaskList();
    }

    public void parseFile(String taskString) throws IOException{
        String type = taskString.substring(1,2);
        String isDone = taskString.substring(4,5);
        if(type.equals("T")){
            String description = taskString.substring(7);
            ToDo task = new ToDo(description);
            if(isDone.equals("X")){
                task.markAsDone();
            }
            this.taskList.addTask(task);
        }
        else if(type.equals("D")){
            String description = taskString.split(" \\(by: ")[0].substring(7);
            String by = taskString.split(" \\(by: ")[1].replaceAll("\\)","");
            Deadlines task = new Deadlines(description, by);
            if(isDone.equals("X")){
                task.markAsDone();
            }
            this.taskList.addTask(task);
        }
        else{
            String description = taskString.split(" \\(at: ")[0].substring(7);
            String by = taskString.split(" \\(at: ")[1].replaceAll("\\)","");
            Events task = new Events(description, by);
            if(isDone.equals("X")){
                task.markAsDone();
            }
            this.taskList.addTask(task);
        }
    }

    public void parseUserCommand(String commandFromUser) throws IOException, DukeException {
        if(commandFromUser.equals("list")){
            this.taskList.listTask();
        }
        else if(commandFromUser.split(" ")[0].equals("done")){
            int index = Integer.parseInt(commandFromUser.split(" ")[1]);
            this.taskList.finishTask(index);
        }
        else if(commandFromUser.split(" ")[0].equals("delete")){
            int index = Integer.parseInt(commandFromUser.split(" ")[1]);
            this.taskList.deleteTask(index);
        }
        else if(commandFromUser.split(" ")[0].equals("todo")){
            if(commandFromUser.split(" ").length == 1){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String res = commandFromUser.split("todo")[1];
            this.taskList.addTask(new ToDo(res));
        }
        else if(commandFromUser.split(" ")[0].equals("deadline")){
            String[] res = (commandFromUser.split("deadline")[1]).split(" /by ");
            if(res.length != 2){
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = res[0];
            String by = res[1];
            this.taskList.addTask(new Deadlines(description, by));
        }

        else if(commandFromUser.split(" ")[0].equals("event")){
            String[] res = (commandFromUser.split("event")[1]).split(" /at ");
            String description = res[0];
            String by = res[1];
            this.taskList.addTask(new Events(description, by));
        }

        else{
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void runParsing(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            try{
                String input = scanner.nextLine();
                if(input.equals("bye")){
                    System.out.println("\t" + Ui.line + "\n\tBye. Need to go now since I am impeached twice\n\t" + Ui.line);
                    break;
                }
                else{
                    this.parseUserCommand(input);
                }

            } catch (DukeException  | IOException e) {
                System.out.println(e.getMessage());
                break;
            }


        }
    }
}
