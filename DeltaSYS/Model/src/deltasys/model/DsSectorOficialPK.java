package deltasys.model;

import java.io.Serializable;

public class DsSectorOficialPK implements Serializable {
    private int id_sector;
    private String oid;

    public DsSectorOficialPK() {
    }

    public DsSectorOficialPK(int id_sector, String oid) {
        this.id_sector = id_sector;
        this.oid = oid;
    }

    public boolean equals(Object other) {
        if (other instanceof DsSectorOficialPK) {
            final DsSectorOficialPK otherDsSectorOficialPK = (DsSectorOficialPK)other;
            final boolean areEqual =
                (otherDsSectorOficialPK.id_sector == id_sector && otherDsSectorOficialPK.oid.equals(oid));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int getId_sector() {
        return id_sector;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
