/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeDevis;

import GestionDevis.Devis;
import GestionDevis.HistoriqueUtilisateurDevis;
import GestionUtilisateur.UtilisateurHardis;
import java.util.Calendar;
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
public class HistoriqueUtilisateurDevisFacade extends AbstractFacade<HistoriqueUtilisateurDevis> implements HistoriqueUtilisateurDevisFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoriqueUtilisateurDevisFacade() {
        super(HistoriqueUtilisateurDevis.class);
    }
    
    //Méthode à utiliser lors de l'affectation du tout premier employé sur le devis
    @Override
    public HistoriqueUtilisateurDevis creerPremierHistoriqueUtilisateurDevis(Devis devis, UtilisateurHardis uh){
        HistoriqueUtilisateurDevis historique = new HistoriqueUtilisateurDevis();
        historique.setDevis(devis);
        historique.setUtilisateurHardis(uh);
        historique.setDateDebut(new Date());
         Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, 0);
            cal.set(Calendar.YEAR, 2100);
            historique.setDateFin(cal.getTime());
        create(historique);  
        return historique;
    }
    
    //Méthode à utiliser lors du transfert de devis entre employés
    @Override
    public HistoriqueUtilisateurDevis creerSuiteHistoriqueUtilisateurDevis(HistoriqueUtilisateurDevis ancienHistorique, UtilisateurHardis nouvelUtilisateur){
        //Création nouvel historique
        HistoriqueUtilisateurDevis nouvelHistorique = new HistoriqueUtilisateurDevis();
        //Récupération du devis
        nouvelHistorique.setDevis(ancienHistorique.getDevis());
        //Affectation du nouveau gestionnaires et de ses dates
        nouvelHistorique.setUtilisateurHardis(nouvelUtilisateur);
        nouvelHistorique.setDateDebut(new Date());
        nouvelHistorique.setDateFin(new Date(Long.MAX_VALUE));
        //Affectation date de fin de l'ancien historique
        ancienHistorique.setDateFin(new Date());
        //Mise à jour BDD
        create(nouvelHistorique);
        edit(ancienHistorique);
        return nouvelHistorique;
    }
    
    @Override
    public HistoriqueUtilisateurDevis rechercheHistoriqueUtilisateurDevis(long id){
        return find(id);
    }
    
    @Override
    public List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis(){
        return findAll();
    }
    
    @Override
    public List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis(UtilisateurHardis uh){
        Query requete = em.createQuery("SELECT hud FROM HistoriqueUtilisateurDevis as hud where hud.utilisateurHardis=:uh");
        requete.setParameter("uh",uh);
        return requete.getResultList();
    }
    
    @Override
    public List<HistoriqueUtilisateurDevis> rechercheHistoriqueUtilisateurDevis(Devis devis){
        Query requete = em.createQuery("SELECT hud FROM HistoriqueUtilisateurDevis as hud where hud.devis=:devis");
        requete.setParameter("devis",devis);
        return requete.getResultList();
    }
    
    @Override
    public HistoriqueUtilisateurDevis rechercheDernierHistoriqueUtilisateurDevis(Devis devis){
        Query requete = em.createQuery("SELECT hud FROM HistoriqueUtilisateurDevis as hud where hud.devis=:devis and hud.dateFin>=:CURRENT_TIMESTAMP");
        requete.setParameter("devis",devis);
        if (!requete.getResultList().isEmpty()) {
            return (HistoriqueUtilisateurDevis) requete.getSingleResult();
        } else {
            return null;
        }
    }
}
