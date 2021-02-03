package blarb;

import java.util.Optional;

/**
 * {@code Output} stores warning and normal outputs for Blarb.
 */
class Output {
    final String normal;
    final Optional<String> warn;

    Output(String normal, String warn) {
        this.normal = normal;
        this.warn = Optional.ofNullable(warn);
    }
}
