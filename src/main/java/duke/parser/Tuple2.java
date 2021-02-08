package duke.parser;

/**
 * Object used to let methods return 2 separate types.
 * @param <Integer> Returned Integer.
 * @param <String> Returned String.
 */
public class Tuple2 <Integer, String> {
    private Integer integer;
    private String str;

    /**
     * Default constructor.
     */
    public Tuple2() {
    }

    /**
     * Overloaded constructor.
     * @param integer Returned Integer.
     * @param str Returned String.
     */
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
