package deltasys.model;

import java.io.Serializable;

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
@NamedQueries( { @NamedQuery(name = "DsIntervalo.findAll", query = "select o from DsIntervalo o") })
@Table(name = "\"ds_intervalo\"")
public class DsIntervalo implements Serializable {
    @Column(name = "fecha_hora_modificado")
    private Timestamp fecha_hora_modificado;
    @Id
    @Column(name = "id_intervalo", nullable = false)
    private int id_intervalo;
    @Column(name = "intervalo")
    private int intervalo;
    @ManyToOne
    @JoinColumn(name = "oid_responsable")
    private DsUsuarios dsUsuarios;

    public DsIntervalo() {
    }

    public DsIntervalo(Timestamp fecha_hora_modificado, int id_intervalo, int intervalo, DsUsuarios dsUsuarios) {
        this.fecha_hora_modificado = fecha_hora_modificado;
        this.id_intervalo = id_intervalo;
        this.intervalo = intervalo;
        this.dsUsuarios = dsUsuarios;
    }

    public Timestamp getFecha_hora_modificado() {
        return fecha_hora_modificado;
    }

    public void setFecha_hora_modificado(Timestamp fecha_hora_modificado) {
        this.fecha_hora_modificado = fecha_hora_modificado;
    }

    public int getId_intervalo() {
        return id_intervalo;
    }

    public void setId_intervalo(int id_intervalo) {
        this.id_intervalo = id_intervalo;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }


    public DsUsuarios getDsUsuarios() {
        return dsUsuarios;
    }

    public void setDsUsuarios(DsUsuarios dsUsuarios) {
        this.dsUsuarios = dsUsuarios;
    }
}
