/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionCatalogue.Offre;
import GestionUtilisateur.CV;
import GestionUtilisateur.UtilisateurHardis;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
