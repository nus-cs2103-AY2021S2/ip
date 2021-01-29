package kobe;

public class Commands {
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;


    public static void goodbye(Storage storage) {
        storage.saveFile();
        System.out.println(line + "Bye. Kobe saved your list.\n" + ind
                + "Kobe hopes to see you again soon!\n" + line);
    }

    public static void showList(TaskList tasks, Ui ui) {
        System.out.print(line + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(ind + (i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        ui.showLine();
    }


}