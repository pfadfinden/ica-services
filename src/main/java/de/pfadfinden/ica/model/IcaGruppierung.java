package de.pfadfinden.ica.model;

import com.google.common.base.MoreObjects;

import java.util.Collection;

public class IcaGruppierung {

    public IcaGruppierung() {
    }

    public IcaGruppierung(String descriptor, int id) {
        this.descriptor = descriptor;
        this.id = id;
    }

    private String descriptor;
    private int id;
    private Collection<IcaGruppierung> children;

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

    /**
     * Deaktiverte Gruppierungen werden nach MV Konvetion mit Präfix zzz versehen.
     *
     * @return true, falls Gruppierung als zzz markiert ist, ansonsten false.
     */
    public boolean isZzz(){
        if(this.getGruppierungsname().substring(0,3).equals("zzz")) return true;
        return false;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("descriptor", descriptor)
                .toString();
    }

}
