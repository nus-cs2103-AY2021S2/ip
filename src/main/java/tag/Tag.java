package tag;

public class Tag {
    public String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String toString() {
        return "#" + this.tagName;
    }
}
