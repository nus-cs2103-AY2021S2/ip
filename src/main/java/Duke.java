import  java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(sc.hasNext()){
            String out = sc.next();
            if(out.equals("bye")){
                System.out.println("Bye. Hope to see you again");
                break;
            }else {
                System.out.println(out);
            }
        }
    }
}
