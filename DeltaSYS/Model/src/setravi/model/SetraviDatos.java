package setravi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({ 
        @NamedQuery(name = "SetraviDatos.findAll", query = "select o from SetraviDatos o") ,
        @NamedQuery(name = "SetraviDatos.findDatosPlaca", query = "select o from SetraviDatos o where o.num_placa = :num_placa") 
    })
@Table(name = "\"setravi_datos\"")
public class SetraviDatos implements Serializable {
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "licencia")
    private String licencia;
    @Column(name = "nombre")
    private String nombre;
    @Id
    @Column(name = "num_placa", nullable = false)
    private String num_placa;

    public SetraviDatos() {
    }

    public SetraviDatos(String direccion, String licencia, String nombre, String num_placa) {
        this.direccion = direccion;
        this.licencia = licencia;
        this.nombre = nombre;
        this.num_placa = num_placa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNum_placa() {
        return num_placa;
    }

    public void setNum_placa(String num_placa) {
        this.num_placa = num_placa;
    }
}
