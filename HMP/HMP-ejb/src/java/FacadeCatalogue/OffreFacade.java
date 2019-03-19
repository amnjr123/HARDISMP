/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
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
    public Offre creerOffre(String libelle){
        Offre o = new Offre();
        o.setLibelle(libelle);
        o.setDateDebutValidite(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2100);
        o.setDateFinValidite(cal.getTime());
        create(o);  
        return o;
    }
    
    @Override
    public Offre modifierOffre(Offre o, String libelle){
        o.setLibelle(libelle);
        edit(o);
        return o;
    }
    
    @Override
    public Offre supprimerOffre(Offre o){
        o.setDateFinValidite(new Date());
        edit(o);
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
    
    @Override
    public List<Offre> rechercheOffresActuelles() {
        Query requete = getEntityManager().createQuery("select o from Offre as o where o.dateFinValidite>:date");
        requete.setParameter("date", new Date());
        return requete.getResultList();
    }
    
    @Override
    public List<Offre> rechercheOffresAnciennes() {
        Query requete = getEntityManager().createQuery("select o from Offre as o where o.dateFinValidite<=:date");
        requete.setParameter("date", new Date());
        return requete.getResultList();
    }
}
