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
import javax.persistence.Query;

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
    
    @Override
    public Intervention rechercheIntervention(UtilisateurHardis uh, Date dateDispo) {
        Query requete = getEntityManager().createQuery("select i from Intervention as i where i.UtilisateurHardis=:uh and i.dateInterventionDemandee=:dateDispo");
        requete.setParameter("uh", uh);
        requete.setParameter("dateDispo", dateDispo);
        if (!requete.getResultList().isEmpty()) {
            return (Intervention) requete.getSingleResult();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Intervention> rechercheInterventions(UtilisateurHardis uh) {
        Query requete = getEntityManager().createQuery("select i from Intervention as i where i.UtilisateurHardis=:uh");
        requete.setParameter("uh", uh);
        return requete.getResultList();
    }
    
    @Override
    public List<Intervention> rechercheInterventions(Devis d) {
        Query requete = getEntityManager().createQuery("select i from Intervention as i where i.devis=:d");
        requete.setParameter("d", d);
        return requete.getResultList();
    }
    
}
