/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.AbstractFacade;
import GestionUtilisateur.Client;
import GestionUtilisateur.DemandeRattachement;
import GestionUtilisateur.Entreprise;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class DemandeRattachementFacade extends AbstractFacade<DemandeRattachement> implements DemandeRattachementFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeRattachementFacade() {
        super(DemandeRattachement.class);
    }
    
    @Override
    public List<DemandeRattachement> rechercherDemandeRattachement(){
        return findAll();
    }
    
    @Override
    public DemandeRattachement rechercherDemandeRattachement(Long id){
        return find(id);
    }

    @Override
    public DemandeRattachement creerDemandeRattachement(Client c, Entreprise e){
        DemandeRattachement d = new DemandeRattachement();
        d.setClient(c);
        d.setEntreprise(e);
        d.setDateDemande(new Date());
        create(d);  
        return d;
    }
    
    @Override
    public List<DemandeRattachement> rechercherDemandeRattachement(Entreprise e){
        Query requete = em.createQuery("select d from DemandeRattachement as d where d.entreprise=:entreprise");
        requete.setParameter("entreprise", e);
        return requete.getResultList();
    }
    
    @Override
    public DemandeRattachement rechercherDemandeRattachement(Client c){
        Query requete = em.createQuery("select d from DemandeRattachement as d where d.Client=:client");
        requete.setParameter("client", c);
        if (!requete.getResultList().isEmpty()) {
            return (DemandeRattachement) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
    @Override
    public List<DemandeRattachement> rechercherDemandeRattachementUrgentes(){
        //Méthode pour administrateur - bug à corriger
        Query requete = em.createQuery("select d from DemandeRattachement as d where d.dateDemande<=date_sub(CURRENT_TIMESTAMP,3)");
        return requete.getResultList();
    }
    
    @Override
    public DemandeRattachement supprimerDemandeRattachement(DemandeRattachement d){
        remove(d);
        return d;
    }
}
