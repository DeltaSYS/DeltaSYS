package setravi.model;

import java.io.Serializable;

import java.sql.Timestamp;

public class SetraviPuntosPK implements Serializable {
    private Timestamp fecha_hora;
    private String licencia;

    public SetraviPuntosPK() {
    }

    public SetraviPuntosPK(Timestamp fecha_hora, String licencia) {
        this.fecha_hora = fecha_hora;
        this.licencia = licencia;
    }

    public boolean equals(Object other) {
        if (other instanceof SetraviPuntosPK) {
            final SetraviPuntosPK otherSetraviPuntosPK = (SetraviPuntosPK)other;
            final boolean areEqual =
                (otherSetraviPuntosPK.fecha_hora.equals(fecha_hora) && otherSetraviPuntosPK.licencia.equals(licencia));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
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
}
