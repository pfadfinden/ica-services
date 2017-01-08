package de.pfadfinden.ica.execption;

import java.net.URISyntaxException;

public class IcaURISyntaxException extends RuntimeException{

    public IcaURISyntaxException(URISyntaxException cause) {
        super(cause);
    }
}
