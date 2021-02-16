package snom.common.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.StringTokenizer;

import org.junit.jupiter.api.Test;

public class SnomioUtilTest {
    @Test
    public void hasNoTokens() {
        StringTokenizer tokenizer = new StringTokenizer("Sample");

        // has token
        assertFalse(SnomioUtil.hasNoTokens(tokenizer));

        // remove next token
        tokenizer.nextToken();

        // no token
        assertTrue(SnomioUtil.hasNoTokens(tokenizer));
    }

    @Test
    public void isValidInteger() {
        assertTrue(SnomioUtil.isValidInteger("123"));
    }

    @Test
    public void isValidDouble() {
        assertTrue(SnomioUtil.isValidDouble("23.321"));
    }
}
