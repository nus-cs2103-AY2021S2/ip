package myDuke;

import java.util.List;
import java.util.Scanner;

/**
 * A UI that deals with interactions with the user
 */
public class Ui {

    public static String DASH = "_______________________________________________"
            + "_____________";

    void print(String s) {
        System.out.println(DASH);
        System.out.println(s);
        System.out.println(DASH);
    }

    public String getInput(Scanner sc) {
        System.out.print("You: ");
        String input = sc.nextLine();
        System.out.println(input);
        return input;
    }

    void print(String[] sArr) {
        System.out.println(DASH);
        for (String s : sArr) {
            if (s != null) {
                System.out.println(s);
            }
        }
        System.out.println(DASH);
    }

    void print(String description, String[] sArr) {
        System.out.println(DASH);
        System.out.println(description);
        for (String s : sArr) {
            if (s != null) {
                System.out.println(s);
            }
        }
        System.out.println(DASH);
    }

    public void showGreetingMsg() {
        print("Pai Kia Bot: Eh harlo! Call me Pai Kia Bot.");
    }

    public String showGreetingMsgStr() {
        return "Eh harlo! Call me Pai Kia Bot.";
    }

    public void showFileLoadSuccessMsg() {
        print("Pai Kia Bot: Got ur input file, baik la");
    }

    public String showFileLoadSuccessMsgStr() {
        return "Got ur input file, baik la";
    }

    public void showLoadingError() {
        print(new String[] {
                "Pai Kia Bot: I cannot find ur input file sia, could be becuz",
                "1) u never create file because u r new user, in that case, just continue using this program and i "
                        + "will create the folder and save file for u in the directory [root]/data",
                "2) ur saveFile.txt is not in the correct directory, pls input bye and shift the file to [root]/data",
                "3) ur input file is not named saveFile.txt, pls input 'bye' and rename ur input file"
        });
    }

    public String showLoadingErrorStr() {
        return ("I cannot find ur input file sia, could be becuz " +
                "1) u never create file because u r new user, in that case, just continue using this program and i "
                + "will create the folder and save file for u in the directory [root]/data "
                + "2) ur saveFile.txt is not in the correct directory, pls input bye and shift the file to"
                + "[root]/data 3) ur input file is not named saveFile.txt, pls input 'bye' and rename "
                + "ur input file");
    }

    public void askForUserInput() {
        print("Pai Kia Bot: how u want me to help u?");
    }

    public String askForUserInputStr() {
        return ("how u want me to help u?");
    }

    public void printErrorMsg(String errorMsg) {
        print(errorMsg);
    }
    public String printErrorMsgStr(String errorMsg) {
        return (errorMsg);
    }

    void printErrorMsg(String[] errorMsg) {
        print(errorMsg);
    }
    String printErrorMsgStr(String[] errorMsg) {
        String str = "";
        for (String s : errorMsg) {
            str += s + " ";
        }
        return str;
    }

    void showListEmptyMsg() {
        print("Paikia Bot: eh list is empty leh.");
    }

    String showListEmptyMsgStr() {
        return ("eh list is empty leh.");
    }

    void showFindEmptyMsg() {
        print("Paikia Bot: eh your find keyword return no result leh.");
    }

    String showFindEmptyMsgStr() {
        return ("eh your find keyword return no result leh.");
    }

    void printDoneTaskAlert(String completedTask) {
        print("Paikia Bot: ok i just help u checked this task as done -- "
                + completedTask);
    }
    String printDoneTaskAlertStr(String completedTask) {
        return("ok i just help u checked this task as done -- "
                + completedTask);
    }

    void printDeletedTaskAlert(String deletedTask, int numOfRemainingTasksInList) {
        print(new String[] {
                "Pai Kia Bot: ok i just help u deleted this task -- " + deletedTask + ". ",
                "now u got " + numOfRemainingTasksInList + " item(s) in your list ah"
        });
    }

    String printDeletedTaskAlertStr(String deletedTask, int numOfRemainingTasksInList) {
        return ("ok i just help u deleted this task -- " + deletedTask +
                ". now u got " + numOfRemainingTasksInList + " item(s) in your list ah");
    }

    void printAddedTodoAlert(String newTodo, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this todo -- " + newTodo,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    String printAddedTodoAlertStr(String newTodo, int numOfTasksInList) {
        return("ok i just help u added this todo -- " + newTodo
                + ". now u got " + numOfTasksInList + " item(s) in your list ah"
        );
    }

    void printAddedEventAlert(String newEvent, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this event -- " + newEvent,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    String printAddedEventAlertStr(String newEvent, int numOfTasksInList) {
        return("ok i just help u added this event -- " + newEvent
                + "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        );
    }

    void printAddedDeadlineAlert(String newDeadline, int numOfTasksInList) {
        print(new String[] {
                "Paikia Bot: ok i just help u added this event -- " + newDeadline,
                "Paikia Bot: now u got " + numOfTasksInList + " item(s) in your list ah"
        });
    }

    String printAddedDeadlineAlertStr(String newDeadline, int numOfTasksInList) {
        return ("ok i just help u added this event -- " + newDeadline
                + ". now u got " + numOfTasksInList + " item(s) in your list ah"
        );
    }

    void showInputError() {
        print("Paikia Bot: wrong input format leh, can try again onot?");
    }

    String showInputErrorStr() {
        return ("wrong input format leh, can try again onot?");
    }

    public void showByeMsg() {
        print("Pai Kia Bot: Leave so soon ah? Ok can limpeh help u save your list "
                + "in [root]/data as saveFile.txt, i go sleep first");
    }

    public String showByeMsgStr() {
        return ("Leave so soon ah? Ok can limpeh help u save your list "
                + "in [root]/data as saveFile.txt, i go sleep first");
    }

    void printTasksInList(String[] tasksArr) {
        print(tasksArr);
    }

    String printTasksInListStr(String[] tasksArr) {

        String str = "";
        for (String s : tasksArr) {
            str += s + ", ";
        }
        return str;
    }

    String printTasksInListStr(List<String> tasksArr) {

        String str = "";
        for (String s : tasksArr) {
            str += s + "\n";
        }
        return str;
    }

    void printSearchResultList(String keyword, String[] tasksArr) {
        print("Paikia Bot: dis is your search result for '" + keyword + "'", tasksArr);
    }

    String printSearchResultListStr(String keyword, List<String> tasksArr) {
        String str = "";
        for (String s : tasksArr) {
            str += s + "\n";
        }
        return ("Paikia Bot: dis is your search result for '" + keyword + "': \n" + str);
    }

    void showSortedMsg() {
        print("Pai Kia Bot: Ok can limpeh help u sort your list according to alphabetical order liao");
    }
    String showSortedMsgStr() {
        return ("Ok can limpeh help u sort your list according to alphabetical order liao");
    }
}