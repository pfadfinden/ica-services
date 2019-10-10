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

    public Collection<IcaGruppierung> getChildren() {
        return children;
    }

    public void setChildren(Collection<IcaGruppierung> children) {
        this.children = children;
    }

    /**
     * Deaktiverte Gruppierungen werden nach MV Konvetion mit Präfix zzz versehen.
     *
     * @return true, falls Gruppierung als zzz markiert ist, ansonsten false.
     */
    public boolean isZzz(){
        return this.getGruppierungsname().substring(0, 3).equals("zzz");
    }

    /**
     * Prueft ob Gruppierungsebene nach MV Konvention Kindgruppierungen zulaesst.
     * Kindgruppierungen sind nur bei Bundes-, Landes- und Bezirksebene moeglich.
     *
     * @return true, falls Gruppierungsebene Kindgruppierungen zulaesst, ansonsten false.
     */
    public boolean isChildrenAllowed(){
        return this.getGruppierungsnummer().substring(this.getGruppierungsnummer().length() - 2).equals("00");
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("descriptor", descriptor)
                .add("gruppierungsnummer", getGruppierungsnummer())
                .add("gruppoerungsname", getGruppierungsname())
                .add("isZzz", isZzz())
                .add("isChildrenAllowed", isChildrenAllowed())
                .toString();
    }

}
