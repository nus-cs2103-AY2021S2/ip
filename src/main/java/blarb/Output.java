package blarb;

/**
 * {@code Output} stores warning and normal outputs for Blarb.
 */
class Output {
    final String normal;
    final String warn;

    Output(String normal, String warn) {
        this.normal = normal;
        this.warn = warn;
    }
}
