/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCatalogue;

import Enum.LieuIntervention;
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
public class Service implements Serializable {
//Clés étrangères

    @ManyToOne
    private Offre offre;

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    @OneToMany(mappedBy = "service")
    private List<Livrable> livrables;

    public List<Livrable> getLivrables() {
        return livrables;
    }

    public void setLivrables(List<Livrable> livrables) {
        this.livrables = livrables;
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

    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(length = 2500)
    private String descriptionService;

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    private LieuIntervention lieuIntervention;

    public LieuIntervention getLieuIntervention() {
        return lieuIntervention;
    }

    public void setLieuIntervention(LieuIntervention lieuIntervention) {
        this.lieuIntervention = lieuIntervention;
    }

    private Float cout;

    public Float getCout() {
        return cout;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }

    private boolean fraisInclus;

    public boolean getFraisInclus() {
        return fraisInclus;
    }

    public void setFraisInclus(boolean fraisInclus) {
        this.fraisInclus = fraisInclus;
    }

    @Column(length = 2500)
    private String conditions;

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    private int delaiRelance;

    public int getDelaiRelance() {
        return delaiRelance;
    }

    public void setDelaiRelance(int delaiRelance) {
        this.delaiRelance = delaiRelance;
    }
    //Attributs pour Historisation

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

    private long ancienID;

    public long getAncienID() {
        return ancienID;
    }

    public void setAncienID(long ancienID) {
        this.ancienID = ancienID;
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GestionCatalogue.Service[ id=" + id + " ]";
    }

}
