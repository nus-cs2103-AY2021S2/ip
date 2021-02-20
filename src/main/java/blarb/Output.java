package blarb;

import java.util.Optional;

/**
 * {@code Output} stores warning and normal outputs for Blarb.
 */
class Output {
    final String normal;
    final Optional<String> warn;
    final boolean isNotice;

    Output(boolean isNotice, String... strings) {
        assert strings.length < 3;
        this.normal = strings[0];
        if (strings.length > 1) {
            this.warn = Optional.ofNullable(strings[1]);
        } else {
            this.warn = Optional.empty();
        }
        this.isNotice = isNotice;
    }
}
