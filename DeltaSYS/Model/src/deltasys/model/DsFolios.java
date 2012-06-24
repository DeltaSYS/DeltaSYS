package deltasys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "DsFolios.findAll", query = "select o from DsFolios o") })
@Table(name = "\"ds_folios\"")
public class DsFolios implements Serializable {
    @Column(name = "folio")
    private int folio;
    @Id
    @Column(name = "id_folio", nullable = false)
    private int id_folio;

    public DsFolios() {
    }

    public DsFolios(int folio, int id_folio) {
        this.folio = folio;
        this.id_folio = id_folio;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public int getId_folio() {
        return id_folio;
    }

    public void setId_folio(int id_folio) {
        this.id_folio = id_folio;
    }
}
