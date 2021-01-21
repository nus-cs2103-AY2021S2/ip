import java.util.*;

public class Duke {
    public List<Task> list = new ArrayList<>();
    public static String line = "____________________________________________________________";
    public Duke(){
    }

    public void doTask (int taskNum){
        if(list.size()<1 ) throw new ArrayIndexOutOfBoundsException("You have no tasks to do!");
        if(taskNum < 1 || taskNum > list.size() ) throw new ArrayIndexOutOfBoundsException("That task does not exist");
        Task curr = list.get(taskNum - 1);
        curr.done = true;
        System.out.format(Duke.line + "\n Nice! I've marked this task as done: " +
                "\n [%s] [%s] %s" +
                "\n" + Duke.line,curr.type(),curr.status(),curr.toString());
    }

    public void delete(int num) throws ArrayIndexOutOfBoundsException{
        if(list.size()<1) throw new ArrayIndexOutOfBoundsException("You have no tasks to delete!");
        if(num < 1 || num > list.size() ) throw new ArrayIndexOutOfBoundsException("That task does not exist");
        Task curr = list.get(num-1);
        list.remove(num-1);
        String deleted = "["+ curr.type() +"]"+"[" + curr.status() +"] "+curr.toString();
        System.out.format("%s\nNoted. I've removed this task: \n %s\nNow you have %d tasks in the list\n%s", line, deleted ,list.size(),line);
    }

    public void printTasks() throws ArrayIndexOutOfBoundsException {
        if(list.size()<1) throw new ArrayIndexOutOfBoundsException("You have no tasks!");
        System.out.println(line);
        int i = 1;
        for(Task s: this.list){
            System.out.format("%d. [%s] [%s] %s \n", i, s.type(), s.status() ,s.toString());
            i++;
        }
        System.out.println(line);
    }

    public void addTask(Task t){
        list.add(t);
    }

    public void handleTask(String[] currLine) throws Exception {
        String command = currLine[0];
        if (command.equals("list")) this.printTasks();
        else if (command.equals("delete")) this.delete(Integer.parseInt(currLine[1]));
        else if(command.equals("done")) this.doTask(Integer.parseInt(currLine[1]));
        else{
            String output = "";
            output += line + "\n" + " Got it. I've added this task: \n";
            Task t;
            if (command.equals("todo")) t = new Todo(currLine);
            else if (command.equals("deadline")) t = new Deadline(currLine);
            else if(command.equals("event"))t = new Event(currLine);
            else{
                throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            list.add(t);
            output += "  "+t.printNew();
            output += "\n Now you have "+list.size() + " tasks in the list" + "\n" + line;
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        System.out.println(Duke.line + "\n"
                +"Hello! I'm Duke\n"
                +"What can I do for you?\n"
                + Duke.line);
        Duke D = new Duke();
        Scanner sc = new Scanner(System.in);
        String scannedLine = sc.nextLine();
        String[] lineList = scannedLine.split(" ");
        String currString = lineList[0];
        while (!currString.equals("bye")) {
            try{
                D.handleTask(lineList);
            }
            catch (Exception e){
                    System.out.format("%s\n☹ %s\n%s",Duke.line,e.getMessage(),Duke.line);
            }
            finally{
                scannedLine = sc.nextLine();
                lineList = scannedLine.split(" ");
                currString = lineList[0];
            }
        }
        System.out.println(line + "\n" + " Bye. Hope to see you again soon!"+"\n" + line);


    }
}
