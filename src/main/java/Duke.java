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
                "\nWhat can I do for you?");

        ArrayList<String> listOfInputs = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                for (int i = 0; i < listOfInputs.size(); i++) {
                    System.out.println((i + 1)+ ". " + listOfInputs.get(i));
                }
            } else {
                listOfInputs.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye Bye. Please give me 5-star rating, I still need this job." +
                "\nMuch thanks.");
    }
}
