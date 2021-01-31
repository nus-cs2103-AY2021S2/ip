package duke.command;

import duke.dukeException.DukeException;
import duke.task.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Command {
    public String command;

    public Command(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks) throws IOException {
        ArrayList<Task> tList = tasks.tasks;
        if(command.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            int i = 1;
            for(Task s : tList) {
                if(s != null) {
                    System.out.println("     " + i + "." + s.toString());
                    i++;
                } else {
                    break;
                }
            }
        } else if(command.split(" ")[0].equals("done")) {
            int n = Integer.parseInt(command.split(" ")[1]);
            tList.get(n-1).markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tList.get(n-1).toString());
            Task t = tList.get(n-1);
            tasks.tasks = tList;
            String old = null;
            if(t instanceof ToDo) {
                old = "T | 0 | " + t.name;
            } else if(t instanceof Deadline) {
                old = "D | 0 | " + t.name;
            } else if(t instanceof Event) {
                old = "E | 0 | " + t.name;
            }
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String oldContent = "";
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = null;
            if(t instanceof ToDo) {
                newContent = oldContent.replace(old, "T | 1 | " + t.name);
            } else if(t instanceof Deadline) {
                newContent = oldContent.replace(old, "D | 1 | " + t.name);
            } else if(t instanceof Event) {
                newContent = oldContent.replace(old, "E | 1 | " + t.name);
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(newContent);
            reader.close();
            writer.close();
        } else if(command.split(" ")[0].equals("delete")) {
            int n = Integer.parseInt(command.split(" ")[1]);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + tList.get(n-1).toString());
            System.out.println("     Now you have " + (tList.size()-1) + " tasks in the list.");
            tList.remove(n-1);
            tasks.tasks = tList;
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String oldContent = "";
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                if(counter != n-1) {
                    oldContent = oldContent + line + System.lineSeparator();
                }
                counter++;
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            writer.write(oldContent);
            reader.close();
            writer.close();
        }else if(command.split(" ")[0].equals("todo")) {
            int iend = command.indexOf(" ");
            String n = command.substring(iend+1);
            try{
                tList.add(new ToDo(n));
                tList.get(tList.size()-1).addTask(tList.size());
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("data/duke.txt", true));
                writer.write("T | 0 | " + n + "\n");
                writer.close();
                tasks.tasks = tList;
            } catch(DukeException e){
                System.out.println(e.getMessage());
            }
        } else if(command.split(" ")[0].equals("deadline")) {
            try{
                int iend1 = command.indexOf(" ");
                int iend = command.indexOf("/");
                String subString1= command.substring(iend1+1 , iend-1);
                String subString2= command.substring(iend+4);
                int year = Integer.valueOf(subString2.substring(0, 4));
                int mon = Integer.valueOf(subString2.substring(5, 7));
                int day = Integer.valueOf(subString2.substring(8));
                tList.add(new Deadline(subString1, LocalDate.of(year, mon, day)));
                tList.get(tList.size()-1).addTask(tList.size());
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("data/duke.txt", true));
                writer.write("D | 0 | " + subString1 + " | " + subString2 + "\n");
                writer.close();
                tasks.tasks = tList;
            }
            catch(DukeException e){
                System.out.println(e.getMessage());
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.println("     ☹ OOPS!!! The due date of a deadline cannot be empty. (Format: /by + date[YYYY-MM-DD])");
            }
        } else if(command.split(" ")[0].equals("event")) {
            try{
                int iend1 = command.indexOf(" ");
                int iend = command.indexOf("/");
                String subString1= command.substring(iend1+1 , iend-1);
                String subString2= command.substring(iend+4);
                int year = Integer.valueOf(subString2.substring(0, 4));
                int mon = Integer.valueOf(subString2.substring(5, 7));
                int day = Integer.valueOf(subString2.substring(8));
                tList.add(new Event(subString1, LocalDate.of(year, mon, day)));
                tList.get(tList.size()-1).addTask(tList.size());
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("data/duke.txt", true));
                writer.write("E | 0 | " + subString1 + " | " + subString2 + "\n");
                writer.close();
                tasks.tasks = tList;
            } catch(DukeException e){
                System.out.println(e.getMessage());
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.println("     ☹ OOPS!!! The start and end date of an event cannot be empty.(Format: /at + duration[YYYY-MM-DD])");

            }
        } else if(command.split(" ")[0].equals("find")) {
            String match = command.split(" ")[1];
            BufferedReader reader = new BufferedReader(new FileReader("data/duke.txt"));
            String line = reader.readLine();
            int counter = 0;
            int secondCounter = 1;
            System.out.println("     Here are the matching tasks in your list:");
            while (line != null) {
                if(line.indexOf(match) != -1) {
                    System.out.println("     " + secondCounter + "." + tasks.tasks.get(counter).toString());
                    secondCounter++;
                }
                line = reader.readLine();
                counter++;
            }
        } else if(command.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!");
        } else {
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isExit() {
        return (command.equals("bye"));
    }
}
