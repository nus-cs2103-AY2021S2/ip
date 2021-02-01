import java.util.*;
public class TaskList {

    public ArrayList<Task> TaskArrList;
    public int stateChangeNum;


    public TaskList(){
        this.TaskArrList = new ArrayList<Task>();
        this.stateChangeNum = 0;
    }

    /*
    public void addTask(List tList){
        System.out.println("Printing from TaskList class, addTask function:");
        for(int i = 0; i < tList.size(); i++){
            System.out.println(tList.get(i));
        }
    }
    public void addTask(Task task){

    }

     */

    public int getStateChangeNum(){
        return stateChangeNum;
    }

    public void addTask(String str) throws DukeException.InvalidCommand{
        if(str.contains("todo")^str.contains("deadline")^str.contains("event")){
            if(str.equals("todo")||str.equals("deadline")||str.equals("event")){
                throw new DukeException.InvalidCommand("Sorry description of " + str + " cannot be empty");
            }
            else{
                if(str.contains("todo")){
                    this.TaskArrList.add(new Todo(str.substring(5)));
                }
                else{
                    if(str.indexOf('/') == -1){
                        throw new DukeException.InvalidCommand("Sorry description of task is in incorrect format");
                    }
                    else{
                        if(str.contains("deadline")){
                            this.TaskArrList.add(new Deadlines(str.substring(9, str.indexOf('/') - 1),
                                    str.substring(str.indexOf('/') + 4, str.length())));
                        }
                        else if(str.contains("event")){
                            this.TaskArrList.add(new Events(str.substring(6, str.indexOf('/') - 1),
                                    str.substring(str.indexOf('/') + 4, str.length())));

                        }
                    }
                }
                this.stateChangeNum++;
                System.out.println("Got it. I've added this task:");
                System.out.println(this.TaskArrList.get(this.TaskArrList.size() - 1));
                System.out.println("Now you have " + this.TaskArrList.size() + " tasks in the list.");
            }
        }
        else{
            throw new DukeException.InvalidCommand("Command entered is invalid");
        }

    }

    public void doneTask(String str) throws DukeException.InvalidCommand {
            int tNum = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            if(!this.TaskArrList.isEmpty() && tNum <= this.TaskArrList.size() && tNum > 0){
                this.stateChangeNum++;
                System.out.println("Nice! I've marked this task as done:");
                this.TaskArrList.get(tNum - 1).markAsDone();
                System.out.println(this.TaskArrList.get(tNum - 1));
            }
            else if (this.TaskArrList.isEmpty()){
                throw new DukeException.InvalidCommand("No tasks were added");
            }
            else{
                throw new DukeException.InvalidCommand("Done command is invalid");
            }


    }

    public void deleteTask(String str) throws DukeException.InvalidCommand{
        if(this.TaskArrList.isEmpty()){
            throw new DukeException.InvalidCommand("No tasks were added");
        }
        else{
            if(str.equals("delete")){
                throw new DukeException.InvalidCommand("Please specify which task number from list to delete");
            }
            else {
                int tDel = Integer.parseInt(str.replaceAll("[^0-9]", ""));
                if(tDel <= 0 || tDel > this.TaskArrList.size()){
                    throw new DukeException.InvalidCommand("Invalid task number selected, please try again");
                }
                else {
                    this.stateChangeNum++;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(this.TaskArrList.get(tDel - 1));
                    this.TaskArrList.remove(tDel - 1);
                    System.out.println("Now you have " + this.TaskArrList.size() + " tasks in the list.");
                }
            }
        }




    }

    public String listTasks() throws DukeException.InvalidCommand{
        if(this.TaskArrList.isEmpty()){
            throw new DukeException.InvalidCommand("No tasks were added");
        }
        else{
            String s = "";
            for(int i = 0; i < this.TaskArrList.size(); i++){
                int num = i + 1;
                s = s + Integer.toString(num) + "." + this.TaskArrList.get(i) + "\n";
            }
            return s;
        }

    }

}
