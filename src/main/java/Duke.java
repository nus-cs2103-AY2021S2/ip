import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        //task 1
        //greeting line
        /*
        System.out.println("Hello my name is Duke\n" + "What can i do for you");
        Scanner sc = new Scanner(System.in); // takes in input
        while(sc.hasNext()){ //while there is an input
            String s = sc.next();// put the input into string
            if(s.equals("bye")){ //if the srting is end word
                System.out.println("Bye, hope to see you again soon"); //display terminating message
                break; //break the loop
            } else { //if the input is not the terminating word
                System.out.println(s); ///print out the input
            }
        }
        */

        //task 2
        //add list
        System.out.println("Hello! I am Duke\n" + "What can I do for you"); //print out greeting line
        Scanner sc = new Scanner(System.in); //take in input
        ArrayList xs = new ArrayList(); ///create arraylist to store words
        while(sc.hasNextLine()){ //use next line so we store phrases instead of word
            String s = sc.nextLine(); //store phrase as string
            if(s.equals("bye")){ //if terminating word
                System.out.println("Bye, hope to see you again soon!"); //display terminating msf
                break; // terminate loop
            } else if(!s.equals("list")){ //as long as we dont ask for the list
                xs.add(s); //add the phrases into the list
                System.out.println("added: " + s); //and display confirmation message
            } else { //if we ask for the list
                xs.stream().forEach(x->System.out.println(x)); // print out the list
            }
        }
    }
}

