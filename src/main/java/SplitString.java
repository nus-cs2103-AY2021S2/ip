public class SplitString {
    private final String firstString, secondString;

    public SplitString(String string, String separator) {
        int separatorIndex = string.indexOf(separator);
        if (separatorIndex == -1) {
            this.firstString = string;
            this.secondString = "";
        } else {
            this.firstString = string.substring(0, separatorIndex);
            this.secondString = string.substring(separatorIndex + separator.length());
        }
    }

    public String getFirstString() {
        return firstString;
    }

    public String getSecondString() {
        return secondString;
    }

}
