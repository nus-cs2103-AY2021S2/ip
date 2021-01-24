import java.io.IOException;

public class Parser {
    private final TaskList taskList;
    private static final String SAVED_FORMAT_DELIMITER = " \\| ";


    Parser(TaskList taskList){
        this.taskList = taskList;
    }

    public static Task parseFileInput(String taskString){
        String[] split = taskString.split(SAVED_FORMAT_DELIMITER);
        String type = split[0];
        String isDone = split[1];
        switch (type){
            case "T":
                String descriptionT = split[2];
                ToDo taskT = new ToDo(descriptionT);
                if(isDone.equals("X")){
                    taskT.markAsDone();
                }
                return taskT;
            case "D":
                String descriptionD = split[2];
                String byD= split[3];
                Deadlines taskD = new Deadlines(descriptionD, byD, true);
                if(isDone.equals("X")){
                    taskD.markAsDone();
                }
                return taskD;
            case "E":
                String descriptionE = split[2];
                String byE= split[3];
                Deadlines taskE = new Deadlines(descriptionE, byE, true);
                if(isDone.equals("X")){
                    taskE.markAsDone();
                }
                return taskE;
            default:
                return null;

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
            String res = commandFromUser.split("todo ")[1];
            this.taskList.addTask(new ToDo(res));
        }
        else if(commandFromUser.split(" ")[0].equals("deadline")){
            String[] res = (commandFromUser.split("deadline ")[1]).split(" /by ");
            if(res.length != 2){
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String description = res[0];
            String by = res[1];
            Task task = new Deadlines(description, by, false);
            this.taskList.addTask(task);
        }

        else if(commandFromUser.split(" ")[0].equals("event")){
            String[] res = (commandFromUser.split("event ")[1]).split(" /at ");
            String description = res[0];
            String by = res[1];
            this.taskList.addTask(new Events(description, by));
        }

        else{
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
