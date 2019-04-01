/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUtilisateur;

import GestionCatalogue.Offre;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author 5151882
 */
@Entity
public class Consultant extends UtilisateurHardis implements Serializable {

    @ManyToMany
    private List<Offre> offres;

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }

    private Float plafondDelegation;

    public Float getPlafondDelegation() {
        return plafondDelegation;
    }

    public void setPlafondDelegation(Float plafondDelegation) {
        this.plafondDelegation = plafondDelegation;
    }

    private Float prix;

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

}
