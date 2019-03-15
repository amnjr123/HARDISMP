/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUtilisateur;

import GestionDevis.Devis;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author 5151882
 */
@Entity
public class Agence implements Serializable {
//Clés étrangères
    @OneToMany(mappedBy = "agence")
    private List<UtilisateurHardis> utilisateurHardiss;

    public List<UtilisateurHardis> getUtilisateurHardiss() {
        return utilisateurHardiss;
    }

    public void setUtilisateurHardiss(List<UtilisateurHardis> utilisateurHardiss) {
        this.utilisateurHardiss = utilisateurHardiss;
    }

    @OneToMany(mappedBy = "agence")
    private List<Devis> deviss;

    public List<Devis> getDeviss() {
        return deviss;
    }

    public void setDeviss(List<Devis> deviss) {
        this.deviss = deviss;
    }
    
    @OneToMany(mappedBy = "agence")
    private List<DemandeCreationEntreprise> demandeCreationEntreprises;

    public List<DemandeCreationEntreprise> getDemandeCreationEntreprises() {
        return demandeCreationEntreprises;
    }

    public void setDemandeCreationEntreprises(List<DemandeCreationEntreprise> demandeCreationEntreprises) {
        this.demandeCreationEntreprises = demandeCreationEntreprises;
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

    private String Localisation;

    public String getLocalisation() {
        return Localisation;
    }

    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
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
        if (!(object instanceof Agence)) {
            return false;
        }
        Agence other = (Agence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classes.Agence[ id=" + id + " ]";
    }
    
}
