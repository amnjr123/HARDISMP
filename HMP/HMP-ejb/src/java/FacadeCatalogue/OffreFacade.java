/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151882
 */
@Stateless
public class OffreFacade extends AbstractFacade<Offre> implements OffreFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OffreFacade() {
        super(Offre.class);
    }
    
    @Override
    public Offre creerOffre(String libelle, Date dateDebutValidite, Date dateFinValidite){
        Offre o = new Offre();
        o.setLibelle(libelle);
        o.setDateDebutValidite(dateDebutValidite);
        o.setDateFinValidite(dateFinValidite);
        create(o);  
        return o;
    }
    
    @Override
    public Offre modifierOffre(Offre o, String libelle, Date dateDebutValidite, Date dateFinValidite){
        o.setLibelle(libelle);
        o.setDateDebutValidite(dateDebutValidite);
        o.setDateFinValidite(dateFinValidite);
        edit(o);
        return o;
    }
    
    @Override
    public Offre supprimerOffre(Offre o){
        remove(o);
        return o;
    }
    
    @Override
    public Offre rechercheOffre(long id){
        return find(id);
    }
    
    @Override
    public List<Offre> rechercheOffre(){
        return findAll();
    }
}
