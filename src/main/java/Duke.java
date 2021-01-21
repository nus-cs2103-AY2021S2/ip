import java.util.*;

public class Duke {
    public List<Task> list = new ArrayList<>();
    public static String line = "____________________________________________________________";
    public Duke(){
    }

    public void doTask (int taskNum){
        Task curr = list.get(taskNum - 1);
        curr.done = true;
        System.out.format(Duke.line + "\n Nice! I've marked this task as done: " +
                "\n [%s] [%s] %s" +
                "\n" + Duke.line,curr.type(),curr.status(),curr.toString());
    }

    public void printTasks(){
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
        if (currLine[0].equals("list")) this.printTasks();
        else if(currLine[0].equals("done")) this.doTask(Integer.parseInt(currLine[1]));
        else{
            String output = "";
            output += line + "\n" + " Got it. I've added this task: \n";
            Task t;
            if (currLine[0].equals("todo")) t = new Todo(currLine);
            else if (currLine[0].equals("deadline")) t = new Deadline(currLine);
            else if(currLine[0].equals("event"))t = new Event(currLine);
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
