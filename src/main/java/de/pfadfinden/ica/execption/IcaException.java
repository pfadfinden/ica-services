package de.pfadfinden.ica.execption;

public class IcaException extends Exception {

    /**
     * Erzeugt die Exception ohne Meldung.
     */
    public IcaException() {
        super();
    }

    /**
     * Erzeugt die Exception mit einem beliebigen String als Meldung.
     *
     * @param str
     *            Meldung.
     */
    public IcaException(String str) {
        super(str);
    }

    /**
     * Erzeugt die Exception mit einer weiteren Exception als Grund.
     *
     * @param cause
     *            .
     */
    public IcaException(Throwable cause) {
        super(cause);
    }
}
