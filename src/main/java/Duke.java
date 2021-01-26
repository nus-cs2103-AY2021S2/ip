import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        List<Task> list = new ArrayList<>();
        int numitems = 0;
        File saveFile = new File("./myData.txt");

        if (!saveFile.exists()) {
            boolean isCreated = saveFile.createNewFile();
            if (isCreated) {
                System.out.println("New save file created!");
            }
        } else {
            Scanner fileScanner = new Scanner(saveFile);
            while (fileScanner.hasNext()) {
                String taskStr = fileScanner.nextLine();
                char taskType = taskStr.charAt(1);
                int len = taskStr.length();
                if (taskType == 'T') {
                    list.add(numitems, new Todo(taskStr.substring(7)));
                    numitems++;
                } else if (taskType == 'D'){
                    int ind = taskStr.indexOf(" (by: ");
                    list.add(numitems, new Deadline(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1)));
                    numitems++;
                } else if (taskType == 'E'){
                    int ind = taskStr.indexOf(" (at: ");
                    list.add(numitems, new Event(taskStr.substring(7, ind + 1), taskStr.substring(ind + 6, len - 1)));
                    numitems++;
                }
                if (taskStr.charAt(4) == 'X') {
                    list.get(numitems - 1).markAsDone();
                }
            }
        }

        FileWriter fw = new FileWriter("./myData.txt");
        //System.out.println(saveFile.getAbsolutePath());
        //Task[] list = new Task[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("You currently have " + numitems + " tasks.");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            Pattern pdone = Pattern.compile("^(done )([0-9]+)");
            Matcher mdone = pdone.matcher(str);

            Pattern ptodo = Pattern.compile("^([Tt]odo )([a-zA-Z_0-9 ]*+)");
            Matcher mtodo = ptodo.matcher(str);

            Pattern pdl = Pattern.compile("^([Dd]eadline )([a-zA-Z_0-9 ]*+)(\\/by )([a-zA-Z_0-9 ]*+)");
            Matcher mdl = pdl.matcher(str);

            Pattern pev = Pattern.compile("^([Ee]vent )([a-zA-Z_0-9 ]*+)(\\/at )([a-zA-Z_0-9 ]*+)");
            Matcher mev = pev.matcher(str);

            Pattern pdel = Pattern.compile("^(delete )([0-9]+)");
            Matcher mdel = pdel.matcher(str);

            if (str.equals("list")) {
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < numitems; i++) {
                    System.out.println(i+1 + "." + list.get(i));

                }

            } else if (mdone.find()) {
                System.out.println("Good job, I've marked the task as done!");
                int n = Integer.parseInt(mdone.group(2)) - 1;
                list.get(n).markAsDone();
                System.out.println(list.get(n));
            } else if (mtodo.find()) {
                System.out.println(mtodo.group(2));
                if (mtodo.group(2).equals("")) {
                    throw new DukeException("The description of a Todo cannot be empty!");
                }
                list.add(numitems, new Todo(mtodo.group(2)));
                //fw.write(list.get(numitems).toString() + "\n");
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mdl.find()) {
                if (mdl.group(2).equals("")) {
                    throw new DukeException("The description of a Deadline cannot be empty!");
                }
                list.add(numitems, new Deadline(mdl.group(2), mdl.group(4)));
                //fw.write(list.get(numitems).toString() + "\n");
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mev.find()) {
                if (mev.group(2).equals("")) {
                    throw new DukeException("The description of an Event cannot be empty!");
                }
                list.add(numitems, new Event(mev.group(2), mev.group(4)));
                //fw.write(list.get(numitems).toString() + "\n");
                System.out.println("Got it!. I have added the following task:");
                System.out.println(list.get(numitems));
                numitems += 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else if (mdel.find()) {
                System.out.println("Okay I have removed this task!");
                int n = Integer.parseInt(mdel.group(2)) - 1;
                System.out.println(list.get(n));
                list.remove(n);
                numitems -= 1;
                System.out.println("Now you have " + numitems + " tasks in the list.");
            } else {
                throw new DukeException("I don't know what that means!!!!");
            }
            str = sc.nextLine();
        }
        for (int i = 0; i < numitems; i++) {
            fw.write(list.get(i).toString() + "\n");
        }
        System.out.println("Bye friend, see you soon!");
        fw.close();
    }
}
