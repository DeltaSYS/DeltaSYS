package deltasys.model;

import java.io.Serializable;

public class DsPerfilesReglamentoPK implements Serializable {
    private int id_articulo;
    private String id_fraccion;
    private String id_inciso;
    private int id_perfil;

    public DsPerfilesReglamentoPK() {
    }

    public DsPerfilesReglamentoPK(int id_articulo, String id_fraccion, String id_inciso, int id_perfil) {
        this.id_articulo = id_articulo;
        this.id_fraccion = id_fraccion;
        this.id_inciso = id_inciso;
        this.id_perfil = id_perfil;
    }

    public boolean equals(Object other) {
        if (other instanceof DsPerfilesReglamentoPK) {
            final DsPerfilesReglamentoPK otherDsPerfilesReglamentoPK = (DsPerfilesReglamentoPK)other;
            final boolean areEqual =
                (otherDsPerfilesReglamentoPK.id_articulo == id_articulo && otherDsPerfilesReglamentoPK.id_fraccion.equals(id_fraccion) &&
                 otherDsPerfilesReglamentoPK.id_inciso.equals(id_inciso) &&
                 otherDsPerfilesReglamentoPK.id_perfil == id_perfil);
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

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
}
