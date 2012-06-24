package deltasys.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({ 
    @NamedQuery(name = "DsReglamento.findAll", query = "select o from DsReglamento o"),
    @NamedQuery(name = "DsReglamento.findReglamento", query = "select o from DsReglamento o where o.id_articulo = :id_articulo  and o.id_fraccion = :id_fraccion and o.id_inciso = :id_inciso")
})
@Table(name = "\"ds_reglamento\"")
@IdClass(DsReglamentoPK.class)
public class DsReglamento implements Serializable {
    @Column(name = "descripcion")
    private String descripcion;
    @Id
    @Column(name = "id_articulo", nullable = false)
    private int id_articulo;
    @Id
    @Column(name = "id_fraccion", nullable = false)
    private String id_fraccion;
    @Id
    @Column(name = "id_inciso", nullable = false)
    private String id_inciso;
    @Column(name = "num_salarios")
    private int num_salarios;
    @OneToMany(mappedBy = "dsReglamento")
    private List<DsPerfilesReglamento> dsPerfilesReglamentoList;
    @OneToMany(mappedBy = "dsReglamento")
    private List<DsInfracciones> dsInfraccionesList;

    public DsReglamento() {
    }

    public DsReglamento(String descripcion, int id_articulo, String id_fraccion, String id_inciso, int num_salarios) {
        this.descripcion = descripcion;
        this.id_articulo = id_articulo;
        this.id_fraccion = id_fraccion;
        this.id_inciso = id_inciso;
        this.num_salarios = num_salarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getNum_salarios() {
        return num_salarios;
    }

    public void setNum_salarios(int num_salarios) {
        this.num_salarios = num_salarios;
    }

    public List<DsPerfilesReglamento> getDsPerfilesReglamentoList() {
        return dsPerfilesReglamentoList;
    }

    public void setDsPerfilesReglamentoList(List<DsPerfilesReglamento> dsPerfilesReglamentoList) {
        this.dsPerfilesReglamentoList = dsPerfilesReglamentoList;
    }

    public DsPerfilesReglamento addDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        getDsPerfilesReglamentoList().add(dsPerfilesReglamento);
        dsPerfilesReglamento.setDsReglamento(this);
        return dsPerfilesReglamento;
    }

    public DsPerfilesReglamento removeDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        getDsPerfilesReglamentoList().remove(dsPerfilesReglamento);
        dsPerfilesReglamento.setDsReglamento(null);
        return dsPerfilesReglamento;
    }

    public List<DsInfracciones> getDsInfraccionesList() {
        return dsInfraccionesList;
    }

    public void setDsInfraccionesList(List<DsInfracciones> dsInfraccionesList) {
        this.dsInfraccionesList = dsInfraccionesList;
    }

    public DsInfracciones addDsInfracciones(DsInfracciones dsInfracciones) {
        getDsInfraccionesList().add(dsInfracciones);
        dsInfracciones.setDsReglamento(this);
        return dsInfracciones;
    }

    public DsInfracciones removeDsInfracciones(DsInfracciones dsInfracciones) {
        getDsInfraccionesList().remove(dsInfracciones);
        dsInfracciones.setDsReglamento(null);
        return dsInfracciones;
    }
}
