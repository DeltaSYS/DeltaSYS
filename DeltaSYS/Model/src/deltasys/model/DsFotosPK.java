package deltasys.model;

import java.io.Serializable;

public class DsFotosPK implements Serializable {
    private int id_folio;
    private int id_foto;

    public DsFotosPK() {
    }

    public DsFotosPK(int id_folio, int id_foto) {
        this.id_folio = id_folio;
        this.id_foto = id_foto;
    }

    public boolean equals(Object other) {
        if (other instanceof DsFotosPK) {
            final DsFotosPK otherDsFotosPK = (DsFotosPK)other;
            final boolean areEqual = (otherDsFotosPK.id_folio == id_folio && otherDsFotosPK.id_foto == id_foto);
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int getId_folio() {
        return id_folio;
    }

    public void setId_folio(int id_folio) {
        this.id_folio = id_folio;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }
}
