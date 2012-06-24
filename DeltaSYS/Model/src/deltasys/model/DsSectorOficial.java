package deltasys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsSectorOficial.findAll", query = "select o from DsSectorOficial o") })
@Table(name = "\"ds_sector_oficial\"")
@IdClass(DsSectorOficialPK.class)
public class DsSectorOficial implements Serializable {
    @Id
    @Column(name = "id_sector", nullable = false)
    private int id_sector;
    @Id
    @Column(name = "oid", nullable = false, insertable = false, updatable = false)
    private String oid;
    @ManyToOne
    @JoinColumn(name = "oid")
    private DsUsuarios dsUsuarios;

    public DsSectorOficial() {
    }

    public DsSectorOficial(int id_sector, DsUsuarios dsUsuarios) {
        this.id_sector = id_sector;
        this.dsUsuarios = dsUsuarios;
    }

    public int getId_sector() {
        return id_sector;
    }

    public void setId_sector(int id_sector) {
        this.id_sector = id_sector;
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
