import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<String> taskList= new ArrayList<String>(100);
        Scanner sc= new Scanner(System.in);
        String logo = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(logo);


        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")){
                break;
            } else {
                if(command.equals("list")){
                    System.out.println("____________________________________________________________");
                    for(int i=1;i<=taskList.size();i++){
                        System.out.println(i+". "+taskList.get(i-1));
                    }
                    System.out.println("____________________________________________________________");
                }
                else{
                    taskList.add(command);
                    System.out.println("____________________________________________________________\n"+
                            "added: "+ command+"\n____________________________________________________________");
                }
            }
        }
        System.out.println("____________________________________________________________\n"
                +"Bye. Hope to see you again soon!"
                + "\n____________________________________________________________");


    }
}
