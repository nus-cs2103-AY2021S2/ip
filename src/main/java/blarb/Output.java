package blarb;

/**
 * {@code Output} stores warning and normal outputs for Blarb.
 */
class Output {
    final String normal;
    final String warn;

    Output(String[] input) {
        this.normal = input[0];
        this.warn = input[1];
    }
}
