/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDevis;

import Enum.TypeCommunication;
import GestionUtilisateur.Client;
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
public class Communication implements Serializable {
//Clés étrangères
    @ManyToOne
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
    private Devis devis;

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
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
    private Date dateEnvoi;

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    private String contenu;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    private TypeCommunication typeCommunication;

    public TypeCommunication getTypeCommunication() {
        return typeCommunication;
    }

    public void setTypeCommunication(TypeCommunication typeCommunication) {
        this.typeCommunication = typeCommunication;
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
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionDevis.Communication[ id=" + id + " ]";
    }
    
}
