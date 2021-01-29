public class UserInterface {
    static String line = "\t________________________________________________________________\n";

    public static void greetUser() {
        String logo = "                                                     \n" +
                "\t88                                                   \n" +
                "\t88                                            ,d     \n" +
                "\t88                                            88     \n" +
                "\t88,dPPYba,   ,adPPYba, ,adPPYYba, ,adPPYba, MM88MMM  \n" +
                "\t88P'    \"8a a8P_____88 \"\"     `Y8 I8[    \"\"   88     \n" +
                "\t88       d8 8PP\"\"\"\"\"\"\" ,adPPPPP88  `\"Y8ba,    88     \n" +
                "\t88b,   ,a8\" \"8b,   ,aa 88,    ,88 aa    ]8I   88,    \n" +
                "\t8Y\"Ybbd8\"'   `\"Ybbd8\"' `\"8bbdP\"Y8 `\"YbbdP\"'   \"Y888  \n" +
                "\t                                                     \n" +
                "\t                                                     \n";
        String greeting = line + logo + "\tWelcome to ToDoBeast, your best productivity task tracker tool!\n"
                + "\tLet's get this bread! How would you like to be productive today?\n" + line;
        System.out.println(greeting);
    }

    public static void exitDisplay() {
        String exitMsg = line + "\tThis app may have stopped but the grind never stops.\n\tSee you again soon!\n" + line;
        System.out.println(exitMsg);
        System.exit(0);
    }
}
