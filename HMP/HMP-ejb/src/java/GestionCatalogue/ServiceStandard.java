/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCatalogue;

import GestionDevis.DevisStandard;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.print.DocFlavor;

/**
 *
 * @author 5151882
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ServiceStandard extends Service implements Serializable {
//Clés étrangères

    @OneToMany(mappedBy = "serviceStandard")
    private List<DevisStandard> devisStandards;

    public List<DevisStandard> getDevisStandards() {
        return devisStandards;
    }

    public void setDevisStandards(List<DevisStandard> devisStandards) {
        this.devisStandards = devisStandards;
    }

//Attributs
    private int nbrJoursConsultantSenior;

    public int getNbrJoursConsultantSenior() {
        return nbrJoursConsultantSenior;
    }

    public void setNbrJoursConsultantSenior(int nbrJoursConsultantSenior) {
        this.nbrJoursConsultantSenior = nbrJoursConsultantSenior;
    }

    private int nbrJoursConsultantConfirme;

    public int getNbrJoursConsultantConfirme() {
        return nbrJoursConsultantConfirme;
    }

    public void setNbrJoursConsultantConfirme(int nbrJoursConsultantConfirme) {
        this.nbrJoursConsultantConfirme = nbrJoursConsultantConfirme;
    }

    private int nbrJoursConsultantJunior;

    public int getNbrJoursConsultantJunior() {
        return nbrJoursConsultantJunior;
    }

    public void setNbrJoursConsultantJunior(int nbrJoursConsultantJunior) {
        this.nbrJoursConsultantJunior = nbrJoursConsultantJunior;
    }

    private int nbrHeuresAtelierEntretienPrevu;

    public int getNbrHeuresAtelierEntretienPrevu() {
        return nbrHeuresAtelierEntretienPrevu;
    }

    public void setNbrHeuresAtelierEntretienPrevu(int nbrHeuresAtelierEntretienPrevu) {
        this.nbrHeuresAtelierEntretienPrevu = nbrHeuresAtelierEntretienPrevu;
    }

    private int nbrHeuresSupportTel;

    public int getNbrHeuresSupportTel() {
        return nbrHeuresSupportTel;
    }

    public void setNbrHeuresSupportTel(int nbrHeuresSupportTel) {
        this.nbrHeuresSupportTel = nbrHeuresSupportTel;
    }
    
    @Column(length = 2500)
    private String descriptionPrestation;

    public String getDescriptionPrestation() {
        return descriptionPrestation;
    }

    public void setDescriptionPrestation(String descriptionPrestation) {
        this.descriptionPrestation = descriptionPrestation;
    }

}
