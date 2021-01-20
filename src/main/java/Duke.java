import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke myDuke = new Duke();
        myDuke.run();
    }

    private void run(){
        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");
        Scanner input = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();

        while(input.hasNextLine()){
            String s = input.nextLine();
            if(s.equals("bye")) {
                System.out.println("---------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------------------");
                break;
            } else if (s.equals("list")) {
                System.out.println("---------------------------------------------");
                int len = myList.size();
                for (int i=1; i<len+1;i++){
                    System.out.println(i+". "+myList.get(i-1));
                }
                System.out.println("---------------------------------------------");
            } else {
                myList.add(s);
                System.out.println("---------------------------------------------");
                System.out.println("added: "+ s);
                System.out.println("---------------------------------------------");
            }
        }
    }



}
