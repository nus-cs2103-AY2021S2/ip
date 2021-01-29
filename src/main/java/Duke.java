import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Duke {
    public static void main(String[] args) {
        int counter = 0;
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner (System.in);
        System.out.println("Hello! What can I do for you:>");
        String input;
        do {
            input = sc.nextLine();
            if (input.equals("bye")){
                break;
            }
            if (input.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("You have no tasks in the list!");
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else {
                String[] inputSplit = input.split(" ");
                switch (inputSplit[0]){
                    case "done":
                        if (inputSplit.length < 2){
                            System.out.println("You have entered too little arguments, please include the task number");
                            break;
                        }
                        int value = Integer.valueOf(inputSplit[1]);
                        list.get(value - 1).setDone();
                        System.out.println("Nice, I have set this task as done!");
                        System.out.println(list.get(value - 1).toString());
                        break;
                    case "delete":
                        if (inputSplit.length < 2){
                            System.out.println("You have entered too little arguments, please include the task number");
                            break;
                        }
                        counter--;
                        int valueToDelete = Integer.valueOf(inputSplit[1]);
                        Task task = list.get(valueToDelete - 1);
                        list.remove(valueToDelete - 1);
                        System.out.println("Noted. I have deleted this task:");
                        System.out.println(task.toString());
                        System.out.println("You now have " + counter + " task(s) in the list!");
                        break;
                    case "todo":
                        if (inputSplit.length < 2){
                            System.out.println("You have entered too little arguments, please include the task description");
                            break;
                        }
                        counter++;
                        String name = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                        list.add(new ToDos(name));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(list.get(counter - 1));
                        System.out.println("You now have " + counter + " task(s) in the list!");
                        break;
                    case "deadline":
                        if (inputSplit.length < 2){
                            System.out.println("You have entered too little arguments, please include the task description");
                            break;
                        }
                        counter++;
                        String deadlineName = String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                        String[] deadlineSplit = deadlineName.split("/by ");
                        list.add(new Deadlines(false, deadlineSplit[0], deadlineSplit[1]));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(list.get(counter - 1));
                        System.out.println("You now have " + counter + " task(s) in the list!");
                        break;
                    case "event":
                        if (inputSplit.length < 2){
                            System.out.println("You have entered too little arguments, please include the task description");
                            break;
                        }
                        counter++;
                        String event = String.join(" ",Arrays.copyOfRange(inputSplit, 1, inputSplit.length));
                        String[] eventSplit = event.split("/at ");
                        list.add(new Events(eventSplit[0], eventSplit[1]));
                        System.out.println("Got it. I have added this task:");
                        System.out.println(list.get(counter - 1));
                        System.out.println("You now have " + counter + " task(s) in the list!");
                        break;
                    default:
                        System.out.println("You have entered invalid commands, please try again!");
                }
            }
        } while (!input.equals("bye"));

        System.out.println("Bye. See you again!");
    }
}