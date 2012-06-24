package deltasys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsPerfilesReglamento.findAll", query = "select o from DsPerfilesReglamento o") })
@Table(name = "\"ds_perfiles_reglamento\"")
@IdClass(DsPerfilesReglamentoPK.class)
public class DsPerfilesReglamento implements Serializable {
    @Id
    @Column(name = "id_articulo", nullable = false, insertable = false, updatable = false)
    private int id_articulo;
    @Id
    @Column(name = "id_fraccion", nullable = false, insertable = false, updatable = false)
    private String id_fraccion;
    @Id
    @Column(name = "id_inciso", nullable = false, insertable = false, updatable = false)
    private String id_inciso;
    @Id
    @Column(name = "id_perfil", nullable = false, insertable = false, updatable = false)
    private int id_perfil;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "id_inciso", referencedColumnName = "id_articulo"),
                    @JoinColumn(name = "id_fraccion", referencedColumnName = "id_fraccion"),
                    @JoinColumn(name = "id_articulo", referencedColumnName = "id_inciso") })
    private DsReglamento dsReglamento;
    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private DsPerfiles dsPerfiles;

    public DsPerfilesReglamento() {
    }

    public DsPerfilesReglamento(DsReglamento dsReglamento, DsPerfiles dsPerfiles) {
        this.dsReglamento = dsReglamento;
        this.dsPerfiles = dsPerfiles;
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

    public DsReglamento getDsReglamento() {
        return dsReglamento;
    }

    public void setDsReglamento(DsReglamento dsReglamento) {
        this.dsReglamento = dsReglamento;
        if (dsReglamento != null) {
            this.id_articulo = dsReglamento.getId_articulo();
            this.id_fraccion = dsReglamento.getId_fraccion();
            this.id_inciso = dsReglamento.getId_inciso();
        }
    }

    public DsPerfiles getDsPerfiles() {
        return dsPerfiles;
    }

    public void setDsPerfiles(DsPerfiles dsPerfiles) {
        this.dsPerfiles = dsPerfiles;
        if (dsPerfiles != null) {
            this.id_perfil = dsPerfiles.getId_perfil();
        }
    }
}
