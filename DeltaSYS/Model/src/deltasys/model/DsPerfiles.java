package deltasys.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries
    ({
        @NamedQuery(name = "DsPerfiles.findAll", query = "select o from DsPerfiles o order by o.perfil") ,
        @NamedQuery(name = "DsPerfiles.findPerfil", query = "select o from DsPerfiles o where o.id_perfil = :id_perfil") 
    })
@Table(name = "\"ds_perfiles\"")
public class DsPerfiles implements Serializable {
    @Id
    @Column(name = "id_perfil", nullable = false)
    private int id_perfil;
    @Column(name = "perfil")
    private String perfil;
    @OneToMany(mappedBy = "dsPerfiles")
    private List<DsUsuarios> dsUsuariosList;
    @OneToMany(mappedBy = "dsPerfiles")
    private List<DsPerfilesReglamento> dsPerfilesReglamentoList;

    public DsPerfiles() {
    }

    public DsPerfiles(int id_perfil, String perfil) {
        this.id_perfil = id_perfil;
        this.perfil = perfil;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<DsUsuarios> getDsUsuariosList() {
        return dsUsuariosList;
    }

    public void setDsUsuariosList(List<DsUsuarios> dsUsuariosList) {
        this.dsUsuariosList = dsUsuariosList;
    }

    public DsUsuarios addDsUsuarios(DsUsuarios dsUsuarios) {
        getDsUsuariosList().add(dsUsuarios);
        dsUsuarios.setDsPerfiles(this);
        return dsUsuarios;
    }

    public DsUsuarios removeDsUsuarios(DsUsuarios dsUsuarios) {
        getDsUsuariosList().remove(dsUsuarios);
        dsUsuarios.setDsPerfiles(null);
        return dsUsuarios;
    }

    public List<DsPerfilesReglamento> getDsPerfilesReglamentoList() {
        return dsPerfilesReglamentoList;
    }

    public void setDsPerfilesReglamentoList(List<DsPerfilesReglamento> dsPerfilesReglamentoList) {
        this.dsPerfilesReglamentoList = dsPerfilesReglamentoList;
    }

    public DsPerfilesReglamento addDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        getDsPerfilesReglamentoList().add(dsPerfilesReglamento);
        dsPerfilesReglamento.setDsPerfiles(this);
        return dsPerfilesReglamento;
    }

    public DsPerfilesReglamento removeDsPerfilesReglamento(DsPerfilesReglamento dsPerfilesReglamento) {
        getDsPerfilesReglamentoList().remove(dsPerfilesReglamento);
        dsPerfilesReglamento.setDsPerfiles(null);
        return dsPerfilesReglamento;
    }
}
