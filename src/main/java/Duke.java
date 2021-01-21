
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList();
        System.out.println("____________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________");

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String s = input.nextLine();
            System.out.println("____________________________________");
            if(s.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________");

                break;
            } else if(s.equals("list")){
                for(int i = 0; i< myList.size(); i++){
                    System.out.println(i+1 + "."+ myList.get(i) + "\n");
                }

            } else {
                myList.add(s);
                System.out.println("added: " + s);

            }
            System.out.println("____________________________________");


        }
    }
}