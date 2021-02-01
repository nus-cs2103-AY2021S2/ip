package ip.src.main.java;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class A_MoreOOP {

    private static void createBot(String filePath, Duke bot) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Storage storage = new Storage(filePath, bot);
        Task newTask = new Task("");
        while (s.hasNext()) {
            storage.loadTasks(s.nextLine());
        }

    }


    private static void updateFile(String filePath, Duke bot) throws IOException {
        Storage storage = new Storage(filePath, bot);
        storage.updateFile();
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
        Parser parser = new Parser();
        Ui ui = new Ui(bot);

        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                bot.printList();
            } else {
                String command = parser.getCommand(input);
                try {
                    if (command.equals("done")) {
                        int id = parser.getId(input);
                        ui.doneCommand(id);

                    } else if (command.equals("todo")) {
                        try {
                            input = parser.toDoTask(input);
                            ui.toDoCommand(input);

                            //counter++;
                        } catch (Exception e) {
                            throw new DukeException(("OOPS!!! The description cannot be empty."));
                        }
                    } else if (command.equals("event")) {
                        String content = parser.eventTaskContent(input);
                        String at = parser.eventTaskAt(input);
                        ui.eventCommand(content,at);


                    } else if (command.equals("deadline")) {
                        String content = parser.deadlineTaskContent(input);
                        String by = parser.deadlineTaskBy(input);
                        ui.deadlineCommand(content,by);

                    }else if(command.equals("delete")){
                        int id = parser.getId(input);
                        ui.deleteCommand(id);

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







