package de.pfadfinden.ica.execption;


import de.pfadfinden.ica.model.IcaApiResponse;
import de.pfadfinden.ica.model.IcaResponse;

public class IcaAuthenticationException extends IcaApiException{

    /**
     * Erzeugt die Exception mit der Antwort von NaMi.
     *
     * @param resp Antwort vom ICA-Server
     */
    public IcaAuthenticationException(IcaApiResponse<? extends IcaResponse> resp) {
        super(resp);
    }

    /**
     * Erzeugt die Exception mit einer beliebigen Fehlermeldung.
     *
     * @param message Fehlermeldung
     */
    public IcaAuthenticationException(String message) {
        super(message);
    }

    /**
     * Erzeugt die Exception, wenn vor der Angabe des Grundes eine weitere
     * Exception auftritt. Das heißt beim Ermitteln des Grundes für den
     * gescheiterten Login tritt eine andere Exception auf (z. B. Fehler beim
     * XML parsen).
     *
     * @param cause .
     */
    public IcaAuthenticationException(Throwable cause) {
        super(cause);
    }

}
