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
public class Proposition implements Serializable {
//Clés étrangères
    @ManyToOne
    private DevisNonStandard devisNonStandard;

    public DevisNonStandard getDevisNonStandard() {
        return devisNonStandard;
    }

    public void setDevisNonStandard(DevisNonStandard devisNonStandard) {
        this.devisNonStandard = devisNonStandard;
    }
    
    @ManyToOne
    private UtilisateurHardis UtilisateurHardis;

    public UtilisateurHardis getUtilisateurHardis() {
        return UtilisateurHardis;
    }

    public void setUtilisateurHardis(UtilisateurHardis UtilisateurHardis) {
        this.UtilisateurHardis = UtilisateurHardis;
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
    private Date dateDebutValidite;

    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFinValidite;

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    private String cheminDocument;

    public String getCheminDocument() {
        return cheminDocument;
    }

    public void setCheminDocument(String cheminDocument) {
        this.cheminDocument = cheminDocument;
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
        if (!(object instanceof Proposition)) {
            return false;
        }
        Proposition other = (Proposition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionDevis.Proposition[ id=" + id + " ]";
    }
    
}
