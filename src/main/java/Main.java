public class Main {
    public static void main(String[] args) {
        Mike mike = new Mike();
        String mikeResponse;

        mike.mikeInit();
        while(mike.isRunning) {
            Command inputCommand = InputHandler.parseInput();
            mikeResponse = mike.getResponse(inputCommand);
            System.out.println(mikeResponse);
        }
    }
}
