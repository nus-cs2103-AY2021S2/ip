package util;

public interface Storable<T> {
    String toSaveString();

    T fromSaveString(String saveString);
}
