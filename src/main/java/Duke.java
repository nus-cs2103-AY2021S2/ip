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
        try {
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
                            System.out.println(listCounter + "." + storageList.get(listCounter - 1).toString());
                            listCounter++;
                        }
                        System.out.println(listCounter + "." + storageList.get(listCounter - 1).toString());
                        userInput = sc.nextLine();
                    } else if (keyword.equals("done")) {
                        if (parts.length > 2) {
                            throw new InsufficientArgumentsException("Wrong arguments");
                        }
                        int index = Integer.parseInt(parts[1]);
                        System.out.println(storageList.get(index - 1).markAsDone());
                        if (sc.hasNextLine()) {
                            userInput = sc.nextLine();
                        }
                    } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                        if (parts.length == 1) {
                            throw new InsufficientArgumentsException("Insufficient arguments provided");
                        }
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
                    } else if (keyword.equals("delete")) {
                        if (parts.length > 2) {
                            throw new InsufficientArgumentsException("Wrong arguments");
                        }
                        int index = Integer.parseInt(parts[1]);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(storageList.get(index - 1).toString());
                        storageList.remove(index-1);
                        System.out.println("Now you have " + storageList.size() + " tasks in the list.");
                        if (sc.hasNextLine()) {
                            userInput = sc.nextLine();
                        }
                    }
                    else {
                        throw new IllegalKeywordException("Invalid keyword");
                    }
                }
            }
            System.out.println("Bye. Talk to me anytime!");
        } catch (InsufficientArgumentsException err) {
            System.out.println("Number of arguments is invalid. Try again");
        } catch(IllegalKeywordException err) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (NumberFormatException n) {
            System.out.println("Done command must be followed by a number");
        }

    }
}
