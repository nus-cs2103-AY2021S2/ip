package checklst.parser;

import checklst.exception.ChecklstException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws ChecklstException;
}
