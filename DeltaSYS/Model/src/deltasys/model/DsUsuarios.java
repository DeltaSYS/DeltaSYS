package deltasys.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries
    ({   
        @NamedQuery(name = "DsUsuarios.findAll", query = "select o from DsUsuarios o order by o.nombre") ,
        @NamedQuery(name = "DsUsuarios.findUsuario", query = "select o from DsUsuarios o where o.oid = :oid") ,
        @NamedQuery(name = "DsUsuarios.findCatalogo", query = "select o from DsUsuarios o where o.oid = :oid or o.nombre = :nombre or o.dsPerfiles.id_perfil = :id_perfil or 1 = :all order by o.nombre"),
        @NamedQuery(name = "DsUsuarios.findLogin", query = "select o from DsUsuarios o where o.oid = :oid and o.password = :password") 
    })
@Table(name = "\"ds_usuarios\"")
public class DsUsuarios implements Serializable {
    @Column(name = "nombre")
    private String nombre;
    @Id
    @Column(name = "oid", nullable = false)
    private String oid;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "dsUsuarios")
    private List<DsUbicaciones> dsUbicacionesList;
    @OneToMany(mappedBy = "dsUsuarios")
    private List<DsSectorOficial> dsSectorOficialList;
    @OneToMany(mappedBy = "dsUsuarios")
    private List<DsIntervalo> dsIntervaloList;
    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private DsPerfiles dsPerfiles;

    public DsUsuarios() {
    }

    public DsUsuarios(DsPerfiles dsPerfiles, String nombre, String oid, String password) {
        this.dsPerfiles = dsPerfiles;
        this.nombre = nombre;
        this.oid = oid;
        this.password = password;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DsUbicaciones> getDsUbicacionesList() {
        return dsUbicacionesList;
    }

    public void setDsUbicacionesList(List<DsUbicaciones> dsUbicacionesList) {
        this.dsUbicacionesList = dsUbicacionesList;
    }

    public DsUbicaciones addDsUbicaciones(DsUbicaciones dsUbicaciones) {
        getDsUbicacionesList().add(dsUbicaciones);
        dsUbicaciones.setDsUsuarios(this);
        return dsUbicaciones;
    }

    public DsUbicaciones removeDsUbicaciones(DsUbicaciones dsUbicaciones) {
        getDsUbicacionesList().remove(dsUbicaciones);
        dsUbicaciones.setDsUsuarios(null);
        return dsUbicaciones;
    }

    public List<DsSectorOficial> getDsSectorOficialList() {
        return dsSectorOficialList;
    }

    public void setDsSectorOficialList(List<DsSectorOficial> dsSectorOficialList) {
        this.dsSectorOficialList = dsSectorOficialList;
    }

    public DsSectorOficial addDsSectorOficial(DsSectorOficial dsSectorOficial) {
        getDsSectorOficialList().add(dsSectorOficial);
        dsSectorOficial.setDsUsuarios(this);
        return dsSectorOficial;
    }

    public DsSectorOficial removeDsSectorOficial(DsSectorOficial dsSectorOficial) {
        getDsSectorOficialList().remove(dsSectorOficial);
        dsSectorOficial.setDsUsuarios(null);
        return dsSectorOficial;
    }

    public List<DsIntervalo> getDsIntervaloList() {
        return dsIntervaloList;
    }

    public void setDsIntervaloList(List<DsIntervalo> dsIntervaloList) {
        this.dsIntervaloList = dsIntervaloList;
    }

    public DsIntervalo addDsIntervalo(DsIntervalo dsIntervalo) {
        getDsIntervaloList().add(dsIntervalo);
        dsIntervalo.setDsUsuarios(this);
        return dsIntervalo;
    }

    public DsIntervalo removeDsIntervalo(DsIntervalo dsIntervalo) {
        getDsIntervaloList().remove(dsIntervalo);
        dsIntervalo.setDsUsuarios(null);
        return dsIntervalo;
    }

    public DsPerfiles getDsPerfiles() {
        return dsPerfiles;
    }

    public void setDsPerfiles(DsPerfiles dsPerfiles) {
        this.dsPerfiles = dsPerfiles;
    }
}
