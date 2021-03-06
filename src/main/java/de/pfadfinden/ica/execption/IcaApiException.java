package de.pfadfinden.ica.execption;


import de.pfadfinden.ica.model.IcaApiResponse;
import de.pfadfinden.ica.model.IcaResponse;

public class IcaApiException extends IcaException{

    /**
     * Erzeugt die Exception mit einem beliebigen String als Meldung.
     *
     * @param str
     *            Meldung.
     */
    public IcaApiException(String str) {
        super(str);
    }

    /**
     * Erzeugt die Exception, wobei die Antwort von NaMi als Meldung verwendet
     * wird. Es wird der gelieferte StatusCode und die zugehörige Meldung in die
     * Exception gespeichert.
     *
     * @param resp
     *            Antwort vom NaMi-Server
     */
    public IcaApiException(IcaApiResponse<? extends IcaResponse> resp) {
        super(resp.getResponse().getTitle() + ": " + resp.getResponse().getMessage());
    }

    /**
     * Erzeugt die Exception, wobei eine weitere Exception als Grund angegeben
     * wird.
     *
     * @param cause
     *            .
     */
    public IcaApiException(Throwable cause) {
        super(cause);
    }

}
