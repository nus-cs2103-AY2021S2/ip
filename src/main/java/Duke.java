import  java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String line= "__________________________________________________";

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //while(sc.hasNext()){
        System.out.println("Welcome to my todo list");
        int ran_output = -1;
        while (ran_output!= 0){
            try {
                ran_output = run();
            }catch (DukeException e){
                System.out.println(e);
            }


        }


    }
    public static int run() throws DukeException, NotFoundException, TimeException, DescriptionException{
        Scanner sc = new Scanner(System.in);
        String[] input;
        ArrayList<Task> arr = new ArrayList<>();
        int number = 0;


        while(true){
            System.out.println(line);
            String next = sc.nextLine().trim();
            String task_in = "";
            String time = "";
            input = next.split(" ");
            if(next.equals("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }else{
                switch (input[0]) {
                    case "list":
                        for (int i = 1; i <= arr.size(); i++) {
                            System.out.println(i + arr.get(i - 1).toString());
                        }
                        break;
                    case "done":
                        Task task = arr.get(Integer.parseInt((input[1])) - 1);
                        task.setDone(true);
                        System.out.println("Nice! I've marked this Task as done:");
                        System.out.println(input[1] + arr.get(Integer.parseInt(input[1]) - 1).toString());
                        continue;
                    case "todo":
                        if(input.length == 1){
                            throw new DescriptionException(input[0]);
                        }
                        for (int i = 1; i < input.length; i++) {
                            task_in = task_in + input[1];
                        }
                        arr.add(new Todo(next));
                        number++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(next);
                        System.out.println("you now have " + number + " tasks in the list");
                        break;
                    case "deadline":
                        if(input.length == 1){
                            throw new DescriptionException(input[0]);
                        }
                        int index = 0;
                        while (!input[index].equals("/by")){
                            if(index == input.length -1){
                                throw new DescriptionException(input[0]);
                            }

                            if(task_in == ""){
                                task_in = input[index];
                                index++;
                            }else {
                                task_in = task_in + " " + input[index];
                                index++;
                            }
                        }
                        if(index + 1 == input.length){
                            throw new TimeException(input[0]);
                        }
                        for (int i = index + 1; i < input.length; i++){
                            time = time + " " + input [i];
                        }
                        arr.add(new Deadline(task_in, time.trim()));
                        number++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_in + " by" + time);
                        System.out.println("you now have " + number + " tasks in the list");
                        break;
                    case "event":
                        if(input.length == 1){
                            throw new DescriptionException(input[0]);
                        }
                        int index_2 = 0;
                        while (!input[index_2].equals("/at")){
                            if(index_2 == input.length -1){
                                throw new DescriptionException(input[0]);
                            }

                            if(task_in == ""){
                                task_in = input[index_2];
                                index_2++;
                            }else {
                                task_in = task_in + " " + input[index_2];
                                index_2++;
                            }
                        }
                        if(index_2 + 1 == input.length){
                            throw new TimeException(input[0]);
                        }
                        for (int i = index_2 + 1; i < input.length; i++){
                            time = time + " " + input [i];
                        }
                        arr.add(new Event(task_in, time.trim()));
                        number++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task_in + " at"+ time);
                        System.out.println("you now have " + number + " tasks in the list");
                        break;
                    case "delete":
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(arr.get(Integer.parseInt(input[1]) -1));
                        arr.remove(Integer.parseInt(input[1]) -1 );
                        number--;
                        System.out.println("you now have " + number + " tasks in the list");
                        break;
                    default:
                        throw new NotFoundException();
                }

            }


        }
        sc.close();
        return 0;
    }
}
