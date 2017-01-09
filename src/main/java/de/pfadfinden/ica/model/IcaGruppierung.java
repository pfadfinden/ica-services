package de.pfadfinden.ica.model;

import java.util.Collection;
import java.util.regex.Pattern;

public class IcaGruppierung {

    private String descriptor;
    private int id;
    private Collection<IcaGruppierung> children;

    private static final Pattern GRPNUM_PATTERN = Pattern.compile("[\\d]+");
    private static final Pattern GRPNAME_PATTERN = Pattern.compile("[\\d]+");

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGruppierungsnummer() {
        return descriptor.substring(descriptor.lastIndexOf(' ')+1);
    }

    public String getGruppierungsname() {
        return descriptor.substring(0,descriptor.lastIndexOf(' '));
    }

}
