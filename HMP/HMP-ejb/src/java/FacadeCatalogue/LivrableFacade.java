/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import GestionCatalogue.Livrable;
import javax.ejb.Stateless;
import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151882
 */
@Stateless
public class LivrableFacade extends AbstractFacade<Livrable> implements LivrableFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivrableFacade() {
        super(Livrable.class);
    }
    
    @Override
    public Livrable creerLivrable(String libelle, Service service){
        Livrable l = new Livrable();
        l.setLibelle(libelle);
        l.setService(service);
        create(l);  
        return l;
    }
    
    @Override
    public Livrable modifierLivrable(Livrable l, String libelle){
        l.setLibelle(libelle);
        edit(l);
        return l;
    }
    
    @Override
    public Livrable supprimerLivrable(Livrable l){
        remove(l);
        return l;
    }
    
    @Override
    public Livrable rechercheLivrable(long id){
        return find(id);
    }
    
    @Override
    public List<Livrable> rechercheLivrable(){
        return findAll();
    }
    
}
