import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> arr = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(sc.hasNext()){
            String out = sc.next();
            if(out.equals("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }else if (out.equals("list")){
                for(int i = 1; i <= arr.size();  i++){
                    System.out.println(i + ". " + arr.get(i-1));
                }
            }else {
                arr.add(out);
                System.out.println("added: " + out);
            }
        }
    }
}
