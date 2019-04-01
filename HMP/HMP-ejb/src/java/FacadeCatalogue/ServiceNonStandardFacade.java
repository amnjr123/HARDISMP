/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeCatalogue;

import Enum.LieuIntervention;
import GestionCatalogue.ServiceNonStandard;
import FacadeUtilisateur.AbstractFacade;
import GestionCatalogue.Offre;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 5151882
 */
@Stateless
public class ServiceNonStandardFacade extends AbstractFacade<ServiceNonStandard> implements ServiceNonStandardFacadeLocal {

    @PersistenceContext(unitName = "HMP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceNonStandardFacade() {
        super(ServiceNonStandard.class);
    }
    
    @Override
    public ServiceNonStandard creerServiceNonStandard(String nom, String description, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o){
        ServiceNonStandard s = new ServiceNonStandard();
        s.setNom(nom);
        s.setDescriptionService(description);
        s.setLieuIntervention(lieu);
        s.setCout(cout);
        s.setFraisInclus(fraisInclus);
        s.setConditions(conditions);
        s.setDelaiRelance(delaiRelance);
        s.setOffre(o);
        s.setDateDebutValidite(new Date());
        s.setDtype("ServiceNonStandard");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2100);
        s.setDateFinValidite(cal.getTime());
        s.setAncienID(0);
        create(s);  
        return s;
    }
    
    @Override
    public ServiceNonStandard modifierServiceNonStandard(ServiceNonStandard ancienService, String nom, String descriptionService, LieuIntervention lieu, float cout, boolean fraisInclus, String conditions, int delaiRelance, Offre o){
        ServiceNonStandard nouveauService = new ServiceNonStandard();
        nouveauService.setNom(nom);
        nouveauService.setDescriptionService(descriptionService);
        nouveauService.setLieuIntervention(lieu);
        nouveauService.setCout(cout);
        nouveauService.setFraisInclus(fraisInclus);
        nouveauService.setConditions(conditions);
        nouveauService.setDelaiRelance(delaiRelance);
        nouveauService.setOffre(o);
        nouveauService.setDateDebutValidite(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2100);
        nouveauService.setDateFinValidite(cal.getTime());
        nouveauService.setAncienID(ancienService.getId());
        create(nouveauService);
        ancienService.setDateFinValidite(new Date());
        edit(ancienService);
        return nouveauService;
    }
    
    @Override
    public ServiceNonStandard supprimerServiceNonStandard(ServiceNonStandard service){
        service.setDateFinValidite(new Date());
        return service;
    }
    
    @Override
    public ServiceNonStandard rechercheServiceNonStandard(long id){
        return find(id);
    }
    
    @Override
    public List<ServiceNonStandard> rechercheServiceNonStandard(){
        return findAll();
    }
    
    @Override
    public List<ServiceNonStandard> rechercherServiceNonStandard(Offre o){
        Query requete = em.createQuery("select s from ServiceNonStandard as s where s.offre=:o ");
        requete.setParameter("o", o);
        return requete.getResultList();
    }
    
    @Override
    public List<ServiceNonStandard> rechercheServicesNonStandardsActuels(Offre o) {
        Query requete = getEntityManager().createQuery("select s from ServiceNonStandard as s where s.dateFinValidite>:date AND s.offre=:offre");
        requete.setParameter("date", new Date());
        requete.setParameter("offre", o);
        return requete.getResultList();
    }
}
