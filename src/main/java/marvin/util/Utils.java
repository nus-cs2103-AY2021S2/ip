package marvin.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @@author damithc -reused
 * https://github.com/se-edu/addressbook-level2
 *
 * Utility methods
 */
public class Utils {
    /**
     * Checks if every element in a collection are unique by {@link Object#equals(Object)}.
     */
    public static boolean elementsAreUnique(Collection<?> items) {
        final Set<Object> testSet = new HashSet<>();
        for (Object item : items) {
            final boolean itemAlreadyExists = !testSet.add(item); // see Set documentation
            if (itemAlreadyExists) {
                return false;
            }
        }
        return true;
    }
}
