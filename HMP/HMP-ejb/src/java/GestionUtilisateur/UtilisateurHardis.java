/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionUtilisateur;

import Enum.ProfilTechnique;
import GestionDevis.Communication;
import GestionDevis.Conversation;
import GestionDevis.Devis;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionDevis.Intervention;
import GestionDevis.Proposition;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UtilisateurHardis extends Utilisateur implements Serializable {
//Clés étrangères 

    @OneToMany(mappedBy = "utilisateurHardis")
    private List<CV> cVs;

    public List<CV> getcVs() {
        return cVs;
    }

    public void setcVs(List<CV> cVs) {
        this.cVs = cVs;
    }

    @ManyToOne
    private Agence agence;

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @OneToMany(mappedBy = "utilisateurHardis")
    private List<Disponibilite> disponibilites;

    public List<Disponibilite> getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(List<Disponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }

    public void addDisponibilite(Disponibilite d) {
        disponibilites.add(d);
        d.setUtilisateurHardis(this);
    }

    public void removeDisponibilite(Disponibilite d) {
        disponibilites.remove(d);
        d.setUtilisateurHardis(null);
    }

    @OneToMany(mappedBy = "UtilisateurHardis")
    private List<HistoriqueUtilisateurDevis> historiqueUtilisateurDeviss;

    public List<HistoriqueUtilisateurDevis> getHistoriqueUtilisateurDeviss() {
        return historiqueUtilisateurDeviss;
    }

    public void setHistoriqueUtilisateurDeviss(List<HistoriqueUtilisateurDevis> historiqueUtilisateurDeviss) {
        this.historiqueUtilisateurDeviss = historiqueUtilisateurDeviss;
    }
    @OneToMany(mappedBy = "UtilisateurHardis")
    private List<Intervention> interventions;

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }

    @OneToMany(mappedBy = "UtilisateurHardis")
    private List<Proposition> propositions;

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }

    @OneToMany(mappedBy = "UtilisateurHardis")
    private List<Conversation> conversations;

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @OneToMany(mappedBy = "utilisateurHardis")
    private List<Communication> communications;

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }

    @OneToMany(mappedBy = "utilisateurHardis")
    private List<Devis> deviss;

    public List<Devis> getDeviss() {
        return deviss;
    }

    public void setDeviss(List<Devis> deviss) {
        this.deviss = deviss;
    }

//Attributs
    public ProfilTechnique getProfilTechnique() {
        return profilTechnique;
    }

    public void setProfilTechnique(ProfilTechnique profilTechnique) {
        this.profilTechnique = profilTechnique;
    }

    private ProfilTechnique profilTechnique;

    public ProfilTechnique getString() {
        return profilTechnique;
    }

    public void setString(ProfilTechnique profilTechnique) {
        this.profilTechnique = profilTechnique;
    }

    private boolean actifInactif;

    public boolean getActifInactif() {
        return actifInactif;
    }

    public void setActifInactif(boolean actifInactif) {
        this.actifInactif = actifInactif;
    }

}
