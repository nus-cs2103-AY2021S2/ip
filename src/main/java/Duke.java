import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> storageList = new ArrayList<>();
        int listCounter;
        String name = "Link";
        String userInput;
        System.out.println("Hello! I'm " + name);
        System.out.println("How are you feeling today?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            userInput = sc.nextLine();
            while (!userInput.equals("bye")) {
                listCounter = 1;
                String copy = userInput;
                String[] parts = copy.split(" ");
                String keyword = parts[0];
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    while (storageList.size() != listCounter) {
                        Task task = storageList.get(listCounter - 1);
                        System.out.println(listCounter + "." + storageList.get(listCounter - 1).toString());
                        listCounter++;
                    }
                    System.out.println(listCounter + "." +storageList.get(listCounter - 1).toString());
                    userInput = sc.nextLine();
                } else if (keyword.equals("done")) {
                    int index = Integer.parseInt(parts[1]);
                    System.out.println(storageList.get(index-1).markAsDone());
                    if (sc.hasNextLine()) {
                        userInput = sc.nextLine();
                    }
                }
                else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                    System.out.println("Got it. I've added this task:");
                    StringBuilder str = new StringBuilder();
                    if (keyword.equals("todo")) {
                        for (int i = 1; i < parts.length; i++) {
                            str.append(" ");
                            str.append(parts[i]);
                        }
                        String taskString = str.toString();
                        Todo todo = new Todo(taskString);
                        storageList.add(todo);
                        System.out.println(todo.toString());
                    } else {
                        int slashIndex = 0;
                        for (int i = 0; i < parts.length; i++) {
                            if (parts[i].contains(Character.toString('/'))) {
                                slashIndex = i;
                            }
                        }
                        for (int j = 1; j < slashIndex; j++) {
                            str.append(" ");
                            str.append(parts[j]);
                        }
                        String taskString = str.toString();
                        StringBuilder byStringBuilder = new StringBuilder();
                        for (int k = slashIndex + 1; k < parts.length; k++) {
                            byStringBuilder.append(" ");
                            byStringBuilder.append(parts[k]);
                        }
                        if (keyword.equals("deadline")) {
                            Deadline deadline = new Deadline(taskString, byStringBuilder.toString());
                            storageList.add(deadline);
                            System.out.println(deadline.toString());
                        } else {
                            Event event = new Event(taskString, byStringBuilder.toString());
                            storageList.add(event);
                            System.out.println(event.toString());
                        }
                    }
                    System.out.println("Now you have " + storageList.size() + " tasks in the list.");
                    if (sc.hasNextLine()) {
                        userInput = sc.nextLine();
                    }
                }
                else {
                    Task task = new Task(userInput);
                    storageList.add(task);
                    System.out.println("added: " + userInput);
                    userInput = sc.nextLine();
                }
            }
        }

        System.out.println("Bye. Talk to me anytime!");
    }
}
