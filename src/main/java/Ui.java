//import java.util.Scanner;
//
///**
// * Duke's UI. Deals with interactions with the user by reading commands,
// * passing it to the Parser and printing the resultant output string.
// */
//public class Ui {
//    private boolean isDone;
//
//    /**
//     * Runs the UI object with the specified TaskList to edit and Storage to store taskList when the application ends.
//     * @param taskList Specified TaskList to be read and written to.
//     * @param storage Specified Storage object that reads from and writes to
//     *               a data file to populate and save taskList.
//     */
//    public void runUi(TaskList taskList, Storage storage) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//
//        System.out.println("    ____________________________________________________________\n" +
//                "     Duke... booted...\n" +
//                "     requesting tasks\n" +
//                "    ____________________________________________________________\n");
//        Scanner sc = new Scanner(System.in);
//        Parser parser = new Parser();
//
//        while (!isDone) {
//            String input = sc.next();
//            String nextOutput = parser.parse(input);
//            System.out.println(nextOutput);
//        }
//        sc.close();
//    }
//
//    /**
//     * Toggles an inner boolean to end the loop that reads user commands.
//     */
//    public void end() {
//        isDone = true;
//    }
//}
