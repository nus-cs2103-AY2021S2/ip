import java.util.*;

public class Duke {
    public List<String> list = new ArrayList<>();

    public Duke(){
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
                +"Hello! I'm Duke\n"
                +"What can I do for you?\n"
                + " ____________________________________________________________");
        Duke D = new Duke();
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String currString = sc.nextLine();
        while(!currString.equals("bye")){
            if(currString.equals("list")){
                System.out.println(line);
                int i = 1;
                for(String s: D.list){
                    System.out.format("%d. %s \n", i, s);
                    i++;
                }
                System.out.println(line);
            }
            else {
                D.list.add(currString);
                System.out.println(line + "\n" + "added: " + currString + "\n" + line);
            }
            currString = sc.nextLine();
        }
        System.out.println(line + "\n" + " Bye. Hope to see you again soon!"+"\n" + line);


    }
}
