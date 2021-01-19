public class Mike {
    /**
     * Duke chatbot constructor
     */
    private final String logo = "  __  __   _   _               _     _                _    _                      _\n" +
            " |  \\/  | (_) | |             | |   | |              | |  | |                    | |   \n" +
            " | \\  / |  _  | | __   ___    | |_  | |__     ___    | |__| |   __ _  __      __ | | __\n" +
            " | |\\/| | | | | |/ /  / _ \\   | __| | '_ \\   / _ \\   |  __  |  / _` | \\ \\ /\\ / / | |/ /\n" +
            " | |  | | | | |   <  |  __/   | |_  | | | | |  __/   | |  | | | (_| |  \\ V  V /  |   < \n" +
            " |_|  |_| |_| |_|\\_\\  \\___|    \\__| |_| |_|  \\___|   |_|  |_|  \\__,_|   \\_/\\_/   |_|\\_\\\n" +
            "                                                                                       \n" +
            "                                           __     __\n" +
            "                                          /'(  _  )`\\\n" +
            "                                         / . \\/^\\/ . \\\n" +
            "                                        /  _)_`-'_(_  \\\n" +
            "                                       /.-~   ).(   ~-.\\\n" +
            "                                      /'     /\\_/\\     `\\\n" +
            "                                             \"-V-\"";

    public boolean isRunning;
    public Mike() {
        this.isRunning = true;
    }

    /**
     * Prints initialisation message
     */
    public void mikeInit() {
        System.out.println("Hello from\n" + this.logo);
    }

    public String getResponse(Command inputCommand) {
        this.isRunning = !inputCommand.isExitCommand();
        return inputCommand.runCommand();
    }

//    public void mikeRun() {
//        boolean isRunning = true;
//        while(isRunning) {
//            Command inputCommand = inputHandler.parseInput();
//            inputCommand.runCommand();
//            isRunning = !inputCommand.getExitVal();
//        }
//    }
}


