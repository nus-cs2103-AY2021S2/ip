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
        String[] list = new String[100];
        int count = 0;

        //commands entered by the user
        while(!str.equals("bye"))
            if(str.equals("list")){
                //display them back to the user when requested
                for(int i=1; i<=count; i++){
                    System.out.println(i + ". " + list[i-1]);
                }
                if(count==0){
                    System.out.println("There is no task in the list.");
                }
                System.out.println();
                str = sc.nextLine();
            } else {
                //store whatever text entered by the user
                list[count] = str;
                count++;
                System.out.println("added: " + str +"\n");
                str = sc.nextLine();
            }

        //exits when the user types bye
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
