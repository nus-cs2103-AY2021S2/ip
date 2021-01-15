import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("------------------------");
        System.out.println("Hello! I'm Duke's friend, Ekud." +
                "\nDuke's dead, so I'm here to take his job." +
                "\nYou want to jot down some tasks?");

        ArrayList<Task> listOfInputs = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")){
            if (input.equals("list")){
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listOfInputs.size(); i++) {
                    System.out.println((i + 1)+ ". " + listOfInputs.get(i));
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                try{
                    listOfInputs.get(index - 1).setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listOfInputs.get(index - 1));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You have " + listOfInputs.size() + " tasks in your list. Try again.");
                }
            } else {
                listOfInputs.add(new Task(input));
                System.out.println("Added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye Bye. Please give me 5-star rating, I still need this job." +
                "\nMuch thanks.");
    }
}
