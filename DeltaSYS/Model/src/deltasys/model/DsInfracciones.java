package deltasys.model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries
    ({ 
        @NamedQuery(name = "DsInfracciones.findAll", query = "select o from DsInfracciones o"),
        @NamedQuery(name = "DsInfracciones.findInfraccion", query = "select o from DsInfracciones o where o.id_folio = :id_folio"),
        @NamedQuery(name = "DsInfracciones.findInfracciones", query = "select o from DsInfracciones o where o.id_folio = :id_folio or o.num_placa = :num_placa order by o.id_folio")
    })
@Table(name = "\"ds_infracciones\"")
public class DsInfracciones implements Serializable {
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "domicilio")
    private String domicilio;
    @Column(name = "fecha_hora")
    private Timestamp fecha_hora;
    @Id
    @Column(name = "id_folio", nullable = false)
    private int id_folio;
    @Column(name = "id_oid")
    private String id_oid;
    @Column(name = "latitud")
    private BigDecimal latitud;
    @Column(name = "licencia")
    private String licencia;
    @Column(name = "longitud")
    private BigDecimal longitud;
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "nom_infractor")
    private String nom_infractor;
    @Column(name = "num_placa")
    private String num_placa;
    @OneToMany(mappedBy = "dsInfracciones")
    private List<DsFotos> dsFotosList;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "id_articulo", referencedColumnName = "id_articulo"),
                    @JoinColumn(name = "id_fraccion", referencedColumnName = "id_fraccion"),
                    @JoinColumn(name = "id_inciso", referencedColumnName = "id_inciso") })
    private DsReglamento dsReglamento;
    @OneToMany(mappedBy = "dsInfracciones")
    private List<DsCancelaciones> dsCancelacionesList;

    public DsInfracciones() {
    }

    public DsInfracciones(String descripcion, String domicilio, Timestamp fecha_hora, int id_folio,
                          DsReglamento dsReglamento, String id_oid, BigDecimal latitud, String licencia,
                          BigDecimal longitud, BigDecimal monto, String nom_infractor, String num_placa) {
        this.descripcion = descripcion;
        this.domicilio = domicilio;
        this.fecha_hora = fecha_hora;
        this.id_folio = id_folio;
        this.dsReglamento = dsReglamento;
        this.id_oid = id_oid;
        this.latitud = latitud;
        this.licencia = licencia;
        this.longitud = longitud;
        this.monto = monto;
        this.nom_infractor = nom_infractor;
        this.num_placa = num_placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }


    public int getId_folio() {
        return id_folio;
    }

    public void setId_folio(int id_folio) {
        this.id_folio = id_folio;
    }


    public String getId_oid() {
        return id_oid;
    }

    public void setId_oid(String id_oid) {
        this.id_oid = id_oid;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getNom_infractor() {
        return nom_infractor;
    }

    public void setNom_infractor(String nom_infractor) {
        this.nom_infractor = nom_infractor;
    }

    public String getNum_placa() {
        return num_placa;
    }

    public void setNum_placa(String num_placa) {
        this.num_placa = num_placa;
    }

    public List<DsFotos> getDsFotosList() {
        return dsFotosList;
    }

    public void setDsFotosList(List<DsFotos> dsFotosList) {
        this.dsFotosList = dsFotosList;
    }

    public DsFotos addDsFotos(DsFotos dsFotos) {
        getDsFotosList().add(dsFotos);
        dsFotos.setDsInfracciones(this);
        return dsFotos;
    }

    public DsFotos removeDsFotos(DsFotos dsFotos) {
        getDsFotosList().remove(dsFotos);
        dsFotos.setDsInfracciones(null);
        return dsFotos;
    }

    public DsReglamento getDsReglamento() {
        return dsReglamento;
    }

    public void setDsReglamento(DsReglamento dsReglamento) {
        this.dsReglamento = dsReglamento;
    }

    public List<DsCancelaciones> getDsCancelacionesList() {
        return dsCancelacionesList;
    }

    public void setDsCancelacionesList(List<DsCancelaciones> dsCancelacionesList) {
        this.dsCancelacionesList = dsCancelacionesList;
    }

    public DsCancelaciones addDsCancelaciones(DsCancelaciones dsCancelaciones) {
        getDsCancelacionesList().add(dsCancelaciones);
        dsCancelaciones.setDsInfracciones(this);
        return dsCancelaciones;
    }

    public DsCancelaciones removeDsCancelaciones(DsCancelaciones dsCancelaciones) {
        getDsCancelacionesList().remove(dsCancelaciones);
        dsCancelaciones.setDsInfracciones(null);
        return dsCancelaciones;
    }
}
