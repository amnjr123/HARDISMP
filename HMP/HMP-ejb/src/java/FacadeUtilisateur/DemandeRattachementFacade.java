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
    public DemandeRattachement rechercherDemandeRattachement(Long id){
        return find(id);
    }
    
    @Override
    public DemandeRattachement rechercherDemandeRattachement(Entreprise e){
        Query requete = em.createQuery("select d from DemandeRattachement as d where d.entreprise=:entreprise");
        requete.setParameter("entreprise", e);
        if (!requete.getResultList().isEmpty()) {
            return (DemandeRattachement) requete.getSingleResult();
        } else {
            return null;
        }
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
    
    
    
     DemandeRattachement d = demandeRattachementFacade.creerDemandeRattachement(c, e);
   
     demandeRattachementFacade.supprimerDemandeRattachement(d);
       
}
