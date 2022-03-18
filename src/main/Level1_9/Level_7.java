package Level1_9;
import ip.src.main.java.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Level_7 {

    private static void createBot(String filePath, Duke bot) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Task newTask = new Task("");
        while (s.hasNext()) {
            String[] taskData = s.nextLine().split(" \\| ");
            String type = taskData[0];
            String doneStatus = taskData[1];
            if (type.equals("T")) {
                String details = taskData[2];
                newTask = new ToDo(details);
                bot.addToBot(newTask);

            } else if (type.equals("E")) {
                String content = taskData[2];
                String at = taskData[3];
                newTask = new Event(content, at);
                bot.addToBot(newTask);

            } else {
                String content = taskData[2];
                String by = taskData[3];
                newTask = new Deadline(content, by);
                bot.addToBot(newTask);
            }

            if (doneStatus == " 1 ") {
                newTask.markDone();
            }

        }
    }


    private static void updateFile(String filePath, Duke bot) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(Task element:bot.list.list) {
            fw.write(element.toString() + "\n");

        }
        fw.close();
    }
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Duke bot = new Duke();
        try{
            File f = new File("data/duke.txt");
            f.getParentFile().mkdirs();
            if(f.createNewFile()){
                System.out.println("File created");
            }else {
                createBot("data/duke.txt", bot);
            }
        }catch (IOException e){
            System.out.println("Error in creating file");


        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                bot.printList();
            } else {
                String command = input.split(" ")[0];
                try {
                    if (command.equals("done")) {
                        int id = Integer.valueOf(input.split(" ")[1]);
                        bot.markTaskAsDone(id);

                    } else if (command.equals("todo")) {
                        try {
                            input = input.split(" ", 2)[1];
                            ToDo newTask = new ToDo(input);
                            bot.addToList(newTask);
                            //counter++;
                        } catch (Exception e) {
                            throw new DukeException(("OOPS!!! The description cannot be empty."));
                        }
                    } else if (command.equals("event")) {
                        input = input.split(" ", 2)[1];
                        String content = input.split("/at")[0];
                        String at = input.split("/at")[1];
                        Event newTask = new Event(content, at);
                        bot.addToList(newTask);
                    } else if (command.equals("deadline")) {
                        input = input.split(" ", 2)[1];
                        String content = input.split("/by")[0];
                        String by = input.split("/by")[1];
                        Deadline newTask = new Deadline(content, by);
                        bot.addToList(newTask);
                    }else if(command.equals("delete")){
                        int id = Integer.valueOf(input.split(" ")[1]);
                        bot.deleteTask(id);

                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-{");
                    }
                } catch (DukeException e1) {
                    System.out.println(e1);

                }
            }
            try {
                updateFile("data/duke.txt", bot);
            }catch (IOException e) {
                System.out.println("Unable to update file");
            }
            input = sc.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}







