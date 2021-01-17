import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println(
        "____________________________________________________________ \n" +
        "Hello! I'm Duke \nWhat can I do for you? \n" +
        "____________________________________________________________");

        inputCommand in = new inputCommand();
        while(!in.getInput().equals("bye")){
            in = new inputCommand(sc.nextLine());
            if(in.getInput().equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            }else {
                System.out.println(in.getInput());
            }
        }

    }
}
