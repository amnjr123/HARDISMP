package GestionUtilisateur;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Disponibilite implements Serializable {
//Clé étrangères
    
    @ManyToOne
    private UtilisateurHardis utilisateurHardis;

    public UtilisateurHardis getUtilisateurHardis() {
        return utilisateurHardis;
    }

    public void setUtilisateurHardis(UtilisateurHardis utilisateurHardis) {
        this.utilisateurHardis = utilisateurHardis;
    }

//Attributs    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)    
    private Date dateFin;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disponibilite)) {
            return false;
        }
        Disponibilite other = (Disponibilite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionUtilisateur.Disponibilite[ id=" + id + " ]";
    }

    
}
