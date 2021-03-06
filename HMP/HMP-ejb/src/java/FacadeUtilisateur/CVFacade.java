/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionCatalogue.Offre;
import GestionUtilisateur.CV;
import GestionUtilisateur.Utilisateur;
import GestionUtilisateur.UtilisateurHardis;
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
public class CVFacade extends AbstractFacade<CV> implements CVFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CVFacade() {
        super(CV.class);
    }
    
    @Override
    public CV creerCV(String chemin, UtilisateurHardis uh, Offre o){
        CV cv = new CV();
        cv.setCheminCV(chemin);
        cv.setUtilisateurHardis(uh);
        cv.setOffre(o);
        create(cv);  
        uh.getcVs().add(cv);
        em.merge(uh);
        return cv;
    }
    
    @Override
    public CV creerCV(String chemin, UtilisateurHardis uh){
        CV cv = new CV();
        cv.setCheminCV(chemin);
        cv.setUtilisateurHardis(uh);
        create(cv);
        uh.getcVs().add(cv);
        em.merge(uh);
        return cv;
    }
    
    @Override
    public CV modifierCV(CV cv, String chemin){
        cv.setCheminCV(chemin);
        edit(cv);
        return cv;
    }
    
    @Override
    public CV supprimerCV(CV cv){
        remove(cv);
        return cv;
    }
    
    @Override
    public CV rechercheCV(long id){
        return find(id);
    }
    
    @Override
    public List<CV> rechercheCV(){
        return findAll();
    }
    
    @Override
    public List<CV> rechercherCV(Offre offre){
        Query requete = em.createQuery("select c from CV as c where c.offre=:offre ");
        requete.setParameter("offre", offre);
        return requete.getResultList();
    }
    
    @Override
    public List<CV> rechercherCV(UtilisateurHardis uh){
        Query requete = em.createQuery("select c from CV as c where c.utilisateurHardis=:utilisateur");
        requete.setParameter("utilisateur", uh);
        return requete.getResultList();
    }
    
    @Override
    public CV rechercherCV(Offre offre, UtilisateurHardis uh){
        Query requete = em.createQuery("select c from CV as c where c.offre=:offre and c.utilisateurHardis=:uh");
        requete.setParameter("offre", offre);
        requete.setParameter("uh", uh);
        if (!requete.getResultList().isEmpty()) {
            return (CV) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
    @Override
    public CV rechercherCVSansOffre(UtilisateurHardis uh){
        Query requete = em.createQuery("select c from CV as c where c.offre is null and c.utilisateurHardis=:uh");
        requete.setParameter("uh", uh);
        if (!requete.getResultList().isEmpty()) {
            return (CV) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
}
