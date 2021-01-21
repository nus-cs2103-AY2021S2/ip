import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> arr = new ArrayList<>();
        boolean[] done  = new boolean[100];
        //initialise an array of undone boolean expressions.
        for (int i = 0; i < 100; i ++ ){
            done[1] = false;
        }


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //while(sc.hasNext()){
        while(true){
            String out = sc.next();
            if(out.equals("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }
            if (out.equals("list")){
                for(int i = 1; i <= arr.size();  i++){
                    if (done[i - 1]) {
                        System.out.println(i + ". [X]" + arr.get(i - 1));
                    }else{
                        System.out.println(i + ". [ ]" + arr.get(i-1));

                    }
                }
                continue;
            }
            if (out.equals("done")){
                int index = sc.nextInt();
                done[index - 1] = true;

                System.out.println("Nice! I've marked this Task as done:");
                System.out.println("[X]" + arr.get(index - 1));
                continue;
            }
            if(true){
                String print = out + sc.nextLine();
                arr.add(print);
                System.out.println("added: " + print);
            }
        }
    }
}
