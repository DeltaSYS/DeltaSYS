package deltasys.model;

import java.io.Serializable;

public class DsReglamentoPK implements Serializable {
    private int id_articulo;
    private String id_fraccion;
    private String id_inciso;

    public DsReglamentoPK() {
    }

    public DsReglamentoPK(int id_articulo, String id_fraccion, String id_inciso) {
        this.id_articulo = id_articulo;
        this.id_fraccion = id_fraccion;
        this.id_inciso = id_inciso;
    }

    public boolean equals(Object other) {
        if (other instanceof DsReglamentoPK) {
            final DsReglamentoPK otherDsReglamentoPK = (DsReglamentoPK)other;
            final boolean areEqual =
                (otherDsReglamentoPK.id_articulo == id_articulo && otherDsReglamentoPK.id_fraccion.equals(id_fraccion) &&
                 otherDsReglamentoPK.id_inciso.equals(id_inciso));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getId_fraccion() {
        return id_fraccion;
    }

    public void setId_fraccion(String id_fraccion) {
        this.id_fraccion = id_fraccion;
    }

    public String getId_inciso() {
        return id_inciso;
    }

    public void setId_inciso(String id_inciso) {
        this.id_inciso = id_inciso;
    }
}
