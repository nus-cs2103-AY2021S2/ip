import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //test push, it doesnt work!
        //starts by greeting the user
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Task[] list = new Task[100];
        int count = 0;

        //commands entered by the user
        while(!str.equals("bye"))
            if(str.equals("list")) {
                //display them back to the user when requested
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + ". " + list[i - 1]);
                }
                if (count == 0) {
                    System.out.println("There is no task in the list.");
                }
                System.out.println();
                str = sc.nextLine();
            }else{
                String type = str.substring(0, str.indexOf(' '));
                String detail = str.substring(str.indexOf(' ') + 1);
                if(type.equals("done")) {
                    //mark tasks as done
                    int number = Integer.parseInt(detail);
                    list[number-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + list[number-1] +"\n");
                    str = sc.nextLine();
                }else {
                    //store task entered by the user

                    if(type.equals("todo")){
                        list[count] = new Todo(detail);
                    }else if(type.equals("event")){
                        String name=detail.substring(0,detail.indexOf(" /at"));
                        String time=detail.substring(detail.indexOf(" /at") + 5);
                        list[count] = new Event(name, time);
                    }else if(type.equals("deadline")){
                        String name = detail.substring(0,detail.indexOf(" /by"));
                        String time = detail.substring(detail.indexOf(" /by") + 5);
                        list[count] = new Deadline(name, time);
                    }
                    count++;
                    if(count == 1){
                        System.out.println("Got it. I've added this task:\n  " + list[count - 1] + "\nNow you have " + count + " task in the list.\n");
                    }else {
                        System.out.println("Got it. I've added this task:\n  " + list[count - 1] + "\nNow you have " + count + " tasks in the list.\n");
                    }
                    str = sc.nextLine();
                }
            }

        //exits when the user types bye
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
