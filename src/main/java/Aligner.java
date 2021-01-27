public class Aligner {
    public Aligner() { }

    public static String align(String s) {
        int stringLength = s.length();
        int LINELENGTH = 73;
        int left = (int) Math.ceil((LINELENGTH - stringLength) / 2);
        int right = (int) Math.floor((LINELENGTH - stringLength) / 2);
        String newString;
        newString = String.format("%" + left + "s", " ") + s
                + String.format("%" + right + "s", " ");
        return newString;
    }
}
