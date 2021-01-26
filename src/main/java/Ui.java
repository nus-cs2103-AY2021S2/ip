import java.util.Scanner;

public class Ui {
    Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }
    public String read() {
        return sc.nextLine();
    }

    public boolean canRead() {
        return sc.hasNextLine();
    }

    public void println(String str) {
        System.out.println(str);
    }
}