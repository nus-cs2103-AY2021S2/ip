package com.tanboonji.jhin.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The AliasMap class contains the hash map of alias.
 */
public class AliasMap implements Serializable {

    private static final String aliasFormat = "%s --> %s";
    private final HashMap<String, String> aliasMap;

    /**
     * Class constructor specifying a hash map of alias to be initialised to.
     *
     * @param aliasMap Hash map of alias.
     */
    public AliasMap(HashMap<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    public int getSize() {
        return aliasMap.size();
    }

    /**
     * Adds a new command alias.
     *
     * @param command Command to be aliased.
     * @param alias Alias to be aliased to command.
     */
    public void addAlias(String command, String alias) {
        aliasMap.put(alias, command);
    }

    /**
     * Deletes an alias by command alias.
     *
     * @param alias Alias to be deleted.
     */
    public String deleteAlias(String alias) {
        String command = aliasMap.get(alias);
        aliasMap.remove(alias);
        return String.format(aliasFormat, alias, command);
    }

    /**
     * Checks if user alias input exist in alias hash map.
     *
     * @param alias User alias input.
     */
    public boolean containsAlias(String alias) {
        return aliasMap.containsKey(alias);
    }

    public String getAlias(String alias) {
        return aliasMap.get(alias);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String alias: aliasMap.keySet()) {
            builder.append(String.format(aliasFormat, alias, aliasMap.get(alias)))
                    .append("\n");
        }

        return builder.toString();
    }
}
