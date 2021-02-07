package data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tag is for tagging tasks
 */
public class Tag {

    @JsonProperty
    private String name;

    private Tag() {
    }

    /**
     * Constructs a Tag with the given name
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the tag
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the tag with the given name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a boolean stating if the given object is equal to the current object
     *
     * @return equality
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Tag)) {
            return false;
        }

        Tag tag = (Tag) o;

        return name.equals(tag.name);
    }

    /**
     * Returns the hashcode of name
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
