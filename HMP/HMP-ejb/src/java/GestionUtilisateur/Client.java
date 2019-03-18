package GestionUtilisateur;

import GestionDevis.Communication;
import GestionDevis.Devis;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;



@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Utilisateur implements Serializable {    
//Clés étrangères  
    @OneToMany(mappedBy = "client")
    private List<Devis> deviss;

    public List<Devis> getDeviss() {
        return deviss;
    }

    public void setDeviss(List<Devis> deviss) {
        this.deviss = deviss;
    }
    
    @ManyToOne
    private Entreprise entreprise;

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    @OneToMany(mappedBy = "client")
    private List<Communication> communications;

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }
    
    @OneToOne(mappedBy = "client")
    private DemandeCreationEntreprise demandeCreationEntreprise;

    public DemandeCreationEntreprise getDemandeCreationEntreprise() {
        return demandeCreationEntreprise;
    }

    public void setDemandeCreationEntreprise(DemandeCreationEntreprise demandeCreationEntreprise) {
        this.demandeCreationEntreprise = demandeCreationEntreprise;
    }
    
    @OneToOne(mappedBy = "Client")
    private DemandeRattachement demandeRattachement;
    
    public DemandeRattachement getDemandeRattachement() {
        return demandeRattachement;
    }

    public void setDemandeRattachement(DemandeRattachement demandeRattachement) {
        this.demandeRattachement = demandeRattachement;
    }
    
//Attributs
   
    private boolean administrateur=false;

    public boolean getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

}
