/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDevis;

import GestionUtilisateur.UtilisateurHardis;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author 5151882
 */
@Entity
public class HistoriqueUtilisateurDevis implements Serializable {
//Clés étrangères
    @ManyToOne
    private Devis devis;

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    @ManyToOne
    private UtilisateurHardis utilisateurHardis;

    public UtilisateurHardis getUtilisateurHardis() {
        return utilisateurHardis;
    }

    public void setUtilisateurHardis(UtilisateurHardis utilisateurHardis) {
        this.utilisateurHardis = utilisateurHardis;
    }

//Attributs
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

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateDebut;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFin;

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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
        if (!(object instanceof HistoriqueUtilisateurDevis)) {
            return false;
        }
        HistoriqueUtilisateurDevis other = (HistoriqueUtilisateurDevis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionDevis.HistoriqueUtilisateurDevis[ id=" + id + " ]";
    }
    
}
