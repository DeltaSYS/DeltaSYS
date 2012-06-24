package deltasys.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsUbicaciones.findAll", query = "select o from DsUbicaciones o") })
@Table(name = "\"ds_ubicaciones\"")
public class DsUbicaciones implements Serializable {
    @Column(name = "fecha_hora")
    private Timestamp fecha_hora;
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Id
    @Column(name = "oid", nullable = false, insertable = false, updatable = false)
    private String oid;
    @ManyToOne
    @JoinColumn(name = "oid")
    private DsUsuarios dsUsuarios;

    public DsUbicaciones() {
    }

    public DsUbicaciones(Timestamp fecha_hora, BigDecimal latitud, BigDecimal longitud, DsUsuarios dsUsuarios) {
        this.fecha_hora = fecha_hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.dsUsuarios = dsUsuarios;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public DsUsuarios getDsUsuarios() {
        return dsUsuarios;
    }

    public void setDsUsuarios(DsUsuarios dsUsuarios) {
        this.dsUsuarios = dsUsuarios;
        if (dsUsuarios != null) {
            this.oid = dsUsuarios.getOid();
        }
    }
}
