public class Printer {

    public static void printWithStyle(String[] output) {
        System.out.println("    ________________________________________________________________");
        for (String str : output) {
            System.out.println("    " + str);
        }
        System.out.println("    ________________________________________________________________");
    }

    public static void printWithStyle(String output) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + output );
        System.out.println("    ________________________________________________________________");
    }
}
