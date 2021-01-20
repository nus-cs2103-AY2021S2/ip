import java.util.*;

public class Duke {
    public List<Task> list = new ArrayList<>();
    public static String line = "____________________________________________________________";
    public Duke(){
    }

    public String doTask (int taskNum){
        Task curr = list.get(taskNum - 1);
        curr.done = true;
        return Duke.line + "\n Nice! I've marked this task as done: \n  [" +curr.status()+"] return "+curr.taskName+"\n" + Duke.line;
    }

    public void printTasks(){
        System.out.println(line);
        int i = 1;
        for(Task s: this.list){
            System.out.format("%d. [%s] %s \n", i, s.status() ,s.taskName);
            i++;
        }
        System.out.println(line);
    }

    public void addTask(Task t){
        list.add(t);
    }

    public static void main(String[] args) {
        System.out.println(Duke.line + "\n"
                +"Hello! I'm Duke\n"
                +"What can I do for you?\n"
                + Duke.line);
        Duke D = new Duke();
        Scanner sc = new Scanner(System.in);
        String scannedLine = sc.nextLine();
        String[] currStringLine = scannedLine.split(" ");
        String currString = currStringLine[0];

        while(!currString.equals("bye")){
            if(currString.equals("list")){
                D.printTasks();
            }
            else if(currString.equals("done")) {
                System.out.format(D.doTask(Integer.parseInt(currStringLine[1])));
            }
            else {
                D.addTask(new Task(scannedLine));
                System.out.println(line + "\n" + "added: " + scannedLine + "\n" + line);
            }
            scannedLine = sc.nextLine();
            currStringLine = scannedLine.split(" ");
            currString = currStringLine[0];
        }
        System.out.println(line + "\n" + " Bye. Hope to see you again soon!"+"\n" + line);


    }
}
