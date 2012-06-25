package deltasys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsSectores.findAll", query = "select o from DsSectores o order by o.sector") })
@Table(name = "\"ds_sectores\"")
public class DsSectores implements Serializable {
    @Id
    @Column(name = "id_setor", nullable = false)
    private int id_setor;
    @Column(name = "sector")
    private String sector;

    public DsSectores() {
    }

    public DsSectores(int id_setor, String sector) {
        this.id_setor = id_setor;
        this.sector = sector;
    }

    public int getId_setor() {
        return id_setor;
    }

    public void setId_setor(int id_setor) {
        this.id_setor = id_setor;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
