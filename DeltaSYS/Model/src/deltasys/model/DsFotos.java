package deltasys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsFotos.findAll", query = "select o from DsFotos o") })
@Table(name = "\"ds_fotos\"")
@IdClass(DsFotosPK.class)
public class DsFotos implements Serializable {
    @Id
    @Column(name = "id_folio", nullable = false, insertable = false, updatable = false)
    private int id_folio;
    @Id
    @Column(name = "id_foto", nullable = false)
    private int id_foto;
    @Column(name = "url")
    private String url;
    @ManyToOne
    @JoinColumn(name = "id_folio")
    private DsInfracciones dsInfracciones;

    public DsFotos() {
    }

    public DsFotos(DsInfracciones dsInfracciones, int id_foto, String url) {
        this.dsInfracciones = dsInfracciones;
        this.id_foto = id_foto;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DsInfracciones getDsInfracciones() {
        return dsInfracciones;
    }

    public void setDsInfracciones(DsInfracciones dsInfracciones) {
        this.dsInfracciones = dsInfracciones;
        if (dsInfracciones != null) {
            this.id_folio = dsInfracciones.getId_folio();
        }
    }
}
