/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDevis;

import Enum.StatutDevis;
import GestionUtilisateur.Agence;
import GestionUtilisateur.Client;
import GestionUtilisateur.UtilisateurHardis;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author 5151882
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Devis implements Serializable {
//Clés étrangères
    
    @ManyToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    @OneToMany(mappedBy = "devis")
    private List<HistoriqueUtilisateurDevis> historiqueUtilisateurDeviss;

    public List<HistoriqueUtilisateurDevis> getHistoriqueUtilisateurDeviss() {
        return historiqueUtilisateurDeviss;
    }

    public void setHistoriqueUtilisateurDeviss(List<HistoriqueUtilisateurDevis> historiqueUtilisateurDeviss) {
        this.historiqueUtilisateurDeviss = historiqueUtilisateurDeviss;
    }
    
    @OneToMany(mappedBy = "devis")
    private List<Intervention> interventions;

    public List<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(List<Intervention> interventions) {
        this.interventions = interventions;
    }
    
    @OneToMany(mappedBy = "devis")
    private List<Communication> communications;

    public List<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(List<Communication> communications) {
        this.communications = communications;
    }
    
    @ManyToOne
    private UtilisateurHardis utilisateurHardis;

    public UtilisateurHardis getUtilisateurHardis() {
        return utilisateurHardis;
    }

    public void setUtilisateurHardis(UtilisateurHardis utilisateurHardis) {
        this.utilisateurHardis = utilisateurHardis;
    }
    
    @ManyToOne
    private Agence agence;

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }


    
//Attributs
    @Column(insertable = false, updatable = false) 
    private String dtype;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
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

    private StatutDevis statut;

    public StatutDevis getStatut() {
        return statut;
    }

    public void setStatut(StatutDevis statut) {
        this.statut = statut;
    }

    private String motifRefus;

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    //Date d'envoi au client
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateEnvoi;

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    
    //Date du refus ou de l'acceptation du devis du côté client
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateReponse;

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateAcompte;

    public Date getDateAcompte() {
        return dateAcompte;
    }

    public void setDateAcompte(Date dateAcompte) {
        this.dateAcompte = dateAcompte;
    }
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateReglement;

    public Date getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }


    
    private Float montant;

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    private String commentaireClient;

    public String getCommentaireClient() {
        return commentaireClient;
    }

    public void setCommentaireClient(String commentaireClient) {
        this.commentaireClient = commentaireClient;
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
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionDevis.Devis[ id=" + id + " ]";
    }
    
}
