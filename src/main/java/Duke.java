import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

        try {
            File myFile = new File("duke.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
                Scanner myReader = new Scanner(myFile);
                while (myReader.hasNextLine()) {
                    String type = myReader.nextLine();
                    if (type.contains("[T]")) {
                        String description = type.substring(type.indexOf("  ") + 2);
                        storageList.add(new Todo(description));
                    } else if (type.contains("[D]")) {
                        String description = type.substring(type.indexOf("  ") + 2, type.indexOf(" (") + 1);
                        String date = type.substring(type.indexOf("by: ") + 3, type.indexOf(")"));
                        storageList.add(new Deadline(description, date));
                    } else if (type.contains("[E]")) {
                        String description = type.substring(type.indexOf("  ") + 2, type.indexOf(" (") + 1);
                        String time = type.substring(type.indexOf("at: ") + 3, type.indexOf(")"));
                        storageList.add(new Event(description, time));
                    }
                }
                myReader.close();
            }
        } catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        try {
            while (sc.hasNextLine()) {
                userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    break;
                }
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
                        PrintWriter myWriter = new PrintWriter("duke.txt");
                        for (Task t : storageList) {
                            myWriter.println(t.toString());
                        }
                        myWriter.flush();
                        myWriter.close();
                        if (sc.hasNextLine()) {
                            userInput = sc.nextLine();
                            if (userInput.equals("bye")) {
                                break;
                            }
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
                        PrintWriter myWriter = new PrintWriter("duke.txt");
                        for (Task t : storageList) {
                            myWriter.println(t.toString());
                        }
                        myWriter.flush();
                        myWriter.close();
                        if (sc.hasNextLine()) {
                            userInput = sc.nextLine();
                            if (userInput.equals("bye")) {
                                break;
                            }
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

                        PrintWriter myWriter = new PrintWriter("duke.txt");
                        for (Task t : storageList) {
                            myWriter.println(t.toString());
                        }
                        myWriter.flush();
                        myWriter.close();
                        if (sc.hasNextLine()) {
                            userInput = sc.nextLine();
                            if (userInput.equals("bye")) {
                                break;
                            }
                        }
                    }
                    else {
                        throw new IllegalKeywordException("Invalid keyword");
                    }
                }
                if (userInput.equals("bye")) {
                    break;
                }
            }
            System.out.println("Bye. Talk to me anytime!");
        } catch (InsufficientArgumentsException err) {
            System.out.println("Number of arguments is invalid. Try again");
        } catch(IllegalKeywordException err) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (NumberFormatException n) {
            System.out.println("Done command must be followed by a number");
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }

    }
}
