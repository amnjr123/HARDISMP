/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Devis;
import GestionDevis.Intervention;
import GestionUtilisateur.UtilisateurHardis;
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
public class InterventionFacade extends AbstractFacade<Intervention> implements InterventionFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterventionFacade() {
        super(Intervention.class);
    }
    
    
    @Override
    public Intervention creerIntervention(UtilisateurHardis uh, Devis devis, Date dateIntervention){
        Intervention i = new Intervention();
        i.setUtilisateurHardis(uh);
        i.setDevis(devis);
        i.setDateInterventionDemandee(dateIntervention);
        create(i);  
        return i;
    }
    
    @Override
    public Intervention modifierIntervention(Intervention i,UtilisateurHardis uh, Date dateIntervention){
        i.setUtilisateurHardis(uh);
        i.setDateInterventionDemandee(dateIntervention);
        edit(i);
        return i;
    }
    
    @Override
    public Intervention supprimerIntervention(Intervention i){
        remove(i);
        return i;
    }
    
    @Override
    public Intervention rechercheIntervention(long id){
        return find(id);
    }
    
    @Override
    public List<Intervention> rechercheIntervention(){
        return findAll();
    }
    
}
