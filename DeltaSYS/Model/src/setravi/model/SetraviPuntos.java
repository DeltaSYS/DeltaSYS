package setravi.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "SetraviPuntos.findAll", query = "select o from SetraviPuntos o") })
@Table(name = "\"setravi_puntos\"")
@IdClass(SetraviPuntosPK.class)
public class SetraviPuntos implements Serializable {
    @Id
    @Column(name = "fecha_hora", nullable = false)
    private Timestamp fecha_hora;
    @Id
    @Column(name = "licencia", nullable = false)
    private String licencia;
    @Column(name = "motivo")
    private String motivo;

    public SetraviPuntos() {
    }

    public SetraviPuntos(Timestamp fecha_hora, String licencia, String motivo) {
        this.fecha_hora = fecha_hora;
        this.licencia = licencia;
        this.motivo = motivo;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
