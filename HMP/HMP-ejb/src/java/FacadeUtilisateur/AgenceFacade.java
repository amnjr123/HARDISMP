/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeUtilisateur;

import GestionUtilisateur.Agence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 5151882
 */
@Stateless
public class AgenceFacade extends AbstractFacade<Agence> implements AgenceFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AgenceFacade() {
        super(Agence.class);
    }
    
    
    @Override
    public Agence creerAgence(String localisation, String adresse){
        Agence a = new Agence();
        a.setLocalisation(localisation);
        a.setAdresse(adresse);
        create(a);  
        return a;
    }
    
    @Override
    public Agence modifierAgence(Agence a, String localisation){
        a.setLocalisation(localisation);
        edit(a);
        return a;
    }
    
    @Override
    public Agence supprimerAgence(Agence a){
        remove(a);
        return a;
    }
    
    @Override
    public Agence rechercheAgence(long id){
        return find(id);
    }
    
    @Override
    public List<Agence> rechercheAgences(){
        return findAll();
    }
    
}
