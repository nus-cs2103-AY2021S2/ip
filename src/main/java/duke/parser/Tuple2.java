package duke.parser;

public class Tuple2 <Integer, String> {
    private Integer integer;
    private String str;

    public Tuple2() {
    }

    public Tuple2 (Integer integer, String str) {
        this.integer = integer;
        this.str = str;
    }

    public Integer getInteger() {
        return this.integer;
    }

    public String getString() {
        return this.str;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
