package deltasys.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
            @NamedQuery(name = "DsCancelaciones.findAll", query = "select o from DsCancelaciones o") ,
            @NamedQuery(name = "DsCancelaciones.findFolioCancelado", query = "select o from DsCancelaciones o where o.id_folio = :id_folio") 
        })
@Table(name = "\"ds_cancelaciones\"")
public class DsCancelaciones implements Serializable {
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "folio_oficio")
    private String folio_oficio;
    @Id
    @Column(name = "id_folio", nullable = false, insertable = false, updatable = false)
    private int id_folio;
    @ManyToOne
    @JoinColumn(name = "id_folio")
    private DsInfracciones dsInfracciones;

    public DsCancelaciones() {
    }

    public DsCancelaciones(Date fecha, String folio_oficio, DsInfracciones dsInfracciones) {
        this.fecha = fecha;
        this.folio_oficio = folio_oficio;
        this.dsInfracciones = dsInfracciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFolio_oficio() {
        return folio_oficio;
    }

    public void setFolio_oficio(String folio_oficio) {
        this.folio_oficio = folio_oficio;
    }

    public int getId_folio() {
        return id_folio;
    }

    public void setId_folio(int id_folio) {
        this.id_folio = id_folio;
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
